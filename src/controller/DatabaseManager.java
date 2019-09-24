package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import controller.database.Connector;
import controller.database.ResultDatabase;
import controller.database.SQLOperation;
import controller.database.select.SelectComponent;
import controller.database.select.SimpleSelect;
import controller.database.utilformodel.SQLiteData;
import controller.database.utilformodel.Storable;
import exception.FailRollBackException;
import exception.MyConnectionException;
import exception.SQLiteFailRequestException;

/**
 * This class will manage
 * ALL the request with the 
 * database
 * @author Vallero
 *
 */

public class DatabaseManager {
	
	private DatabaseManager() {
		//this class is only called by static methods
	}

	public static void create(Storable newData) throws SQLiteFailRequestException, IOException {
		SQLiteData sqlData = newData.getSQLiteData();
		
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(sqlData.getStatement())){
			sqlData.configure(statement);
			statement.execute();
		} catch (SQLException | MyConnectionException e) {
			throw new SQLiteFailRequestException();
		}
	}
	
	public static void create(List<Storable> newData) throws SQLiteFailRequestException, FailRollBackException {
		List<SQLOperation> insert = newData.stream()
				.map(Storable::getSQLiteData)
				.collect(Collectors .toCollection(ArrayList::new));
		execute(insert);
	}
	
	/**
	 * Note : the entire operation will be 
	 * treated as a sql transaction
	 * @throws FailRollBackException 
	 * @throws IOException 
	 */
	public static void execute(List<SQLOperation> operations) throws SQLiteFailRequestException, FailRollBackException {
		Connection connection = null;
		try{
			connection = Connector.getConnection();
			connection.setAutoCommit(false);
			
			for(SQLOperation singleOperation : operations) {
				execute(singleOperation, connection);
			}
			
			connection.commit();
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
	
	public static ResultDatabase executeSelect(SelectComponent select) throws SQLiteFailRequestException{
		ResultDatabase result = null;
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(select.getStatement())){
			select.configure(statement);
			result = new ResultDatabase(statement.executeQuery());
		} catch (MyConnectionException | SQLException e) {
			throw new SQLiteFailRequestException();
		}
		return result;
	}

}