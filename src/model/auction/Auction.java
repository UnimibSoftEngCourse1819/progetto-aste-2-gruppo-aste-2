package model.auction;

import java.util.List;

import exception.IncompatibilityClassException;
import model.Offer;
import model.Operation;
import model.user.User;

public abstract class Auction {
	
	protected User seller;
	
	public abstract void addOffer(Offer newOffer) throws IncompatibilityClassException;
	public abstract List<Operation> end();
}