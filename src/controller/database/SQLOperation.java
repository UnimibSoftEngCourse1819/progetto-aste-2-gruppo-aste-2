package controller.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SQLOperation {
	public abstract String getStatement();
	public abstract void configure(PreparedStatement statement) throws SQLException;
}