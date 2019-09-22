package model.auction;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import controller.database.SQLParameter;
import controller.database.utilformodel.SQLiteData;
import controller.database.utilformodel.Storable;
import exception.IncompatibilityClassException;
import model.Offer;
import model.Operation;
import model.user.User;

/**
 * Template Design Patter Applied
 * 
 * @author Vallero
 *
 */
public abstract class Auction implements Storable{
	
	protected static final String SQL_TABLE = "auction";
	protected User seller;
	protected int id;
	protected String title;
	protected String description;
	protected LocalDate creation;
	protected int penalty;
	
	protected Auction(HttpServletRequest request) {
		//TODO qualcosa che capisca chi � l'utente
		id = (int) request.getSession().getAttribute("id");
		title = request.getParameter("auctionTitle");
		description = request.getParameter("auctionDescription");
		creation = LocalDate.now();
		if(request.getParameter("refund").equals(""))
			penalty = 0;
		else
			penalty = 1;
	}
	
	@Override
	public SQLiteData getSQLiteData() {
		SQLiteData sqlData = new SQLiteData(SQL_TABLE);
		sqlData.add("Seller", SQLParameter.INTEGER, id);
		sqlData.add("Title", SQLParameter.VARCHAR + "(25)", title);
		sqlData.add("Description", SQLParameter.VARCHAR + "(255)", description);
		sqlData.add("Creation", SQLParameter.DATE, creation);
		sqlData.add("Type", SQLParameter.VARCHAR + "(30)", getType());
		//TODO missing penalty
		return sqlData;
	}
	
	public abstract void addOffer(Offer newOffer) throws IncompatibilityClassException;
	public abstract List<Operation> end();
	protected abstract String getType();
}