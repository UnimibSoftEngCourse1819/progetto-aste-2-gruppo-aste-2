package model;

import java.util.List;

import controller.database.SQLOperation;

public abstract class Operation {
	public abstract List<SQLOperation> getSQLOperations();
}
