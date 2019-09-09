package model.auction;

import java.util.List;

import exception.IncompatibilityClassException;
import model.Offer;
import model.Operation;

public abstract class Auction {
	public abstract void addOffer(Offer newOffer) throws IncompatibilityClassException;
	public abstract List<Operation> end();
}