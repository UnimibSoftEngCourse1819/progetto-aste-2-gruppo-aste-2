package model.auction.dutchauction;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import controller.database.SQLOperation;
import exception.InsufficientRequirementsException;
import exception.SQLiteFailRequestException;
import model.Offer;

public class DutchOffer extends Offer{
	
	public static String nameType = "DutchOffer";
	
	public DutchOffer(HttpServletRequest request) {
		super(request);
	}

	@Override
	protected String getType() {
		return "DucthOffer";
	}

	@Override
	public List<SQLOperation> getSQLOperation() throws SQLiteFailRequestException, InsufficientRequirementsException {
		// TODO Auto-generated method stub
		return null;
	}
}
