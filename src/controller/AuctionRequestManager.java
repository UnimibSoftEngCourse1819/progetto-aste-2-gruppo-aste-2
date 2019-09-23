package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import controller.database.ResultDatabase;
import controller.database.select.SimpleSelect;
import controller.database.select.decorator.OrderBy;
import exception.FailRollBackException;
import exception.InexistentTypeParameterException;
import exception.SQLiteFailRequestException;
import model.auction.Auction;

public class AuctionRequestManager {
	
	private AuctionRequestManager() {
		//This class has only static method
	}

	public static void createAuction(HttpServletRequest request) throws SQLiteFailRequestException, InexistentTypeParameterException, IOException, FailRollBackException {
		Auction newAuction = AuctionFactory.createAuction(request.getParameter("mod"), request);
		CategoryAuction categories = CategoryAuction.getInstance();
		
		List<String> auctionCategory = Arrays.asList(request.getParameterValues("categories[]"));
		
		categories.addMissing(auctionCategory);
		
		DatabaseManager.create(newAuction);
		
		SimpleSelect selectAuction = new SimpleSelect("userAuctions", (int)request.getSession().getAttribute("id"));
		OrderBy orderedSelect = new OrderBy(selectAuction, "Creation");
		orderedSelect.setDesc(true);
		
		ResultDatabase result = DatabaseManager.executeSelect(orderedSelect);
		Integer idAuction = (int)result.getValue("ID", 0);
		
		DatabaseManager.execute(CategoryAuction.getSQLInsert(auctionCategory, idAuction));
	}
}