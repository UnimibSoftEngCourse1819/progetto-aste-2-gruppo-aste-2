package controller.database.utilformodel;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sun.tools.javac.util.Pair;

import controller.database.SQLParameter;


public class SQLiteData {
	private String table;
	private List<Pair<String, SQLParameter>> data;
	
	public SQLiteData(String tableName) {
		table = tableName;
		data = new ArrayList<>();
	}
	
	public String getInsertStatement(){
		StringBuilder statement = new StringBuilder("INSERT INTO ");
		statement.append(table + " (");
		Iterator<Pair<String, SQLParameter>> typeIterator = data.iterator();
		while(typeIterator.hasNext()) {
			statement.append(typeIterator.next().fst);
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
	
	public void configureStatement(PreparedStatement statement) throws SQLException {
		for(int indexParameter = 0; indexParameter < data.size(); indexParameter++) {
			SQLParameter singleParameter = data.get(indexParameter).snd;
			singleParameter.configure(indexParameter + 1, statement);
		}
	}
	
	public void add(String nameColumn, String typeColumn, Object value) {
		data.add(new Pair<String, SQLParameter>(nameColumn, new SQLParameter(typeColumn, value)));
	}
}
