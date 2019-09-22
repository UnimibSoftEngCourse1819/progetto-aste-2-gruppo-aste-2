package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sun.tools.javac.util.Pair;

import controller.database.ResultDatabase;
import controller.database.select.SimpleSelect;
import exception.SQLiteFailRequestException;
import model.User;

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
	
	public Pair<Integer, String> authenticate(HttpServletRequest request){
		Pair<Integer, String> values = null;
		
		try {
			String name = null;
			int id = -1;
			SimpleSelect select = new SimpleSelect("Authentication", request.getParameter("email"), request.getParameter("password"));
			ResultDatabase result = DatabaseManager.executeSelect(select);
			name = (String) result.getValue("Name", 0);
			id = (int) result.getValue("ID", 0);
			values = new Pair<Integer, String>(id, name);
		} catch (SQLiteFailRequestException e) {
			e.printStackTrace();
		}
		
		return values;
	}
}
