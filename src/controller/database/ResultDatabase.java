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

		public void addValue(ResultSet result) throws SQLException {
			switch(type) {
			case Types.INTEGER :
				values.add((Integer) result.getInt(name));
				break;
			case Types.VARCHAR :
				values.add(result.getString(name));
				break;
			case Types.DATE :
				values.add(result.getDate(name).toLocalDate());
			default:
				//this shouldn't happen ... maybe we should throw an Exception
			}
		}
		
	}
}
