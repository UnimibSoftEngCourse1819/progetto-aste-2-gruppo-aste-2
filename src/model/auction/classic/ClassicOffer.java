package model.auction.classic;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import controller.DatabaseManager;
import controller.database.ResultDatabase;
import controller.database.SQLOperation;
import controller.database.select.SimpleSelect;
import controller.database.select.decorator.OrderBy;
import exception.InsufficientRequirementsException;
import exception.SQLiteFailRequestException;
import model.Offer;

public class ClassicOffer extends Offer{	
	public ClassicOffer(HttpServletRequest request) {
		super(request);
	}

	@Override
	protected String getType() {
		return ("ClassicOffer");
	}

	@Override
	public List<SQLOperation> getSQLOperation() throws SQLiteFailRequestException, InsufficientRequirementsException {
		SimpleSelect select = new SimpleSelect("auctionOffers", auction);
		OrderBy orderedSelect = new OrderBy(select, "Price");
		orderedSelect.setDesc(true);
		
		ResultDatabase result = DatabaseManager.executeSelect(select);
		if((Integer) result.getValue("Price", 0) > price || !isValidOffer()) {
			throw new InsufficientRequirementsException("Il prezzo inserito non soddisfa i requisiti");
		}
		
		List<SQLOperation> operations = new ArrayList<>();
		operations.add(getSQLiteData());
		operations.add(getOngoingUpdate()); 
		return operations;
	}	
}
