package controller;

import javax.servlet.http.HttpServletRequest;

import exception.InexistentTypeParameterException;
import model.auction.Auction;
import model.auction.dutchauction.DutchAuction;
import model.auction.englishauction.EnglishAuction;
import model.auction.firstsealed.AuctionFirstSealed;
import model.auction.secondsealed.AuctionSecondSealed;

/**
 * Simple Factory idiom applied
 * @author Vallero
 *
 */
public class AuctionFactory {
	private AuctionFactory() {
		throw new IllegalStateException("Utility class");
	}

	public static Auction createAuction(String parameter, HttpServletRequest request) throws InexistentTypeParameterException {
		Auction result = null;
		
		if(parameter.equalsIgnoreCase("firstSealed")) {
			result =  new AuctionFirstSealed(request);
		}else if(parameter.equalsIgnoreCase("secondSealed")) {
			result =  new AuctionSecondSealed(request);
		}else if(parameter.equalsIgnoreCase("englishAuction")) {
			result =  new EnglishAuction(request);
		}else if(parameter.equalsIgnoreCase("dutchAuction")) {
			result =  new DutchAuction(request);
		}else {
			throw new InexistentTypeParameterException();
		}
		
		return result;
	}
	
}
