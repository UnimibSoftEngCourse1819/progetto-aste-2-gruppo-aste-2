package controller.database.select.decorator;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import controller.database.SQLParameter;
import controller.database.select.SelectComponent;

public class Where extends SelectDecorator {
	private String clauseDecorator;
	private List<SQLParameter> values;
	
	public Where(SelectComponent inner, String extraClause, SQLParameter...values) {
		super(inner);
		clauseDecorator = extraClause;
		this.values = Arrays.asList(values);
	}

	@Override
	public int getNumberParametersRequired() {
		return inner.getNumberParametersRequired() + SelectComponent.getNumberOfValuesFromStatement(clauseDecorator);
	}

	@Override
	public String getStatement() {
		return inner.getStatement() + clauseDecorator + " ";
	}

	@Override
	public void configure(PreparedStatement statement) throws SQLException {
		inner.configure(statement);	
		int beginIndex = inner.getNumberParametersRequired() + 1;
		for(int indexValue = 0;
				indexValue < SelectComponent.getNumberOfValuesFromStatement(clauseDecorator);
				indexValue++) {
			values.get(indexValue).configure(indexValue + beginIndex, statement); 
		}
	}

}
