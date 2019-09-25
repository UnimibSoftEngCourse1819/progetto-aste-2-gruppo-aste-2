package controller.database.utilformodel;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

import controller.database.SQLOperation;
import controller.database.SQLParameter;



public class SQLiteData implements SQLOperation{
	private String table;
	private LinkedHashMap<String, SQLParameter> data;
	
	public SQLiteData(String tableName) {
		table = tableName;
		data = new LinkedHashMap<>();
	}
	
	public String getStatement(){
		StringBuilder statement = new StringBuilder("INSERT INTO ");
		statement.append(table + " (");
		Iterator<String> typeIterator = new ArrayList<>(data.keySet()).iterator();
		
		while(typeIterator.hasNext()) {
			statement.append(typeIterator.next());
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
	
	public void configure(PreparedStatement statement) throws SQLException {
		Set<String> columnsName = data.keySet();
		int indexParameter = 0;
		
		for(String singleColumn : columnsName) {
			SQLParameter parameterToConfigure = data.get(singleColumn);
			parameterToConfigure.configure(indexParameter, statement);
			
			indexParameter++;
		}
	}
	
	public void add(String nameColumn, String typeColumn, Object value) {
		data.put(nameColumn, new SQLParameter(typeColumn, value));
	}
}
