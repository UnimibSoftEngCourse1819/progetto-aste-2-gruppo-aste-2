package controller.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import exception.MyConnectionException;

public class Connector {
	private static final String path = "database" + File.separator;
	private static final String DATABASE_DRIVER = "jdbc:sqlite:";
	private static final String DATABASE_NAME = "asta2.db";
	
	public static Connection getConnection() throws MyConnectionException {
		try {
			Class.forName("org.sqlite.JDBC");
			return DriverManager.getConnection(DATABASE_DRIVER + path + DATABASE_NAME);
		} catch (SQLException | ClassNotFoundException e) {
			throw new MyConnectionException();
		}
	}
}