package model;

import controller.database.SQLParameter;
import controller.database.utilformodel.SQLiteData;
import controller.database.utilformodel.Storable;

public class Category implements Storable {
	protected static final String SQL_TABLE = "category";
	private String name;
	
	public Category(String name) {
		this.name = name;
	}
	
	@Override
	public SQLiteData getSQLiteData() {
		SQLiteData sqlData = new SQLiteData(SQL_TABLE);
		
		sqlData.add("Name", SQLParameter.VARCHAR, name);
		
		return sqlData;
	}

}
