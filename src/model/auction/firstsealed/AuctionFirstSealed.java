package model.auction.firstsealed;

import java.util.ArrayList;
import java.util.List;

import exception.IncompatibilityClassException;
import model.Offer;
import model.Operation;
import model.auction.Auction;

public class AuctionFirstSealed extends Auction {
	
	private User seller;
	private List<FirstSealedOffer> offers;
	
	@Override
	public void addOffer(Offer newOffer) {
		if(!newOffer.getClass().equals(FirstSealedOffer.class)) {
			throw new IncompatibilityClassException();
		}
		
		offers.add((FirstSealedOffer) newOffer);
s
	}
	
	@Override
	public  List<Operation> end(){
		FirstSealedOffer winner = offers.get(0);
		for(FirstSealedOffer offerToScan : offers) {
			if(offerToScan.compareTo(winner) > 0) {
				winner = offerToScan;
			}
		}
		List<model.Operation> operationToDo = new ArrayList<>();
		operationToDo.add(new Transiction(winner.getBidder(), seller, winner.getPrice()));
		return operationToDo;
	}
}
