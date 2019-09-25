package model.auction.firstsealed;


import java.util.Objects;

import model.Offer;
import model.User;

public class FirstSealedOffer implements Offer, Comparable<FirstSealedOffer>{
	private User bidder;
	private long price;
	
	public FirstSealedOffer(User bidder, long price) {
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
	public int compareTo(FirstSealedOffer other) {
		int result = 0;
		if(price != other.price) {
			result = price > other.price ? 1 : -1;
		}
		return result;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bidder, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof FirstSealedOffer)) {
			return false;
		}
		FirstSealedOffer other = (FirstSealedOffer) obj;
		return Objects.equals(bidder, other.bidder) && price == other.price;
	}
}