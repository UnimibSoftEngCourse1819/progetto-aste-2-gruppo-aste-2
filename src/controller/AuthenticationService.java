package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import controller.database.ResultDatabase;
import controller.database.select.SimpleSelect;
import exception.SQLiteFailRequestException;
import model.user.User;

/**
 * This class manages the user
 * 
 *
 */

public class AuthenticationService {
	
	private List<User> users;
 	
	
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
			
		} catch (SQLiteFailRequestException e) {
			e.printStackTrace();
		}
		
		return name;
	}
}
