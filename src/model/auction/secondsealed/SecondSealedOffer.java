package model.auction.secondsealed;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import controller.database.SQLOperation;
import exception.InsufficientRequirementsException;
import exception.SQLiteFailRequestException;
import model.Offer;

public class SecondSealedOffer extends Offer{
	public SecondSealedOffer(HttpServletRequest request) {
		super(request);
	}

	@Override
	protected String getType() {
		return "SecondSealedOffer";
	}

	@Override
	public List<SQLOperation> getSQLOperation() throws SQLiteFailRequestException, InsufficientRequirementsException {
		List<SQLOperation> operations = new ArrayList<>();
		
		isValidOffer();
		
		operations.add(getSQLiteData());
		
		return operations;
	}
}
