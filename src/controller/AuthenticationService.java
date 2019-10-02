package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import controller.database.ResultDatabase;
import controller.database.select.SimpleSelect;
import exception.SQLiteFailRequestException;
import model.User;

/**
 * This class manages the user
 *
 */

public class AuthenticationService {	
	
	public AuthenticationService() {
		super();
	}
	
	public void registerUser(User user) throws SQLiteFailRequestException, IOException {
		DatabaseManager.create(user);		
	}
	
	public HashMap<String, Object> authenticate(HttpServletRequest request){
		HashMap<String, Object> values = null;
		
		try {
			
			SimpleSelect select = new SimpleSelect("Authentication", request.getParameter("email"), request.getParameter("password"));
			ResultDatabase result = DatabaseManager.executeSelect(select);
			
			if(!result.isEmpty()) {
				values = (HashMap<String, Object>) result.getRowValues(0);
			}
		} catch (SQLiteFailRequestException e) {
			e.printStackTrace();
		}
		
		return values;
	}
}
