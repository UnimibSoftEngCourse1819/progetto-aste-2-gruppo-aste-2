package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import controller.DatabaseManager;
import controller.database.ResultDatabase;
import controller.database.SQLOperation;
import controller.database.SQLParameter;
import controller.database.select.SelectComponent;
import controller.database.select.SimpleSelect;
import controller.database.utilformodel.SQLiteData;
import exception.FailRollBackException;
import exception.SQLiteFailRequestException;
import model.auction.Auction;

abstract class Notice implements Operation{
	
	protected static final String SQL_TABLE = "notice";
	protected LocalDateTime creation;
	protected int idWinner;
	protected int idAuction;
	
	public Notice(User winner, Auction auction) {
		idWinner = winner.getId();
		idAuction = auction.getId();
		creation = LocalDateTime.now();
	}

	@Override
	public List<SQLOperation> getSQLOperations() {
		List<SQLOperation> operations = new ArrayList<>();
		SQLiteData noticeTable = new SQLiteData(SQL_TABLE);
		
		noticeTable.add("message", SQLParameter.VARCHAR + "(255)", getMessage());
		noticeTable.add("Creation", SQLParameter.DATE_TIME, creation);
		operations.add(noticeTable);
		
		try {
			DatabaseManager.execute(operations);
			
			operations.clear();
			
			SelectComponent select = new SimpleSelect("newNotify",getMessage(), creation);
			ResultDatabase result = DatabaseManager.executeSelect(select);
			
			SQLiteData relationTable = new SQLiteData(SQL_TABLE);
			
			relationTable.add("user", SQLParameter.INTEGER,idWinner);
			relationTable.add(SQL_TABLE, SQLParameter.INTEGER, (Integer) result.getValue("ID", 0));
			
			operations.add(relationTable);
		} catch (SQLiteFailRequestException | FailRollBackException e) {
			operations.clear();
		}
		
		return operations;
	}
	
	public abstract String getMessage();
}
