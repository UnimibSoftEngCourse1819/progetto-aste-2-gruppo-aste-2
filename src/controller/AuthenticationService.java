package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import controller.database.ResultDatabase;
import controller.database.select.SimpleSelect;
import exception.SQLiteFailRequestException;
import model.User;

/**
 * This class manages the users
 *
 */

public class AuthenticationService {	
	
	private static final Logger LOGGER = MyLogger.getLoggerInstance(AuthenticationService.class.getName());
	
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
			LOGGER.log(Level.SEVERE, "Non è stato possibile eseguire la verifica dell'utente ", e);
		}
		
		return values;
	}
}
