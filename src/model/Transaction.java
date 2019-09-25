package model;

import java.util.ArrayList;
import java.util.List;

import controller.database.SQLOperation;
import controller.database.SQLParameter;
import controller.database.UpdateOperation;
import jdk.javadoc.internal.doclets.toolkit.util.Utils.Pair;

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
		
		List<Pair<String, SQLParameter>> clauses = new ArrayList<>();
		clauses.add(new Pair<String, SQLParameter>("ID", new SQLParameter(SQLParameter.INTEGER, donator)));
		
		List<Pair<String, SQLParameter>> valuesToChange = new ArrayList<>();
		valuesToChange.add(new Pair<String, SQLParameter>("Portfolio", new SQLParameter(SQLParameter.INTEGER, donator.addAmount(-amount))));
		
		operation.add(new UpdateOperation("user", clauses, valuesToChange));
		
		List<Pair<String, SQLParameter>> clausesOfReceiver = new ArrayList<>();
		clausesOfReceiver.add(new Pair<String, SQLParameter>("ID", new SQLParameter(SQLParameter.INTEGER, receiver.getId())));
		
		List<Pair<String, SQLParameter>> valuesToChangeOfReceiver = new ArrayList<>();
		valuesToChangeOfReceiver.add(new Pair<String, SQLParameter>("Portfolio", new SQLParameter(SQLParameter.INTEGER, receiver.addAmount(amount))));
		
		operation.add(new UpdateOperation("user", clausesOfReceiver, valuesToChangeOfReceiver));
		
		return operation;
	}
	
	
}
