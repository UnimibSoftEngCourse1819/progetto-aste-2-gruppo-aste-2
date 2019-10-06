package controller;

import javax.servlet.http.HttpServletRequest;

import exception.InexistentTypeParameterException;
import model.Offer;
import model.auction.classic.ClassicOffer;
import model.auction.dutchauction.DutchOffer;
import model.auction.firstsealed.FirstSealedOffer;
import model.auction.secondsealed.SecondSealedOffer;

/**
 * A factory class for Offer
 * 
 * Simple Factory idiom applied
 *
 */

public class OfferFactory {
	private OfferFactory() {
		throw new IllegalStateException("Utility class");
	}
	
	public static Offer createOffer(String parameter, HttpServletRequest request) throws InexistentTypeParameterException {
		Offer result = null;
		
		if(parameter.equalsIgnoreCase("firstSealed")) {
			result =  new FirstSealedOffer(request);
		}else if(parameter.equalsIgnoreCase("secondSealed")) {
			result =  new SecondSealedOffer(request);
		}else if(parameter.equalsIgnoreCase("englishAuction")) {
			result =  new ClassicOffer(request);
		}else if(parameter.equalsIgnoreCase("dutchAuction")) {
			result =  new DutchOffer(request);
		}else {
			throw new InexistentTypeParameterException("Tipologia asta non esistente");
		}
		
		return result;
	}
}
