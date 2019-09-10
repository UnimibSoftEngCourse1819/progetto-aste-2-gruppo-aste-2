package model.auction.DutchAuction;

import java.util.List;

import exception.IncompatibilityClassException;
import model.Offer;
import model.Operation;
import model.auction.Auction;

public class DutchAuction extends Auction {
	private long minPrice;
	private long currentPrice;
	private long amount;
	private DutchOffer offer;
	
	public void adjudicate(User bidder) {
		offer = new DutchOffer(bidder, currentPrice);
	}
	
	public void decreasePrice() {
		if(currentPrice > minPrice && (currentPrice - amount) >= currentPrice)
			currentPrice = currentPrice - amount;
	}
	
	@Override
	public void addOffer(Offer newOffer) throws IncompatibilityClassException {	}

	@Override
	public List<Operation> end() {
		List<Operation> operationToDo = new ArrayList<>();
		operationToDo.add(new Transaction(offer.getBidder(), seller, currentPrice()));
		
		return operationToDo;
	}

}
