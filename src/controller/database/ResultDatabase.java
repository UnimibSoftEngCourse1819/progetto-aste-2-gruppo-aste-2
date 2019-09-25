package controller.database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


/**
 * TODO maybe we should create null object 
 *
 */
public class ResultDatabase {
	private List<SQLColumn> table;
	
	
	public ResultDatabase(ResultSet result) throws SQLException {
		ResultSetMetaData resultMetadata = result.getMetaData();
		table = new ArrayList<>();
		for(int indexColumn = 1; indexColumn <= resultMetadata.getColumnCount(); indexColumn++ ) {
			table.add(new SQLColumn(resultMetadata.getColumnLabel(indexColumn), 
					resultMetadata.getColumnType(indexColumn)));
		}
		
		while(result.next()) {
			for(SQLColumn column : table) {
				column.addValue(result);
			}
		}
	}
	
	public boolean isEmpty() {
		boolean result = false;
		for(SQLColumn column : table) {
			if(column.isEmpty()) {
				result = true;
			}
		}
		return result;
	}
	
	public Object getValue(String nameColumn, int index) {
		SQLColumn result = getColumn(nameColumn);
		return result == null ? null : result.getValue(index);
	}
	
	public List<Object> getValuesList(String nameColumn){
		SQLColumn result = getColumn(nameColumn);
		return result == null ? null : result.getValueList();
	}
	
	public HashMap<String, Object> getRowValues(int indexRow) {
		HashMap <String, Object> result = new HashMap<>();
		for(SQLColumn field : table) {
			result.put(field.getName(), field.getValue(indexRow));
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")//we checked the type on the first value
	public static <T> List<T> castListInto(Class<T> type, List<Object> listToCast) {
		List<T> resultList = new ArrayList<>();
		if(listToCast.get(0).getClass().equals(type)) {
			for(Object singleElement : listToCast) {
				resultList.add((T) singleElement );
			}
		}
		return resultList;
	}
	
	private SQLColumn getColumn(String nameColumn) {
		Optional<SQLColumn> result = table.stream()
				.filter(singleColumn -> singleColumn.getName().equals(nameColumn))
				.findFirst();
		return result.isPresent() ? result.get() : null;
	}
	
	private class SQLColumn{
		private String name;
		private int type; //these are from java.sql.Types
		private List<Object> values;
		
		public SQLColumn(String columnLabel, int columnType) {
			name = columnLabel;
			type = columnType;
			values = new ArrayList<>();
		}
		
		public boolean isEmpty() {
			return values.isEmpty();
		}

		protected String getName() {
			return name;
		}
		
		protected Object getValue(int index) {
			return values.size() > index ? values.get(index) : null;
		}
		
		protected List<Object> getValueList() {
			return values;
		}

		public void addValue(ResultSet result) throws SQLException {
			switch(type) {
			case Types.INTEGER :
				values.add((Integer) result.getInt(name));
				break;
			case Types.VARCHAR :
				values.add(result.getString(name));
				break;
			case Types.DATE :
				Object value = result.getObject(name);
				values.add(castToLocalDateTime(value));
				break;
			default:
				//this shouldn't happen ... maybe we should throw an Exception
			}
		}
		
		private Object castToLocalDateTime(Object date) {
			Object result = null;
			String dateString = (String) date;
			
			if(dateString.contains("T")) {
				result = LocalDateTime.parse(dateString);
			}
			else {
				result = LocalDate.parse(dateString);
			}		
			
			return result;
		}
		
	}
}
