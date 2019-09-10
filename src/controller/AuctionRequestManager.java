package controller;

import javax.servlet.http.HttpServletRequest;

import model.auction.Auction;

public class AuctionRequestManager {

	public static void createAuction(HttpServletRequest request) {
		try {
			Auction newAuction = AuctionFactory.createAuction(request.getParameter("mod"), request);
			DatabaseManager.create(newAuction);
		}
		
	}

}