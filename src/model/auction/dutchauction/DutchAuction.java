package model.auction.dutchauction;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import controller.DatabaseManager;
import controller.database.ResultDatabase;
import controller.database.SQLOperation;
import controller.database.SQLParameter;
import controller.database.UpdateOperation;
import controller.database.select.SimpleSelect;
import controller.database.select.decorator.OrderBy;
import exception.IncompatibilityClassException;
import exception.SQLiteFailRequestException;
import model.Offer;
import model.Transaction;
import model.User;
import model.auction.Auction;

public class DutchAuction extends Auction {
	
	public DutchAuction(HttpServletRequest request) {
		super(request);
	}

	public DutchAuction(Map<String, Object> rowValues) {
		super(rowValues);
	}

	public DutchAuction(HttpServletRequest request, LinkedHashMap<String, String> values) {
		super(request, values);
	}

	private long minPrice;
	private long currentPrice;
	private long amount;
	private DutchOffer offer;
	
	public void decreasePrice() {
		if(currentPrice > minPrice && (currentPrice - amount) >= currentPrice)
			currentPrice = currentPrice - amount;
	}

	@Override
	public List<SQLOperation> getCloseOperation() {
		List<SQLOperation> operationToDo = new ArrayList<>();

		SimpleSelect select = new SimpleSelect("auctionOffers", id);

		try {
			ResultDatabase result = DatabaseManager.executeSelect(select);

			User buyer = new User((Integer) result.getValue("IDBuyer", 0));
			buyer.loadCredit();

			User seller = new User((Integer) result.getValue("IDSeller", 0));
			buyer.loadCredit();

			LinkedHashMap<String, SQLParameter> valueToChange = new LinkedHashMap<>();
			valueToChange.put("Status", new SQLParameter(SQLParameter.VARCHAR, ENDED));

			LinkedHashMap<String, SQLParameter> clauses = new LinkedHashMap<>();
			clauses.put("ID", new SQLParameter(SQLParameter.INTEGER, id));


			if(!result.isEmpty()) {
				Transaction transaction = new Transaction(buyer, seller,
						(Integer) result.getValue("Price", 0));

				operationToDo.addAll(transaction.getSQLOperations());

				valueToChange.put("Winner", new SQLParameter(SQLParameter.INTEGER, result.getValue("IDBuyer", 0)));
			}

			operationToDo.add(new UpdateOperation("auction", clauses, valueToChange));
		} catch (SQLiteFailRequestException e) {
			e.printStackTrace();
		}
		
		return operationToDo;
	}

	@Override
	public String getType() {
		return "Dutch";
	}

}
