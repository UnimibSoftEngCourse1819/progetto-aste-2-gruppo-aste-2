package model.auction.secondsealed;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import controller.database.SQLOperation;
import exception.IncompatibilityClassException;
import model.Offer;
import model.Operation;
import model.Transaction;
import model.auction.Auction;

public class AuctionSecondSealed extends Auction {

	public AuctionSecondSealed(HttpServletRequest request) {
		super(request);
	}

	public AuctionSecondSealed(Map<String, Object> rowValues) {
		super(rowValues);
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
	public List<SQLOperation> getCloseOperation() {
		List<SQLOperation> operationToDo = new ArrayList<>();
		//TODO da riaddatare
//		SecondSealedOffer winner = offers.get(0);
//		SecondSealedOffer second = winner;
//		
//		for(SecondSealedOffer offerToScan : offers) {
//			
//			if(offerToScan.compareTo(winner) > 0) {
//				winner = offerToScan;
//			}else if(offerToScan.compareTo(second) > 0) {
//				second = offerToScan;
//			}
//		}
//		
//		operationToDo.add(new Transaction(winner.getBidder(), seller, second.getPrice()));
//		
		return operationToDo;
		
	}

	@Override
	public String getType() {
		return "SecondSealed";
	}
	
	
}
