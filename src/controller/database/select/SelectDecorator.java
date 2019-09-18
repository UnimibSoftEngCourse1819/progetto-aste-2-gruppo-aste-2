package controller.database.select;

abstract class SelectDecorator implements SelectComponent {

	protected SelectComponent innerWhere;
	
	protected SelectDecorator(SelectComponent inner){
		innerWhere = inner;
	}
	
}
