package model.auction.dutchauction;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import exception.IncompatibilityClassException;
import model.Offer;
import model.Operation;
import model.Transaction;
import model.auction.Auction;

public class DutchAuction extends Auction {
	public DutchAuction(HttpServletRequest request) {
		super(request);
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
		//TODO da decidere quando verrà implementato le pagine per le offerte
	}

	@Override
	public List<Operation> end() {
		List<Operation> operationToDo = new ArrayList<>();
		operationToDo.add(new Transaction(offer.getBidder(), seller, currentPrice));
		
		return operationToDo;
	}

	@Override
	protected String getType() {
		return "Dutch";
	}

}
