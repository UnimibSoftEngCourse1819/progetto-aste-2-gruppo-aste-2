package controller;

import java.util.ArrayList;
import java.util.List;

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
	
	public boolean authenticate() { 
		return true;
	}
}
