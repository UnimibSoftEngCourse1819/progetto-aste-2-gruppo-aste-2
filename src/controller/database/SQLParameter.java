package controller.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
			break;
		default://this should be avoided
			statement.setObject(indexStatement, value);
		}
	}

	public static List<SQLParameter> parse(List<String> typeParameters, List<Object> parameters) {
		List<SQLParameter> result = new ArrayList<>();
		
		for(int indexParameter = 0; indexParameter < parameters.size(); indexParameter++) {
			result.add(new SQLParameter(typeParameters.get(indexParameter), parameters.get(indexParameter)));
		}
		
		return result;
	}
}
