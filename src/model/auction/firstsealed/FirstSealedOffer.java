package model.auction.firstsealed;


import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import controller.database.SQLOperation;
import exception.InsufficientRequirementsException;
import exception.SQLiteFailRequestException;
import model.Offer;

public class FirstSealedOffer extends Offer {
	
	public FirstSealedOffer(HttpServletRequest request) {
		super(request);
	}


	@Override
	protected String getType() {
		return "FirstSealedOffer";
	}


	@Override
	public List<SQLOperation> getSQLOperation() throws SQLiteFailRequestException, InsufficientRequirementsException {
		List<SQLOperation> operations = new ArrayList<>();
		bidder.loadCredit();
		if(!isValidOffer()) {
			throw new InsufficientRequirementsException("Il prezzo inserito non soddisfa i requisiti");
		}
		
		operations.add(getSQLiteData());
		operations.add(getOngoingUpdate());
		return operations;
	}
}