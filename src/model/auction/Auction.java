package model.auction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import controller.database.SQLParameter;
import controller.database.utilformodel.SQLiteData;
import controller.database.utilformodel.Storable;
import exception.IncompatibilityClassException;
import model.Offer;
import model.Operation;
import model.User;

/**
 * Template Design Patter Applied
 * 
 * @author Vallero
 *
 */
public abstract class Auction implements Storable{
	
	protected static final String SQL_TABLE = "auction";
	public static final String STANDBY = "STANDBY";
	public static final String ON_GOING = "ON_GOING";
	public static final String ENDED = "END";
	protected User seller;
	protected int id;
	protected String title;
	protected String description;
	protected LocalDateTime creation;
	protected LocalDateTime ending;
	protected String status;
	protected int penalty;
	
	protected Auction(HttpServletRequest request) {
		seller = new User((int)request.getSession().getAttribute("id")); 
		title = request.getParameter("auctionTitle");
		description = request.getParameter("auctionDescription");
		creation = LocalDateTime.now();
		status = STANDBY;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		formatter = formatter.withLocale(Locale.getDefault());
		String data = request.getParameter("date");
		LocalDate tempDate = LocalDate.parse(request.getParameter("date"), formatter);
		LocalTime tempTime = LocalTime.parse(request.getParameter("time"));
		ending = LocalDateTime.of(tempDate, tempTime);
		
		if(request.getParameter("refund").equals("")) {
			penalty = 0;
		}
		else {
			penalty = 1;
		}
	}
	
	@Override
	public SQLiteData getSQLiteData() {
		SQLiteData sqlData = new SQLiteData(SQL_TABLE);
		
		sqlData.add("Seller", SQLParameter.INTEGER, seller.getId());
		sqlData.add("Title", SQLParameter.VARCHAR + "(25)", title);
		sqlData.add("Description", SQLParameter.VARCHAR + "(255)", description);
		sqlData.add("Creation", SQLParameter.DATE_TIME, creation);
		sqlData.add("Conclusion", SQLParameter.DATE_TIME, ending);
		sqlData.add("Type", SQLParameter.VARCHAR + "(30)", getType());
		sqlData.add("penalty", SQLParameter.INTEGER, penalty);
		sqlData.add("Status", SQLParameter.VARCHAR + "(20)", status);
		
		return sqlData;
	}
	
	public abstract void addOffer(Offer newOffer) throws IncompatibilityClassException;
	public abstract List<Operation> end();
	protected abstract String getType();
}