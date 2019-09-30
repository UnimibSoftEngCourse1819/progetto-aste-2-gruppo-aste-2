package model;

import javax.servlet.http.HttpServletRequest;

import controller.database.SQLParameter;
import controller.database.utilformodel.SQLiteData;
import controller.database.utilformodel.Storable;

/**
 * Note : for compareTo -> -1 means the instance is less than parameter
 * @author Vallero
 *
 */
public abstract class Offer implements Storable {
	protected static final String SQL_TABLE = "offer";
	protected User bidder;
	protected int auction;
	protected int price;
	
	protected Offer(HttpServletRequest request) {
		bidder = new User((int) request.getSession().getAttribute("id"));
		auction = Integer.parseInt(request.getParameter("auctionID"));
		price = Integer.parseInt(request.getParameter("price"));
	}
	
	public SQLiteData getSQLiteData() {
		SQLiteData sqlData = new SQLiteData(SQL_TABLE);
		
		sqlData.add("Offerer", SQLParameter.INTEGER, bidder.getId());
		sqlData.add("Auction", SQLParameter.INTEGER, auction);
		sqlData.add("Price", SQLParameter.INTEGER, price);
		
		return sqlData;
	}
	
	protected abstract String getType();
	
	public User getBidder() {
		return bidder;
	}
	
	public long getPrice() {
		return price;
	}
}
