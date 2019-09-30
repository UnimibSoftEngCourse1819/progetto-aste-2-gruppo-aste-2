package model.auction.firstsealed;


import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import model.Offer;

public class FirstSealedOffer extends Offer implements Comparable<FirstSealedOffer> {
	
	public FirstSealedOffer(HttpServletRequest request) {
		super(request);
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

	@Override
	protected String getType() {
		return "FirstSealedOffer";
	}
}