package model.auction.dutchauction;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import controller.DatabaseManager;
import controller.database.ResultDatabase;
import controller.database.SQLOperation;
import controller.database.select.SelectComponent;
import controller.database.select.SimpleSelect;
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
		if(!isValidOffer()) {
			throw new InsufficientRequirementsException();
		}
		
		SelectComponent select = new SimpleSelect("auction", auction);
		ResultDatabase result = DatabaseManager.executeSelect(select);
		
		DucthAution auction = new DucthAution(result);
		
		
		return null;
	}
}
