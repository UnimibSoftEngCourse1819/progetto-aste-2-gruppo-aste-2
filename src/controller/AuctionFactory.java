package controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import exception.InexistentTypeParameterException;
import model.auction.Auction;
import model.auction.classic.Classic;
import model.auction.dutchauction.DutchAuction;
import model.auction.firstsealed.AuctionFirstSealed;
import model.auction.secondsealed.AuctionSecondSealed;

/**
 * A factory class for Auction
 * 
 * Simple Factory idiom applied
 *
 */
public class AuctionFactory {
	private AuctionFactory() {
		throw new IllegalStateException("Utility class");
	}

	public static Auction createAuction(String type, HttpServletRequest request, LinkedHashMap<String, String> values) throws InexistentTypeParameterException {
		Auction result = null;
		
		if(type.equalsIgnoreCase("FirstSealed")) {
			result =  new AuctionFirstSealed(request, values);
		}else if(type.equalsIgnoreCase("secondSealed")) {
			result =  new AuctionSecondSealed(request, values);
		}else if(type.equalsIgnoreCase("Classic")) {
			result =  new Classic(request);
		}else if(type.equalsIgnoreCase("dutchAuction")) {
			result =  new DutchAuction(request, values);
		}else {
			throw new InexistentTypeParameterException();
		}
		
		return result;
	}

	public static Auction getAuctionFromValues(Map<String, Object> rowValues) throws InexistentTypeParameterException {
		Auction result = null;
		String type = (String) rowValues.get("Type");
		
		if(type.equalsIgnoreCase("firstSealed")) {
			result =  new AuctionFirstSealed(rowValues);
		}else if(type.equalsIgnoreCase("secondSealed")) {
			result =  new AuctionSecondSealed(rowValues);
		}else if(type.equalsIgnoreCase("english")) {
			result =  new Classic(rowValues);
		}else if(type.equalsIgnoreCase("dutch")) {
			result =  new DutchAuction(rowValues);
		}else {
			throw new InexistentTypeParameterException();
		}
		
		return result;
	}
	
	
	
}
