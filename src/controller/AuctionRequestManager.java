package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import exception.InexistentTypeParameterException;
import exception.SQLiteFailRequestException;
import model.auction.Auction;

public class AuctionRequestManager {
	
	private AuctionRequestManager() {
		//This class has only static method
	}

	public static void createAuction(HttpServletRequest request) throws SQLiteFailRequestException, InexistentTypeParameterException, IOException {
		Auction newAuction = AuctionFactory.createAuction(request.getParameter("mod"), request);
		CategoryAuction categories = CategoryAuction.getInstance();
		List<String> auctionCategory = new ArrayList<>(); 
		//TODO qualcosa che prenda tutte le stringe delle categorie e li mette su auctionCategory
		categories.addMissing(auctionCategory);
		
		DatabaseManager.create(newAuction);
		DatabaseManager.create(auctionCategory.getSQLInsert(auctionCategory));
	}
}