package controller.database;

import java.sql.PreparedStatement;

public interface SQLOperation {
	public abstract String getStatement();
	public abstract void configure(PreparedStatement statement);
}