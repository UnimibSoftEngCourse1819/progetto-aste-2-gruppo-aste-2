package model.auction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import controller.ImageUploader;
import controller.database.SQLOperation;
import controller.database.SQLParameter;
import controller.database.utilformodel.SQLiteData;
import controller.database.utilformodel.Storable;
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
	protected int basePrice;
	protected String image;
	
	protected Auction(HttpServletRequest request) {
		seller = new User((int)request.getSession().getAttribute("id")); 
		title = request.getParameter("auctionTitle");
		description = request.getParameter("auctionDescription");
		creation = LocalDateTime.now();
		status = STANDBY;
		basePrice = Integer.parseInt(request.getParameter("basePrice"));
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		formatter = formatter.withLocale(Locale.getDefault());
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
	
	protected Auction(HttpServletRequest request, LinkedHashMap<String, String> values) {
		seller = new User((int) request.getSession().getAttribute("id"));
		title = values.get("auctionTitle");	
		description = values.get("auctionDescription");
		creation = LocalDateTime.now();
		status = STANDBY;
		basePrice = Integer.parseInt(values.get("basePrice"));
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		formatter = formatter.withLocale(Locale.getDefault());
		LocalDate tempDate = LocalDate.parse(values.get("date"), formatter);
		LocalTime tempTime = LocalTime.parse(values.get("time"));
		ending = LocalDateTime.of(tempDate, tempTime);
		
		image = ImageUploader.getFileName();
		
		if(values.get("refund").equals("")) {
			penalty = 0;
		}
		else {
			penalty = 1;
		}
	}
	
	public Auction(Map<String, Object> rowValues) {
		id = (Integer) rowValues.get("ID"); 
		seller = new User((Integer) rowValues.get("Seller")); 
		title = (String) rowValues.get("Title");
		description = (String) rowValues.get("Description");
		creation = (LocalDateTime) rowValues.get("Creation");
		ending = (LocalDateTime) rowValues.get("Conclusion");
		status = (String) rowValues.get("Status");
		penalty = (Integer) rowValues.get("Penalty");
		basePrice = (Integer) rowValues.get("BasePrice");
		image = (String) rowValues.get("Image");
	}

	public Auction(int auction) {
		id = auction;
	}

	public String getTitle() {
		return title;
	}
	
	public int getId() {
		return id;
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
		sqlData.add("Penalty", SQLParameter.INTEGER, penalty);
		sqlData.add("Status", SQLParameter.VARCHAR + "(20)", status);
		sqlData.add("BasePrice", SQLParameter.INTEGER, basePrice);
		sqlData.add("Image", SQLParameter.VARCHAR, image);
		
		return sqlData;
	}
	
	public abstract List<SQLOperation> getCloseOperation();
	public abstract String getType();
}