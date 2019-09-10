package model.auction;

import java.util.List;

import controller.database.Storable;
import exception.IncompatibilityClassException;
import model.Offer;
import model.Operation;
import model.user.User;

public abstract class Auction implements Storable{
	
	protected User seller;
	
	public abstract void addOffer(Offer newOffer) throws IncompatibilityClassException;
	public abstract List<Operation> end();
}