package controller.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import exception.MyConnectionException;

public class Connector {
	private static String path = "";
	private static final String DATABASE_DRIVER = "jdbc:sqlite:";
	private static final String DATABASE_NAME = "auction.db";
	
	public static Connection getConnection() throws MyConnectionException {
		try {
			Class.forName("org.sqlite.JDBC");
			return DriverManager.getConnection(DATABASE_DRIVER + path + DATABASE_NAME);
		} catch (SQLException | ClassNotFoundException e) {
			throw new MyConnectionException();
		}
	}
}