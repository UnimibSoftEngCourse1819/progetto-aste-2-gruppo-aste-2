package controller.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DatabaseManager;
import controller.database.ResultDatabase;
import controller.database.select.SimpleSelect;
import exception.SQLiteFailRequestException;

/**
 * Servlet implementation class personalAreaServlet
 */
@WebServlet("/personalArea")
public class PersonalAreaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonalAreaServlet() {
        super();
    }
    
    private List<String[]> getAuctions(SimpleSelect select) {
    	List<String[]> auctions = new ArrayList<>();
    	
    	try {
			ResultDatabase result = DatabaseManager.executeSelect(select);
			
			if(!result.isEmpty()) {
				int index = 0;
				
				while(result.getValue("ID", index) != null) {
					String[] data = new String[4];
					
					data[0] = Integer.toString((Integer) result.getValue("ID", index));
					data[1] = (String) result.getValue("Title", index);
					data[2] = (String) result.getValue("Description", index);
					data[3] = result.getValue("Creation", index).toString();
					
					auctions.add(data);
					index++;
				}
			}
		} catch (SQLiteFailRequestException e) {
			e.printStackTrace();
		}
    	
    	return auctions;
    }
    
    private String getUserCredit(SimpleSelect select) {
    	String userCredit = "";
    	
    	try {
    		ResultDatabase result = DatabaseManager.executeSelect(select);
    		
    		if(!result.isEmpty()) {
    			userCredit = Integer.toString((Integer) result.getValue("Credit", 0));
    		}
    	}
    	catch (SQLiteFailRequestException e) {
    		e.printStackTrace();
    	}
    	
    	return userCredit;
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object userID = request.getSession(false).getAttribute("id");
		List<String[]> userAuctions = getAuctions(new SimpleSelect("userAuctions", userID));
		List<String[]> auctionOffered = getAuctions(new SimpleSelect("auctionOffered", userID));
		String userCredit = getUserCredit(new SimpleSelect("userCredit", userID));
		
		request.setAttribute("userAuctions", userAuctions);
		request.setAttribute("auctionOffered", auctionOffered);
		request.setAttribute("userCredit", userCredit);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("personalArea.jsp");
		dispatcher.forward(request, response);
	}

}
