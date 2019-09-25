package controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

import controller.database.ResultDatabase;
import controller.database.select.SimpleSelect;
import exception.SQLiteFailRequestException;
import jdk.javadoc.internal.doclets.toolkit.util.Utils.Pair;
import model.User;

/**
 * This class manages the user
 * 
 *
 */

public class AuthenticationService {	
	
	public AuthenticationService() {
		super();
	}
	
	public void registerUser(User user) throws SQLiteFailRequestException, IOException {
		DatabaseManager.create(user);		
	}
	
	public Pair<Integer, String> authenticate(HttpServletRequest request){
		Pair<Integer, String> values = null;
		
		try {
			
			SimpleSelect select = new SimpleSelect("Authentication", request.getParameter("email"), request.getParameter("password"));
			ResultDatabase result = DatabaseManager.executeSelect(select);
			
			if(!result.isEmpty()) {
				String name = (String) result.getValue("Name", 0);
				Integer id = (Integer) result.getValue("ID", 0);
				
				values = new Pair<>(id, name);
			}
		} catch (SQLiteFailRequestException e) {
			e.printStackTrace();
		}
		
		return values;
	}
}
