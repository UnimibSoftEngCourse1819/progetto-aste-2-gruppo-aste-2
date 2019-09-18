package controller.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import exception.MyConnectionException;

public class Connector {
	private static final String PATH = File.separator + "database" + File.separator;
	private static final String DATABASE_DRIVER = "jdbc:sqlite:";
	private static final String DATABASE_NAME = "asta2.db";
	
	private Connector(){
		/*not used*/
	}
	
	public static Connection getConnection() throws MyConnectionException {
		try {
			Class.forName("org.sqlite.JDBC");
			String sqlScriptUrl = System.getProperty("user.dir");

			return DriverManager.getConnection(Connector.DATABASE_DRIVER + sqlScriptUrl + PATH + Connector.DATABASE_NAME);
		} catch (SQLException | ClassNotFoundException e) {
			throw new MyConnectionException();
		}
	}
}