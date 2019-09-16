package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import controller.database.Connector;
import exception.MyConnectionException;
import exception.SQLiteFailRequestException;
import model.user.User;

/**
 * This class manages the user
 * 
 *
 */

public class AuthenticationService {
	
	List<User> users;
	
	public AuthenticationService() {
		users = new ArrayList<>();
	}
	
	public void registerUser(User user) throws SQLiteFailRequestException {
		DatabaseManager.create(user);		
	}
	
	public String authenticate(HttpServletRequest request) throws MyConnectionException, IOException {
		String name = null;
		
		try {
			Connection connection = Connector.getConnection();
			String  statementString = "SELECT * FROM user WHERE Email = ? AND Password = ?";
			PreparedStatement statement =  connection.prepareStatement(statementString);
			statement.setString(1, request.getParameter("email"));
			statement.setString(2, request.getParameter("password"));
			
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			
			name = resultSet.getString("Name");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return name;
	}
}
