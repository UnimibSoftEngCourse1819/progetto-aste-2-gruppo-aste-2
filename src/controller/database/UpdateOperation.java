package controller.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jdk.javadoc.internal.doclets.toolkit.util.Utils.Pair;

public class UpdateOperation implements SQLOperation {
	private enum Operator {
		EQUALS("= "), 
		GREATER("> "), 
		LOWER("< ");
		
		private String compareOperator;
		
		Operator(String operator) {
			this.compareOperator = operator;
		}
		
		public String getOperator() {
			return compareOperator;
		}
	}
	
	private String tableName;
	private List<Pair<String, SQLParameter>> valuesToChange;
	private List<Pair<String, SQLParameter>> clauses;
	private List<String> typeClauses;//TODO replace the String with an enum
	
	//TODO replace these value into enum
	/*public static final String EQUALS_OPERATION = "= ";
	public static final String GREATER_OPERATION = "> ";
	public static final String LOWER_OPERATION = "< ";
	*/
	
	/**
	 * Note : this will make all the clauses as
	 * EQUALS type.
	 */
	public UpdateOperation(String tableName, List<Pair<String, SQLParameter>> clauses,
			List<Pair<String, SQLParameter>> valuesToChange) {
		this.tableName = tableName;
		this.valuesToChange = valuesToChange;
		typeClauses = new ArrayList<>();
		for(int counter = 0; counter < clauses.size(); counter++) {
			typeClauses.add(Operator.EQUALS.getOperator());
		}
	}

	@Override
	public String getStatement() {
		StringBuilder statement = new StringBuilder("UPDATE ");
		statement.append(tableName + " ");
		
		statement.append("SET ");
		Iterator<Pair<String, SQLParameter>> indexValues = valuesToChange.iterator();
		while(indexValues.hasNext()) {
			
			statement.append(indexValues.next().first + " = ? ");
			
			if(indexValues.hasNext()) {
				statement.append(", ");
			}
		}
		
		statement.append("WHERE ");
		for(int indexClauses = 0; indexClauses < clauses.size(); indexClauses++) {
			statement.append(clauses.get(indexClauses).first + " ");
			statement.append(typeClauses.get(indexClauses) + " ? ");
			if(indexClauses != clauses.size() -1) {
				statement.append(", ");
			}else {
				statement.append(";");
			}
		}
		
		
		return statement.toString();
	}

	@Override
	public void configure(PreparedStatement statement) throws SQLException{
		int indexParameter = 0;
		
		for(Pair<String, SQLParameter> singleValue : valuesToChange) {
			singleValue.second.configure(indexParameter++, statement);
		}
		
		for(Pair<String, SQLParameter> singleClause : clauses) {
			singleClause.second.configure(indexParameter++, statement);
		}
	}

}
