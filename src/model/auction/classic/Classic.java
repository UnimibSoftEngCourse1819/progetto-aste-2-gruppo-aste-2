package model.auction.classic;

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

public class Classic extends Auction {
	private static final Logger LOGGER = MyLogger.getLoggerInstance(Classic.class.getName());
	public Classic(HttpServletRequest request) {
		super(request);
	}

	public Classic(Map<String, Object> rowValues) {
		super(rowValues);
	}

	@Override
	public List<SQLOperation> getCloseOperation() {
		List<SQLOperation> operationToDo = new ArrayList<>();

		SimpleSelect select = new SimpleSelect("auctionOffers", id);
		OrderBy orderedSelect = new OrderBy(select, "Price");
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
				Transaction transaction = new Transaction(buyer, seller,
						(Integer) result.getValue("Price", 0));

				operationToDo.addAll(transaction.getSQLOperations());

				valueToChange.put("Winner", new SQLParameter(SQLParameter.INTEGER, result.getValue("IDBuyer", 0)));
			}

			operationToDo.add(new UpdateOperation("auction", clauses, valueToChange));
		} catch (SQLiteFailRequestException e) {
			LOGGER.log(Level.SEVERE, "Non � stato possibile generare le operazioni da eseguire sul database", e);
		}

		return operationToDo;
	}

	@Override
	public String getType() {
		return "Classic";
	}

}
