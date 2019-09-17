package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import controller.database.Connector;
import controller.database.SQLOperation;
import controller.database.utilformodel.SQLiteData;
import controller.database.utilformodel.Storable;
import exception.FailRollBackException;
import exception.MyConnectionException;
import exception.SQLiteFailRequestException;

public class DatabaseManager {

	public static void create(Storable newData) throws SQLiteFailRequestException, IOException {
		SQLiteData sqlData = newData.getSQLiteData();
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(sqlData.getInsertStatement())){
			sqlData.configureStatement(statement);
			statement.execute();
		} catch (SQLException | MyConnectionException e) {
			throw new SQLiteFailRequestException();
		}
	}
	
	/**
	 * Note : the entire operation will be 
	 * treated as a sql transaction
	 * @throws FailRollBackException 
	 * @throws IOException 
	 */
	public static void execute(List<SQLOperation> operations) throws SQLiteFailRequestException, FailRollBackException, IOException {
		Connection connection = null;
		try{
			connection = Connector.getConnection();
			connection.setAutoCommit(false);
			
			for(SQLOperation singleOperation : operations) {
				execute(singleOperation, connection);
			}
			
		} catch (SQLException | MyConnectionException e) {
			if(connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					throw new FailRollBackException();
				}				
			}
			
			throw new SQLiteFailRequestException();
		} finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					//this shouldn't happen
				}				
			}
		}
	}
	
	private static void execute(SQLOperation operation, Connection connection) throws SQLException {
		try(PreparedStatement statement = connection.prepareStatement(operation.getStatement())){
			operation.configure(statement);
			statement.execute();
		}
	}

}