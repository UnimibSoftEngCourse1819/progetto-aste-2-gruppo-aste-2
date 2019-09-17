package controller.database;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import exception.MyConnectionException;

public class Connector {
	private static final String path = "database" + File.separator;
	private static final String DATABASE_DRIVER = "jdbc:sqlite:";
//	private static final String 
	private static final String DATABASE_NAME = "asta2.db";
	
	public static Connection getConnection() throws MyConnectionException, IOException {
		try {
			Class.forName("org.sqlite.JDBC");
			String sqlScriptUrl = Connector.class.getClassLoader().getResource("").getPath();
			System.out.println(sqlScriptUrl);
			return DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Massi\\eclipse-workspace\\ProgettoAsteOnline\\database\\asta2.db");
		} catch (SQLException | ClassNotFoundException e) {
			throw new MyConnectionException();
		}
	}
}