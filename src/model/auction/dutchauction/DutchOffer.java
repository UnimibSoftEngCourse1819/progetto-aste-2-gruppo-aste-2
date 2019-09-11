package model.auction.dutchauction;

import model.Offer;
import model.user.User;

public class DutchOffer implements Offer{
	private User bidder;
	
	public DutchOffer(User bidder) {
		this.bidder = bidder;
	}
	
	public User getBidder() {
		return bidder;
	}

}
