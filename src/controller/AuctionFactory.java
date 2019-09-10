package controller;

import javax.servlet.http.HttpServletRequest;

import exception.InexistentTypeParameterException;
import model.auction.Auction;
import model.auction.firstsealed.AuctionFirstSealed;
import model.auction.secondSealed.AuctionSecondSealed;

/**
 * Simple Factory idiom applied
 * @author Vallero
 *
 */
public class AuctionFactory {

	public static Auction createAuction(String parameter, HttpServletRequest request) {
		Auction result = null;
		if(parameter.equalsIgnoreCase("Busta chiusa")) {
			result =  new AuctionFirstSealed(request);
		}else if(parameter.equalsIgnoreCase("Busta chiusa a secondo prezzo")) {
			result =  new AuctionSecondSealed(request);
		}else if(parameter.equalsIgnoreCase("Asta inglese")) {
			result =  new EnglishAuction(request);
		}else if(parameter.equalsIgnoreCase("Asta olandese")) {
			result =  new DutchAuction(request);
		}else {
			throw new InexistentTypeParameterException();
		}
		return result;
	}
	
}
