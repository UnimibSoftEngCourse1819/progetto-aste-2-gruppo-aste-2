package model.auction.secondsealed;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import exception.IncompatibilityClassException;
import model.Offer;
import model.Operation;
import model.Transaction;
import model.auction.Auction;

public class AuctionSecondSealed extends Auction {

	public AuctionSecondSealed(HttpServletRequest request) {
		super(request);
	}

	private List<SecondSealedOffer> offers;

	@Override
	public void addOffer(Offer newOffer) throws IncompatibilityClassException {
		if(!newOffer.getClass().equals(SecondSealedOffer.class)) {
			throw new IncompatibilityClassException();
		}
		
		offers.add((SecondSealedOffer) newOffer);
	}

	@Override
	public List<Operation> end() {
		SecondSealedOffer winner = offers.get(0);
		SecondSealedOffer second = winner;
		
		for(SecondSealedOffer offerToScan : offers) {
			
			if(offerToScan.compareTo(winner) > 0) {
				winner = offerToScan;
			}else if(offerToScan.compareTo(second) > 0) {
				second = offerToScan;
			}
		}
		
		List<Operation> operationToDo = new ArrayList<>();
		operationToDo.add(new Transaction(winner.getBidder(), seller, second.getPrice()));
		
		return operationToDo;
	}

	@Override
	protected String getType() {
		return "SecondSealed";
	}
	
	
}
