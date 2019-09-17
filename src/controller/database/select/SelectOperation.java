package controller.database.select;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import controller.database.SQLOperation;

public class SelectOperation implements SQLOperation {

	private List<ColumnSelect> columns;
	private String fromTable;
	private List<SelectClause> clauses;
	
	public SelectOperation(List<ColumnSelect> columns, String fromTable, SelectClause... clauses) {
		this.columns = columns;
		this.fromTable = fromTable;
		this.clauses =  new ArrayList<>();
		for(SelectClause singleClause : clauses) {
			this.clauses.add(singleClause);
		}
	}

	/**
	 * There is no check  
	 * so you will get a SQLException when you
	 * will execute the statement for these reasons :
	 * 1) the clauses aren't in the correct order
	 * 2) you trying to use a field without joint his table
	 * 
	 */
	@Override
	public String getStatement() {
		final StringBuilder statement = new StringBuilder(" SELECT ");

		if(columns.isEmpty()) {
			statement.append(" * ");
		}else {
			Iterator<ColumnSelect> indexColumn = columns.iterator();
			while(indexColumn.hasNext()) {
				ColumnSelect singleColumn= indexColumn.next();
				statement.append(singleColumn.getSQLText());
				
				if(indexColumn.hasNext()) {
					statement.append(", ");
				}
			}
		}
		
		statement.append(" FROM " + fromTable);
		
		clauses.stream()
			.forEach(singleClause -> statement.append(singleClause.getSQLText()));

		statement.append(";");
		return statement.toString();
	}

	@Override
	public void configure(PreparedStatement statement) throws SQLException {
		
	}

}
