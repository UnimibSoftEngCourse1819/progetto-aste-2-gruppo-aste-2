package controller.database.select;

/**
 * TODO need to adapt for function
 *
 */
public class ColumnSelect {
	private String tableName;
	private String fieldName;
	private String alias;
	
	public ColumnSelect(String tableName, String fieldName, String alias) {
		this.tableName = tableName;
		this.fieldName = fieldName;
		this.alias = alias;
	}
	
	public ColumnSelect(String tableName, String fieldName) {
		this(tableName, fieldName, "");
	}

	public String getSQLText() {
		String text = tableName + "." + fieldName;
		if(alias != null  && !alias.equals("")) {
			text += "AS " + alias;
		}
		return text;
	}
}
