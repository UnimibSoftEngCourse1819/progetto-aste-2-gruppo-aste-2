package model.auction.englishauction;

import model.Offer;
import model.User;

public class EnglishOffer implements Offer, Comparable<EnglishOffer> {
	private User bidder;
	private long price;
	
	public EnglishOffer(User bidder, long price) {
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
	public int compareTo(EnglishOffer other) {
		int result = 0;
		if(price != other.price) {
			result = price > other.price ? 1 : -1;
		}
		return result;
	}
	
	
}
