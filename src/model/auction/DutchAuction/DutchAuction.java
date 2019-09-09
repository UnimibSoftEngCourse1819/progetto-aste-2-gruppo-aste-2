package model.auction.DutchAuction;

import java.util.List;

import exception.IncompatibilityClassException;
import model.Offer;
import model.Operation;
import model.auction.Auction;

public class DutchAuction extends Auction {
	private long currentPrice;
	
	@Override
	public void addOffer(Offer newOffer) throws IncompatibilityClassException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Operation> end() {
		// TODO Auto-generated method stub
		return null;
	}

}
