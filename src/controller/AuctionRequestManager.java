package controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import controller.database.ResultDatabase;
import controller.database.SQLOperation;
import controller.database.select.SimpleSelect;
import controller.database.select.decorator.OrderBy;
import exception.FailRollBackException;
import exception.InexistentTypeParameterException;
import exception.InsufficientRequirementsException;
import exception.SQLiteFailRequestException;
import model.Offer;
import model.auction.Auction;

/**
 * This class manage the creation and the 
 * offers
 * 
 * @author Vallero
 *
 */
public class AuctionRequestManager {
	
	private AuctionRequestManager() {
		//This class has only static method
	}

	public static void createAuction(HttpServletRequest request, LinkedHashMap<String, String> values) throws SQLiteFailRequestException, InexistentTypeParameterException, IOException, FailRollBackException {
		Auction newAuction = AuctionFactory.createAuction(values.get("mod"), request, values);
		CategoryAuction categories = CategoryAuction.getInstance();
		
		List<String> auctionCategory = Arrays.asList(values.get("categories"));
		
		categories.addMissing(auctionCategory);
		
		DatabaseManager.create(newAuction);
		
		SimpleSelect selectAuction = new SimpleSelect("userAuctions", (int)request.getSession().getAttribute("id"));
		OrderBy orderedSelect = new OrderBy(selectAuction, "Creation");
		orderedSelect.setDesc(true);
		
		ResultDatabase result = DatabaseManager.executeSelect(orderedSelect);
		Integer idAuction = (int) result.getValue("ID", 0);
		
		DatabaseManager.execute(CategoryAuction.getSQLInsert(auctionCategory, idAuction));
	}
	
	public static void makeOffer(HttpServletRequest request) throws SQLiteFailRequestException, InexistentTypeParameterException, InsufficientRequirementsException, FailRollBackException {
		Offer newOffer = OfferFactory.createOffer(request.getParameter("mod"), request);	
		
		List<SQLOperation> operations = newOffer.getSQLOperation();
		DatabaseManager.execute(operations);
	}
}