package controller.database.select;

/**
 * TODO WHERE - GROUP BY - HAVING
 */
abstract class SelectDecorator implements SelectComponent {

	protected SelectComponent inner;
	
	protected SelectDecorator(SelectComponent inner){
		this.inner = inner;
	}
	
}