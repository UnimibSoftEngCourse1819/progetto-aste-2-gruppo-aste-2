package controller.database.select.decorator;

import controller.database.select.SelectComponent;

/**
 * This is the abstract class of the decorators
 * 
 * There some classes not implemented yet
 * TODO : GROUP BY - HAVING 
 */
public abstract class SelectDecorator implements SelectComponent {

	protected SelectComponent inner;
	
	public SelectDecorator(SelectComponent inner){
		this.inner = inner;
	}
	
}