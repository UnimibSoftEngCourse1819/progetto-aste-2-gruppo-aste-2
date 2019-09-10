package model.auction.dutchauction;

import model.Offer;
import model.user.User;

public class DutchOffer implements Offer, Comparable<DutchOffer> {
	private User bidder;
	private long price;
	
	public DutchOffer(User bidder, long price) {
		this.bidder = bidder;
		this.price = price;
	}
	
	public User getBidder() {
		return bidder;
	}
	
	public long getPrice() {
		return price;
	}

	@Override
	public int compareTo(DutchOffer other) {
		int result = 0;
		
		if(price != other.price) {
			result = price > other.price ? 1 : -1;
		}
		
		return result;
	}

}
