package controller;

import java.io.IOException;

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
		DatabaseManager.create(newAuction);

	}
}