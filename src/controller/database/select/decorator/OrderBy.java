package controller.database.select.decorator;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controller.database.select.SelectComponent;

public class OrderBy extends SelectDecorator {
	
	private List<String> fields;
	private List<Boolean> isDesc;
	
	/**
	 * Note : like sql the columns are order 
	 * by ascending
	 */
	protected OrderBy(SelectComponent inner, String...columns) {
		super(inner);
		fields = Arrays.asList(columns);
		isDesc = new ArrayList<>();
		for(int counter = 0; counter < fields.size(); counter ++) {
			isDesc.add(false);
		}
	}
	
	/**
	 * Note : extra values will be ignored
	 * while missing values will be set false
	 */
	public void setDesc(Boolean...isDesc) {
		this.isDesc = Arrays.asList(isDesc);
		while(this.isDesc.size() < fields.size()) {
			this.isDesc.add(false);
		}
	}
	
	public void setDesc(int index) {
		isDesc.set(index, true);
	}

	@Override
	public int getNumberParametersRequired() {
		return inner.getNumberParametersRequired();
	}

	@Override
	public String getStatement() {
		StringBuilder orderByClause = new StringBuilder("ORDER BY ");
		
		for(int indexField = 0; indexField < fields.size(); indexField++) {
			
			orderByClause.append(fields.get(indexField) + " ");
			if(isDesc.get(indexField)) {
				orderByClause.append("DESC ");
			}
			
			if(indexField + 1 < fields.size()) {
				orderByClause.append(", ");
			}
		}
		
		orderByClause.append(" ");
		return inner.getStatement() + orderByClause.toString();
	}

	@Override
	public void configure(PreparedStatement statement) throws SQLException {
		inner.configure(statement);
	}

}
