package controller.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.AuctionRequestManager;
import controller.DatabaseManager;
import controller.database.ResultDatabase;
import controller.database.select.SimpleSelect;
import exception.InexistentTypeParameterException;
import exception.SQLiteFailRequestException;

/**
 * Servlet implementation class AuctionServlet
 */
@WebServlet("/auction")
public class AuctionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuctionServlet() {
        super();
    }
    
    private String getAuctionType(String auctionType) {
    	String type = "";
    	
    	switch(auctionType) {
    	case "FirstSealed":
    		type = "Asta in busta chiusa";
    		break;
    	case "SecondSealed":
    		type = "Asta in busta chiusa al \"secondo prezzo\"";
    		break;
    	case "English":
    		type = "Asta \"inglese\"";
    		break;
    	case "Dutch":
    		type = "Asta \"olandese\"";
    		break;
    	default:
    	}
    	
    	return type;
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			SimpleSelect select = new SimpleSelect("auction", Integer.parseInt(request.getParameter("id")));
			ResultDatabase result = DatabaseManager.executeSelect(select);
			
			if(!result.isEmpty()) {
				String[] auction = new String[6];
				
				auction[0] = Integer.toString((Integer) result.getValue("ID", 0));
				auction[1] = (String) result.getValue("Title", 0);
				auction[2] = (String) result.getValue("Description", 0);
				auction[3] = (String) result.getValue("Type", 0);
				auction[4] = (String) result.getValue("Conclusion", 0);
				auction[5] = getAuctionType((String) result.getValue("Type", 0));
				
				request.setAttribute("auction", auction);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("auction.jsp");
				dispatcher.forward(request, response);
			}
		} catch (SQLiteFailRequestException e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		try {
			AuctionRequestManager.makeOffer(request);
		} catch (SQLiteFailRequestException | InexistentTypeParameterException e) {
			e.printStackTrace();
		}
	}
}
