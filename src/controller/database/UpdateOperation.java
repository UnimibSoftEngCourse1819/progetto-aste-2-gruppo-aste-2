package controller.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
	private LinkedHashMap<String, SQLParameter> valuesToChange;
	private LinkedHashMap<String, SQLParameter> clauses;
	private List<String> typeClauses;
	
	/**
	 * Note : this will make all the clauses as
	 * EQUALS type.
	 */
	public UpdateOperation(String tableName, LinkedHashMap<String, SQLParameter> clauses,
			LinkedHashMap<String, SQLParameter> valuesToChange) {
		this.tableName = tableName;
		this.valuesToChange = valuesToChange;
		this.clauses = clauses;
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
		
		Iterator<String> indexValues = valuesToChange.keySet().iterator();
		while(indexValues.hasNext()) {
			
			statement.append(indexValues.next() + " = ? ");
			
			if(indexValues.hasNext()) {
				statement.append(", ");
			}
		}
		
		statement.append("WHERE ");
		
		int indexClauses = 0;
		for(String singleClause : clauses.keySet()) {
			statement.append(singleClause + " ");
			statement.append(typeClauses.get(indexClauses) + " ? ");
			
			if(indexClauses != clauses.size() -1) {
				statement.append(", ");
			}else {
				statement.append(";");
			}
			indexClauses++;
		}
		
		return statement.toString();
	}

	@Override
	public void configure(PreparedStatement statement) throws SQLException{
		int indexParameter = 1;
		
		for(Map.Entry<String, SQLParameter> singleValue : valuesToChange.entrySet()) {
			singleValue.getValue().configure(indexParameter++, statement);
		}
		
		for(Map.Entry<String, SQLParameter> singleClause : clauses.entrySet()) {
			singleClause.getValue().configure(indexParameter++, statement);
		}
	}

}
