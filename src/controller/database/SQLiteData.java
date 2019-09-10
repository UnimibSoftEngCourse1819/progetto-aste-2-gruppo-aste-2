package controller.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import jdk.javadoc.internal.doclets.toolkit.util.Utils.Pair;

public class SQLiteData {
	private String table;
	private List<Pair<String, SQLParameter>> data;
	
	public String getInsertStatement(){
		StringBuilder statement = new StringBuilder("INSERT INTO ");
		statement.append(table + " (");
		Iterator<Pair<String, SQLParameter>> typeIterator = data.iterator();
		while(typeIterator.hasNext()) {
			statement.append(typeIterator.next().first);
			if(typeIterator.hasNext()) {
				statement.append(", ");
			}
		}
		statement.append(") VALUES (?");
		for(int counter = 1; counter < data.size(); counter++) {
			statement.append(", ?");
		}
		statement.append(");");
		return statement.toString();
	}
	
	public void configureInsert(PreparedStatement statement) throws SQLException {
		for(int indexParameter = 0; indexParameter < data.size(); indexParameter++) {
			SQLParameter singleParameter = data.get(indexParameter).second;
			singleParameter.configure(indexParameter + 1, statement);
		}
	}
	
	private class SQLParameter{
		private String type;
		private Object value;

		public void configure(int indexStatement, PreparedStatement statement) throws SQLException {
			switch(type) {
			case "INTEGER":
				statement.setInt(indexStatement, (Integer) value);
				break;
				//TODO continua qui
			}
		}
	}

}
