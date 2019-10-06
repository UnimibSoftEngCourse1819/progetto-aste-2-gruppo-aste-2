package controller.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.AuctionRequestManager;
import controller.DatabaseManager;
import controller.database.ResultDatabase;
import controller.database.select.SelectComponent;
import controller.database.select.SimpleSelect;
import exception.FailRollBackException;
import exception.InexistentTypeParameterException;
import exception.InsufficientRequirementsException;
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
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			SelectComponent select = new SimpleSelect("auction", Integer.parseInt(request.getParameter("id")));
			ResultDatabase result = DatabaseManager.executeSelect(select);
			
			if(request.getSession(false).getAttribute("id") != null) {
				SimpleSelect selectCredit = new SimpleSelect("creditUsed", (Integer) request.getSession(false).getAttribute("id"));
				ResultDatabase resultCredit = DatabaseManager.executeSelect(selectCredit);
				
				request.setAttribute("credit", resultCredit.getValue("UsedCredit", 0));
			}
			
			if(!result.isEmpty()) {
				List<String> auction = new ArrayList<>();
				
				auction.add(Integer.toString((Integer) result.getValue("ID", 0)));
				auction.add((String) result.getValue("Title", 0));
				auction.add((String) result.getValue("Description", 0));
				auction.add((String) result.getValue("Type", 0));
				auction.add(formatData(result.getValue("Conclusion", 0).toString()));
				auction.add(getAuctionType((String) result.getValue("Type", 0)));
				auction.add(Integer.toString((Integer) result.getValue("BasePrice", 0)));
				auction.add((String) result.getValue("Image", 0));
				
				request.setAttribute("auction", auction);
				
				if(request.getSession(false).getAttribute("id") != null) {
					int seller = (Integer) result.getValue("Seller", 0);
					int id = (Integer) request.getSession().getAttribute("id");
					
					request.setAttribute("isOwner", seller == id);
				}
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("auction.jsp");
				dispatcher.forward(request, response);
			}
		} catch (SQLiteFailRequestException e) {
			e.printStackTrace();
		}
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
    
    private String formatData(String dateTime) {
    	LocalDateTime temp = LocalDateTime.parse(dateTime);
    	LocalTime time = temp.toLocalTime().truncatedTo(ChronoUnit.MINUTES);
    	
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
    	LocalDate date = temp.toLocalDate(); 			   	
    	
    	return formatter.format(date) + " " + time.toString();
    }
}
