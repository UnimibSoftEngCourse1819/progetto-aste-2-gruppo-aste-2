package controller.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class SQLParameter{
	public static final String INTEGER = "INTEGER";
	public static final String VARCHAR = "VARCHAR";
	public static final String DATE = "DATE";
	
	private String type;
	private Object value;

	public SQLParameter(String typeColumn, Object value) {
		type = typeColumn;
		this.value = value;
	}

	public void configure(int indexStatement, PreparedStatement statement) throws SQLException {
		String typeToCheck = type;
		
		if(type.contains("(")) {
			typeToCheck = "" + type.substring(0 , type.indexOf('(')); //the "" is to avoid memory leak
		}
		switch(typeToCheck) {
		case INTEGER:
			statement.setInt(indexStatement, (Integer) value);
			break;
		case VARCHAR:
			statement.setString(indexStatement, (String) value);
			break;
		case DATE:
			statement.setObject(indexStatement, (LocalDate) value);
		default://this should be avoid
			statement.setObject(indexStatement, value);
		}
	}
}
