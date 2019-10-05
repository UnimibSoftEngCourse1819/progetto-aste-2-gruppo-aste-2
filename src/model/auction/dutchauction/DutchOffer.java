package model.auction.dutchauction;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import controller.database.SQLOperation;
import exception.InsufficientRequirementsException;
import exception.SQLiteFailRequestException;
import model.Offer;

public class DutchOffer extends Offer implements Comparable<DutchOffer> {
	
	public static String nameType = "DutchOffer";
	
	public DutchOffer(HttpServletRequest request) {
		super(request);
	}

	@Override
	public int compareTo(DutchOffer other) {
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
		
		if (!(obj instanceof DutchOffer)) {
			return false;
		}
		
		DutchOffer other = (DutchOffer) obj;
		
		return Objects.equals(bidder, other.bidder) && price == other.price;
	}

	@Override
	protected String getType() {
		return "DucthOffer";
	}

	@Override
	public List<SQLOperation> getSQLOperation() throws SQLiteFailRequestException, InsufficientRequirementsException {
		// TODO Auto-generated method stub
		return null;
	}

}
