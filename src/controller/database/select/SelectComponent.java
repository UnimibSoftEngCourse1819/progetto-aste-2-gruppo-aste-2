package controller.database.select;

import controller.database.SQLOperation;

/**
 * @author Vallero
 *
 */
public interface SelectComponent extends SQLOperation{
	abstract int getNumberParametersRequired();
	
	public static int getNumberOfValuesFromStatement(String statement) {
		int counter = 0; 
		  
	    for (int indexCharacter = 0; indexCharacter < statement.length(); indexCharacter++) {	    	
	    	if (statement.charAt(indexCharacter) == '?') {
	    		counter++; 
	    	}
	    }
	    return counter; 
	}
}