package model.auction.dutchauction;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import controller.database.SQLOperation;
import exception.IncompatibilityClassException;
import model.Offer;
import model.Transaction;
import model.auction.Auction;

public class DutchAuction extends Auction {
	public DutchAuction(HttpServletRequest request) {
		super(request);
	}

	public DutchAuction(Map<String, Object> rowValues) {
		super(rowValues);
	}

	private long minPrice;
	private long currentPrice;
	private long amount;
	private DutchOffer offer;
	
	public void decreasePrice() {
		if(currentPrice > minPrice && (currentPrice - amount) >= currentPrice)
			currentPrice = currentPrice - amount;
	}
	
	@Override
	public void addOffer(Offer newOffer) throws IncompatibilityClassException {
		//TODO caso particolare
	}

	@Override
	public List<SQLOperation> getCloseOperation() {
		Transaction singleTransaction = new Transaction(offer.getBidder(), seller, currentPrice);
		
		return singleTransaction.getSQLOperations();
	}

	@Override
	public String getType() {
		return "Dutch";
	}

}
