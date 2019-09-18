package controller.database.select;

import controller.database.SQLOperation;

/**
 * TODO concrete classes : JOINT, WHERE, ORDER BY
 * @author Vallero
 *
 */
public interface SelectComponent extends SQLOperation{
	abstract int getNumberParametersRequired();
	
}