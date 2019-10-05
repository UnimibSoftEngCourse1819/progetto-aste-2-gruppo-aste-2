package controller.servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.AuctionReaper;
import controller.DatabaseManager;
import controller.database.ResultDatabase;
import controller.database.SQLParameter;
import controller.database.select.SelectComponent;
import controller.database.select.SimpleSelect;
import controller.database.select.decorator.Where;
import exception.SQLiteFailRequestException;
import model.auction.Auction;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int MAX_ITEMS_ON_ROW = 10;
	private boolean initializedEnderManager = false;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if(!initializedEnderManager) {
    		AuctionReaper.getInstance();
    	}
    	
		try {
			SelectComponent select = new SimpleSelect("auctions");
			select = new Where(select, "AND Status <> ? ", 
					new SQLParameter(SQLParameter.VARCHAR, Auction.ENDED));
			ResultDatabase result = DatabaseManager.executeSelect(select);
			
			if(!result.isEmpty()) {
				List<List<String>> auctions = new ArrayList<>();
				
				for(int index = 0; index < MAX_ITEMS_ON_ROW && index < result.size(); index++) {
					List<String> temp = new ArrayList<>();
					
					temp.add(Integer.toString((Integer) result.getValue("ID", index)));
					temp.add((String) result.getValue("Title", index));
					temp.add((String) result.getValue("Description", index));
					temp.add((String) result.getValue("Image", index));
					
					auctions.add(temp);
				}
				
				request.setAttribute("auctions", auctions);
				
			}
			
			LocalDateTime today = LocalDateTime.now();
			today = today.minusHours(today.getHour());
			today = today.minusMinutes(today.getMinute());
			today = today.minusSeconds(today.getSecond());
			today = today.minusNanos(today.getNano());
			
			LocalDateTime tomorrow = today.plusDays(1);
			
			SelectComponent selectLast = new SimpleSelect("closingAuction", today);
			selectLast = new Where(selectLast, "AND Conclusion >= ?", new SQLParameter(SQLParameter.DATE_TIME, tomorrow));
			
			ResultDatabase resultLast = DatabaseManager.executeSelect(select);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		} catch (SQLiteFailRequestException e) {
			e.printStackTrace();
		}
	}
}
