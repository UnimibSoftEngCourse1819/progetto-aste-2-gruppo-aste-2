package model;

import model.auction.Auction;

public class WinNotification extends Notice{

	private String message;
	
	public WinNotification(User winner, Auction auction) {
		super(winner, auction);
		message = "Complimenti " + winner.getName() + ", \n";
		message += "hai vinto l'asta : \"" + auction.getTitle() + "\"!";
	}

	@Override
	public String getMessage() {
		return message;
	}
}
