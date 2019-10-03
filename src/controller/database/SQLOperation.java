package controller.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This interface is for classes that
 * can be executed from the DatabaseManager
 * @author Vallero
 *
 */
public interface SQLOperation {
	public abstract String getStatement();
	public abstract void configure(PreparedStatement statement) throws SQLException;
}