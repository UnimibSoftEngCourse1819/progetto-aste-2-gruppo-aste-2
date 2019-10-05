package model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import controller.database.SQLOperation;
import controller.database.SQLParameter;
import controller.database.utilformodel.SQLiteData;
import controller.database.utilformodel.Storable;
import exception.InsufficientRequirementsException;
import exception.SQLiteFailRequestException;

public abstract class Offer implements Storable {
	protected static final String SQL_TABLE = "offer";
	protected User bidder;
	protected int auction;
	protected int basePrice;
	protected int price;
	
	protected Offer(HttpServletRequest request) {
		bidder = new User((int) request.getSession().getAttribute("id"));
		auction = Integer.parseInt(request.getParameter("auctionID"));
		price = Integer.parseInt(request.getParameter("price"));
		basePrice = Integer.parseInt(request.getParameter("BasePrice"));
	}
	
	public SQLiteData getSQLiteData() {
		SQLiteData sqlData = new SQLiteData(SQL_TABLE);
		
		sqlData.add("Offerer", SQLParameter.INTEGER, bidder.getId());
		sqlData.add("Auction", SQLParameter.INTEGER, auction);
		sqlData.add("Price", SQLParameter.INTEGER, price);
		
		return sqlData;
	}
	
	protected boolean isValidOffer() throws SQLiteFailRequestException{

		return price > bidder.getAviableCredit() || price < basePrice;
	}
	
	public abstract List<SQLOperation> getSQLOperation() throws SQLiteFailRequestException, InsufficientRequirementsException;
	protected abstract String getType();
	
	public User getBidder() {
		return bidder;
	}
	
	public int getPrice() {
		return price;
	}

}
