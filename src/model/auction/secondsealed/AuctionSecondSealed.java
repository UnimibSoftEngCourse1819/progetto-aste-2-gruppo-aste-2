package model.auction.secondsealed;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import controller.DatabaseManager;
import controller.MyLogger;
import controller.database.ResultDatabase;
import controller.database.SQLOperation;
import controller.database.SQLParameter;
import controller.database.UpdateOperation;
import controller.database.select.SimpleSelect;
import controller.database.select.decorator.OrderBy;
import exception.SQLiteFailRequestException;
import model.Transaction;
import model.User;
import model.auction.Auction;

public class AuctionSecondSealed extends Auction {
	private final String PRICE = "Price";
	private static final Logger LOGGER = MyLogger.getLoggerInstance(AuctionSecondSealed.class.getName());
	public AuctionSecondSealed(HttpServletRequest request) {
		super(request);
	}

	public AuctionSecondSealed(Map<String, Object> rowValues) {
		super(rowValues);
	}

	public AuctionSecondSealed(HttpServletRequest request, LinkedHashMap<String, String> values) {
		super(request, values);
	}

	@Override
	public  List<SQLOperation> getCloseOperation(){
		List<SQLOperation> operationToDo = new ArrayList<>();
		
		SimpleSelect select = new SimpleSelect("auctionOffers", id);
		OrderBy orderedSelect = new OrderBy(select, PRICE);
		orderedSelect.setDesc(true);
		
		try {
			ResultDatabase result = DatabaseManager.executeSelect(orderedSelect);
			
			User buyer = new User((Integer) result.getValue("IDBuyer", 0));
			buyer.loadCredit();

			User seller = new User((Integer) result.getValue("IDSeller", 0));
			buyer.loadCredit();
			
			LinkedHashMap<String, SQLParameter> valueToChange = new LinkedHashMap<>();
			valueToChange.put("Status", new SQLParameter(SQLParameter.VARCHAR, ENDED));
			
			LinkedHashMap<String, SQLParameter> clauses = new LinkedHashMap<>();
			clauses.put("ID", new SQLParameter(SQLParameter.INTEGER, id));
			
			if(!result.isEmpty()) {
				
				Integer price = result.size() >= 2 ? (Integer) result.getValue(PRICE, 1) : (Integer) result.getValue(PRICE, 0);
				
				Transaction transaction = new Transaction(buyer, seller, price);
				
				operationToDo.addAll(transaction.getSQLOperations());
				
				valueToChange.put("Winner", new SQLParameter(SQLParameter.INTEGER, buyer));
			}
			
			operationToDo.add(new UpdateOperation("auction", clauses, valueToChange));
		} catch (SQLiteFailRequestException e) {
			LOGGER.log(Level.SEVERE, "Non è stato possibile generare le operazioni da eseguire sul database", e);
		}
		
		return operationToDo;
	}
	
	@Override
	public String getType() {
		return "SecondSealed";
	}
	
	
}
