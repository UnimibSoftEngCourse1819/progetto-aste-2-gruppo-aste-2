package controller.database.select;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import controller.database.SQLParameter;

public class SimpleSelect implements SelectComponent {

	private String basicQuery;
	private List<SQLParameter> parameters;
	
	public SimpleSelect(String queryName, Object...parameters) {
		List<Object> values = Arrays.asList(parameters);
		instantiate(queryName, values);
	}
	
	public SimpleSelect(String queryName, List<Object> parameters) {
		instantiate(queryName, parameters);
	}
	
	/**
	 * This is for avoid duplicate code and 
	 * since we forced to use this() constructor
	 * as a first command.
	 */
	private void instantiate(String queryName, List<Object> parameters) {
		QueryLoader loader = QueryLoader.getInstance();
		QueryData query = loader.getQuery(queryName);
		basicQuery = query.getQuery();
				
		this.parameters = SQLParameter.parse(query.getTypeParameters(), parameters);
	}

	/**
	 * There is no check  
	 * so you will get a SQLException when you
	 * will execute the statement for these reasons :
	 * 1) the clauses aren't in the correct order
	 * 2) you trying to use a field without joint his table
	 * 
	 */
	@Override
	public String getStatement() {
		return basicQuery;
	}
	
	
	/**
	 * There is no check  
	 * so you will get a SQLException when you
	 * will execute the statement for these reasons :
	 * 1) the clauses aren't in the correct order
	 * 2) you trying to use a field without joint his table
	 * 
	 */
	@Override
	public void configure(final PreparedStatement statement) throws SQLException {
		int numberOfParameters = getNumberParametersRequired();
		for(int indexParameter = 1; indexParameter <= numberOfParameters; indexParameter++) {
			parameters.get(indexParameter - 1).configure(indexParameter, statement); 
		}
	}
	
	public int getNumberParametersRequired() {
		return SelectComponent.getNumberOfValuesFromStatement(basicQuery);
	}

}
