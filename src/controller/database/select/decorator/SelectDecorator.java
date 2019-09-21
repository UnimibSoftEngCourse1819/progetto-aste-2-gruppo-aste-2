package controller.database.select.decorator;

import controller.database.select.SelectComponent;

/**
 * TODO GROUP BY - HAVING
 */
public abstract class SelectDecorator implements SelectComponent {

	protected SelectComponent inner;
	
	protected SelectDecorator(SelectComponent inner){
		this.inner = inner;
	}
	
}