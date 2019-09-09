package model;

import model.user.User;

public class Transaction extends Operation {

	private User donator;
	private User receiver;
	private long amount;
	
	public Transaction(User donator, User receiver, long amount) {
		this.donator = donator;
		this.receiver = receiver;
		this.amount = amount;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
	}

}
