package model.auction;

import java.util.List;

import model.Offer;
import model.Operation;

public abstract class Auction {
	public abstract void addOffer(Offer newOffer);
	public abstract List<Operation> end();
}