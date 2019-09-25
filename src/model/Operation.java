package model;

import java.util.List;

import controller.database.SQLOperation;

public abstract interface Operation {
	public abstract List<SQLOperation> getSQLOperations();
}
