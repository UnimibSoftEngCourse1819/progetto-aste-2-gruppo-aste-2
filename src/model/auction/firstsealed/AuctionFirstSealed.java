package model.auction.firstsealed;

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

public class AuctionFirstSealed extends Auction {
	
	private List<FirstSealedOffer> offers;
	
	public AuctionFirstSealed(HttpServletRequest request) {
		super(request);
	}

	public AuctionFirstSealed(Map<String, Object> rowValues) {
		super(rowValues);
	}

	@Override
	public void addOffer(Offer newOffer) throws IncompatibilityClassException {
		if(!newOffer.getClass().equals(FirstSealedOffer.class)) {
			throw new IncompatibilityClassException();
		}
		
		offers.add((FirstSealedOffer) newOffer);
	}
	
	@Override
	public  List<SQLOperation> getCloseOperation(){
		List<SQLOperation> operationToDo = new ArrayList<>();
		//TODO da riaddatare
//		FirstSealedOffer winner = offers.get(0);
//		for(FirstSealedOffer offerToScan : offers) {
//			if(offerToScan.compareTo(winner) > 0) {
//				winner = offerToScan;
//			}
//		}
//		operationToDo.add(new Transaction(winner.getBidder(), seller, winner.getPrice()));
		return operationToDo;
	}

	@Override
	public String getType() {
		return "FirstSealed";
	}

	

}
