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
import controller.database.ResultDatabase;
import controller.database.select.SimpleSelect;
import exception.MyConnectionException;
import exception.SQLiteFailRequestException;
import model.user.User;

/**
 * This class manages the user
 * 
 *
 */

public class AuthenticationService {
	
	List<User> users; //<-- TODO per favore metti la visibilità !!!
 	
	
	public AuthenticationService() {
		users = new ArrayList<>();
	}
	
	public void registerUser(User user) throws SQLiteFailRequestException, IOException {
		DatabaseManager.create(user);		
	}
	
	public String authenticate(HttpServletRequest request){
		String name = null;
		
		try {
			SimpleSelect select = new SimpleSelect("Authentication", request.getParameter("email"), request.getParameter("password"));
			ResultDatabase result = DatabaseManager.executeSelect(select);
			name = (String) result.getValue("Name", 0);
			
			//*********OLD AND DISGUSTING WAY !!!!
			Connection connection = Connector.getConnection();
			String  statementString = "SELECT * FROM user WHERE Email = ? AND Password = ?";
			PreparedStatement statement =  connection.prepareStatement(statementString);
			statement.setString(1, request.getParameter("email"));
			statement.setString(2, request.getParameter("password"));
			
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			
			name = resultSet.getString("Name");
		} catch (SQLException | SQLiteFailRequestException | MyConnectionException e) {
			e.printStackTrace();
		}
		
		return name;
	}
}
