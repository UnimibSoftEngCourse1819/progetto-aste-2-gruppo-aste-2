package model.auction.englishauction;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import controller.database.SQLOperation;
import exception.InsufficientRequirementsException;
import exception.SQLiteFailRequestException;
import model.Offer;

public class EnglishOffer extends Offer{	
	public EnglishOffer(HttpServletRequest request) {
		super(request);
	}

	@Override
	protected String getType() {
		return ("EnglishOffer");
	}


	@Override
	public List<SQLOperation> getSQLOperation() throws SQLiteFailRequestException, InsufficientRequirementsException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
