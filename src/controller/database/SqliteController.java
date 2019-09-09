package controller.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import exception.MyConnectionException;

public class SqliteController {
	public static void execute(List<SQLAction> operations) throws SQLException, MyConnectionException {
		try(Connection connection = Connector.getConnection()){
			operations.forEach(singleOperation -> singleOperation.execute(connection));
		}
	}

	public static ResultDatabase executeSelect(SelectRequest select) {
		ResultDatabase result = null;
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(select.getStatement())){
			select.configureStatement(statement);
			result = new ResultDatabase(statement.executeQuery());
		}
		return result;
	}
}
