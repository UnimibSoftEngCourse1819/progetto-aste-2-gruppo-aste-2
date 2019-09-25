package model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import controller.database.SQLOperation;
import controller.database.SQLParameter;
import controller.database.UpdateOperation;

public class Transaction implements Operation {

	private User donator;
	private User receiver;
	private long amount;
	
	public Transaction(User donator, User receiver, long amount) {
		this.donator = donator;
		this.receiver = receiver;
		this.amount = amount;
	}

	@Override
	public List<SQLOperation> getSQLOperations() {
		List<SQLOperation> operation = new ArrayList<>();
		
		LinkedHashMap<String, SQLParameter> clauses = new LinkedHashMap<>();
		clauses.put("ID", new SQLParameter(SQLParameter.INTEGER, donator));
		
		LinkedHashMap<String, SQLParameter> valuesToChange = new LinkedHashMap<>();
		valuesToChange.put("Portfolio", new SQLParameter(SQLParameter.INTEGER, donator.addAmount(-amount)));
		
		operation.add(new UpdateOperation("user", clauses, valuesToChange));
		
		LinkedHashMap<String, SQLParameter> clausesOfReceiver = new LinkedHashMap<>();
		clausesOfReceiver.put("ID", new SQLParameter(SQLParameter.INTEGER, receiver.getId()));
		
		LinkedHashMap<String, SQLParameter> valuesToChangeOfReceiver = new LinkedHashMap<>();
		valuesToChangeOfReceiver.put("Portfolio", new SQLParameter(SQLParameter.INTEGER, receiver.addAmount(amount)));
		
		operation.add(new UpdateOperation("user", clausesOfReceiver, valuesToChangeOfReceiver));
		
		return operation;
	}
}
