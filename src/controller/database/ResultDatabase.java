package controller.database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
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
	
	public Object getValue(String nameColumn, int index) {
		SQLColumn result = getColumn(nameColumn);
		return result == null ? null : result.getValue(index);
	}
	
	public List<Object> getValuesList(String nameColumn){
		SQLColumn result = getColumn(nameColumn);
		return result == null ? null : result.getValueList();
	}
	
	@SuppressWarnings("unchecked")//we checked the type on the first value
	public static <T> List<T> castListInto(Class<T> type, List<Object> listToCast) {
		List<T> resultList = new ArrayList<>();
		if(listToCast.get(0).getClass().equals(type.getClass())) {
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
		
		protected String getName() {
			return name;
		}
		
		protected Object getValue(int index) {
			return values.get(index);
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
				Object value = result.getObject(name);//TODO viene ricevuto come stringa 1997-06-28
				String type = value.getClass().toString();
//				values.add(().toLocalDate());
				System.out.print(type);
			default:
				//this shouldn't happen ... maybe we should throw an Exception
			}
		}
		
	}
}
