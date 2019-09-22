package model.auction.secondsealed;

import model.Offer;
import model.User;

public class SecondSealedOffer implements Offer, Comparable<SecondSealedOffer> {
	private User bidder;
	private long price;
	
	public SecondSealedOffer(User bidder, long price) {
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
	public int compareTo(SecondSealedOffer other) {
		int result = 0;
		if(price != other.price) {
			result = price > other.price ? 1 : -1;
		}
		return result;
	}

}
