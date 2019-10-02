package model.auction.secondsealed;

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
import model.Operation;
import model.Transaction;
import model.User;
import model.auction.Auction;

public class AuctionSecondSealed extends Auction {

	public AuctionSecondSealed(HttpServletRequest request) {
		super(request);
	}

	public AuctionSecondSealed(Map<String, Object> rowValues) {
		super(rowValues);
	}

	private List<SecondSealedOffer> offers;

	@Override
	public void addOffer(Offer newOffer) throws IncompatibilityClassException {
		if(!newOffer.getClass().equals(SecondSealedOffer.class)) {
			throw new IncompatibilityClassException();
		}
		
		offers.add((SecondSealedOffer) newOffer);
	}

	@Override
	public List<SQLOperation> getCloseOperation() {
		List<SQLOperation> operationToDo = new ArrayList<>();
		
		SimpleSelect select = new SimpleSelect("auctionOffers", id);
		OrderBy orderedSelect = new OrderBy(select, "Price");
		orderedSelect.setDesc(true);
		
		ResultDatabase result;
		
		try {
			result = DatabaseManager.executeSelect(orderedSelect);
			
			Transaction transaction = new Transaction(
					new User((Integer) result.getValue("Offerer", 0)),
					new User((Integer) result.getValue("Seller", 0)),
					(Long) result.getValue("Price", 1)
			);
			
			operationToDo.addAll(transaction.getSQLOperations());
			
			LinkedHashMap<String, SQLParameter> clauses = new LinkedHashMap<>();
			clauses.put("ID", new SQLParameter(SQLParameter.INTEGER, id));
			
			LinkedHashMap<String, SQLParameter> valueToChange = new LinkedHashMap<>();
			valueToChange.put("Status", new SQLParameter(SQLParameter.VARCHAR, ENDED));
			
			operationToDo.add(new UpdateOperation("auction", clauses, valueToChange));
		}
		catch(SQLiteFailRequestException e) {
			e.printStackTrace();
		}
		
		return operationToDo;
		
	}

	@Override
	public String getType() {
		return "SecondSealed";
	}
	
	
}
