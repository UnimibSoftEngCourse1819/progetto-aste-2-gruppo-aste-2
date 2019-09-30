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
		
		if(parameter.equalsIgnoreCase("firstSealedOffer")) {
			result =  new AuctionFirstSealed(request);
		}else if(parameter.equalsIgnoreCase("secondSealedOffer")) {
			result =  new AuctionSecondSealed(request);
		}else if(parameter.equalsIgnoreCase("englishOffer")) {
			result =  new EnglishAuction(request);
		}else if(parameter.equalsIgnoreCase("dutchOffer")) {
			result =  new DutchAuction(request);
		}else {
			throw new InexistentTypeParameterException();
		}
		return result;
	}
	
}
