package model.auction.secondsealed;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import controller.database.SQLOperation;
import exception.InsufficientRequirementsException;
import exception.SQLiteFailRequestException;
import model.Offer;

public class SecondSealedOffer extends Offer implements Comparable<SecondSealedOffer> {
	public SecondSealedOffer(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public int compareTo(SecondSealedOffer other) {
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
		if (!(obj instanceof SecondSealedOffer)) {
			return false;
		}
		SecondSealedOffer other = (SecondSealedOffer) obj;
		return Objects.equals(bidder, other.bidder) && price == other.price;
	}

	@Override
	protected String getType() {
		return "SecondSealedOffer";
	}

	@Override
	public List<SQLOperation> getSQLOperation() throws SQLiteFailRequestException, InsufficientRequirementsException {
		List<SQLOperation> operations = new ArrayList<>();
		
		isValidOffer();
		
		operations.add(getSQLiteData());
		
		return operations;
	}
}
