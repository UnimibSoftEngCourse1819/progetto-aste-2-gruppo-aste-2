package controller.database.select;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import controller.database.SQLOperation;

public class SimpleSelect implements SelectComponent {

	private String basicQuery;
	private List<Object> parameters;
	
	public SimpleSelect(String queryName, Object...parameters) {
		QueryLoader loader = QueryLoader.getInstance();
		basicQuery = loader.getQuery(queryName);
		this.parameters = new ArrayList<>();
		for(Object singleParameter : parameters) {
			this.parameters.add(singleParameter);
		}
	}
	
	public SimpleSelect(String queryName, List<Object> parameters) {
		QueryLoader loader = QueryLoader.getInstance();
		basicQuery = loader.getQuery(queryName);
		this.parameters = parameters;
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

	@Override
	public void configure(final PreparedStatement statement) throws SQLException {
		
	}
	
	public int getNumberParametersRequired() {
		int counter = 0; 
		  
	    for (int indexCharacter = 0; indexCharacter<basicQuery.length(); indexCharacter++) {	    	
	    	if (basicQuery.charAt(indexCharacter) == '?') {
	    		counter++; 
	    	}
	    }
	    return counter; 
	}

}
