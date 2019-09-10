package model.auction.englishauction;

import java.util.ArrayList;
import java.util.List;

import exception.IncompatibilityClassException;
import model.Offer;
import model.Operation;
import model.Transaction;
import model.auction.Auction;

public class EnglishAuction extends Auction {
	private List<EnglishOffer> offers;
	private long basePrice;
	private long minIncrement;
	
	@Override
	public void addOffer(Offer newOffer) throws IncompatibilityClassException {
		if(!newOffer.getClass().equals(EnglishOffer.class)) {
			throw new IncompatibilityClassException();
		}
		
		long price = ((EnglishOffer) newOffer).getPrice();
		
		if(price >= basePrice + minIncrement) {
			offers.add((EnglishOffer) newOffer);
			basePrice = price;
		}
	}

	@Override
	public List<Operation> end() {
		EnglishOffer winner = offers.get(0);
		
		for(EnglishOffer offerToScan : offers) {
			if(offerToScan.compareTo(winner) > 0) {
				winner = offerToScan;
			}
		}
		List<Operation> operationToDo = new ArrayList<>();
		operationToDo.add(new Transaction(winner.getBidder(), seller, winner.getPrice()));
		
		return operationToDo;
	}

}
