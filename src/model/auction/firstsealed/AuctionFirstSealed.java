package model.auction.firstsealed;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import exception.IncompatibilityClassException;
import model.Offer;
import model.Operation;
import model.Transaction;
import model.auction.Auction;

public class AuctionFirstSealed extends Auction {
	
	private List<FirstSealedOffer> offers;
	
	public AuctionFirstSealed(HttpServletRequest request) {
		super(request);
	}

	@Override
	public void addOffer(Offer newOffer) throws IncompatibilityClassException {
		if(!newOffer.getClass().equals(FirstSealedOffer.class)) {
			throw new IncompatibilityClassException();
		}
		
		offers.add((FirstSealedOffer) newOffer);
	}
	
	@Override
	public  List<Operation> end(){
		FirstSealedOffer winner = offers.get(0);
		for(FirstSealedOffer offerToScan : offers) {
			if(offerToScan.compareTo(winner) > 0) {
				winner = offerToScan;
			}
		}
		List<Operation> operationToDo = new ArrayList<>();
		operationToDo.add(new Transaction(winner.getBidder(), seller, winner.getPrice()));
		return operationToDo;
	}

	@Override
	protected String getType() {
		return "FirstSealed";
	}

	

}
