package controller.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DatabaseManager;
import controller.MyLogger;
import controller.database.ResultDatabase;
import controller.database.select.SimpleSelect;
import exception.SQLiteFailRequestException;
import model.User;

/**
 * Servlet implementation class personalAreaServlet
 */
@WebServlet("/personalArea")
public class PersonalAreaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = MyLogger.getLoggerInstance(PersonalAreaServlet.class.getName());
    private static final String MESSAGE_ERROR = "Non è stato possibile gestire la richiesta";
	
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
					String[] data = new String[5];
					
					data[0] = Integer.toString((Integer) result.getValue("ID", index));
					data[1] = (String) result.getValue("Title", index);
					data[2] = (String) result.getValue("Description", index);
					data[3] = formatData(result.getValue("Creation", index).toString());
					data[4] = formatData(result.getValue("Conclusion", index).toString());
					
					auctions.add(data);
					index++;
				}
			}
		} catch (SQLiteFailRequestException e) {
			LOGGER.log(Level.SEVERE, MESSAGE_ERROR, e);
		}
    	
    	return auctions;
    }
    
    private List<String[]> getAuctionsWon(SimpleSelect select) {
    	List<String[]> auctions = new ArrayList<>();
    	
    	try {
			ResultDatabase result = DatabaseManager.executeSelect(select);
			
			if(!result.isEmpty()) {
				int index = 0;
				
				while(result.getValue("ID", index) != null) {
					String[] data = new String[7];
					
					data[0] = Integer.toString((Integer) result.getValue("ID", index));
					data[1] = (String) result.getValue("Title", index);
					data[2] = (String) result.getValue("Description", index);
					data[3] = formatData(result.getValue("Creation", index).toString());
					data[4] = formatData(result.getValue("Conclusion", index).toString());
					data[5] = String.valueOf(result.getValue("Price", index));
					data[6] = (String) result.getValue("Waiver", index);
					
					auctions.add(data);
					index++;
				}
			}
		} catch (SQLiteFailRequestException e) {
			LOGGER.log(Level.SEVERE, MESSAGE_ERROR, e);
		}
    	
    	return auctions;
    }
    
    private String formatData(String dateTime) {
    	LocalDateTime temp = LocalDateTime.parse(dateTime);
    	LocalTime time = temp.toLocalTime().truncatedTo(ChronoUnit.MINUTES);
    	
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
    	LocalDate date = temp.toLocalDate(); 			   	
    	
    	return formatter.format(date) + " " + time.toString();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object userID = request.getSession(false).getAttribute("id");
		List<String[]> userAuctions = getAuctions(new SimpleSelect("userAuctions", userID));
		List<String[]> auctionOffered = getAuctions(new SimpleSelect("auctionOffered", userID));
		List<String[]> auctionWon = getAuctionsWon(new SimpleSelect("auctionWon", userID));
		
		User user = new User((Integer) request.getSession().getAttribute("id"));
		String userCredit = "";
		String totalCredit = "";
		String occupiedCredit = "";
		
		try {
			userCredit = String.valueOf(user.getAviableCredit());
			totalCredit = String.valueOf(user.getPortfolio());
			occupiedCredit = String.valueOf(user.getPortfolio() - user.getAviableCredit());
		} catch (SQLiteFailRequestException e) {
			LOGGER.log(Level.SEVERE, MESSAGE_ERROR, e);
		}
		
		request.setAttribute("userAuctions", userAuctions);
		request.setAttribute("auctionOffered", auctionOffered);
		request.setAttribute("auctionWon", auctionWon);
		request.setAttribute("userCredit", userCredit);
		request.setAttribute("totalCredit", totalCredit);
		request.setAttribute("occupiedCredit", occupiedCredit);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("personalArea.jsp");
		dispatcher.forward(request, response);
	}

}
