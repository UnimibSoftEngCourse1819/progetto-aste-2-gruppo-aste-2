package model.auction.englishauction;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import controller.database.SQLOperation;
import exception.IncompatibilityClassException;
import model.Offer;
import model.auction.Auction;

public class EnglishAuction extends Auction {
	public EnglishAuction(HttpServletRequest request) {
		super(request);
	}

	public EnglishAuction(Map<String, Object> rowValues) {
		super(rowValues);
	}

	public EnglishAuction(HttpServletRequest request, LinkedHashMap<String, String> values) {
		super(request, values);
	}

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
	public List<SQLOperation> getCloseOperation() {
		List<SQLOperation> operationToDo = new ArrayList<>();
		//TODO da riaddatare
//		EnglishOffer winner = offers.get(0);
//		
//		for(EnglishOffer offerToScan : offers) {
//			if(offerToScan.compareTo(winner) > 0) {
//				winner = offerToScan;
//			}
//		}
//		List<Operation> operationToDo = new ArrayList<>();
//		operationToDo.add(new Transaction(winner.getBidder(), seller, winner.getPrice()));
		
		return operationToDo;
	}

	@Override
	public String getType() {
		return "English";
	}

}
