package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import controller.database.Connector;
import controller.database.SQLiteData;
import controller.database.Storable;
import exception.MyConnectionException;

public class DatabaseManager {

	public static void create(Storable newData) {
		SQLiteData sqlData = newData.getSQLiteData();
		try(Connection connection = Connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(sqlData.getInsertStatement())){
			sqlData.configureInsert(statement);
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MyConnectionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}