package controller;

import javax.servlet.http.HttpServletRequest;

import exception.InexistentTypeParameterException;
import exception.SQLiteFailRequestException;
import model.auction.Auction;

public class AuctionRequestManager {

	public static void createAuction(HttpServletRequest request) throws SQLiteFailRequestException, InexistentTypeParameterException {
		Auction newAuction = AuctionFactory.createAuction(request.getParameter("mod"), request);
		DatabaseManager.create(newAuction);

	}

}