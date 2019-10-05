package controller.servlet;

import java.io.IOException;

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
				String[][] auctions = new String[MAX_ITEMS_ON_ROW][3];
				
				for(int index = 0; index < MAX_ITEMS_ON_ROW && index < result.size(); index++) {
					auctions[index][0] = Integer.toString((Integer) result.getValue("ID", index));
					auctions[index][1] = (String) result.getValue("Title", index);
					auctions[index][2] = (String) result.getValue("Description", index);
				}
				
				request.setAttribute("auctions", auctions);
				
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		} catch (SQLiteFailRequestException e) {
			e.printStackTrace();
		}
	}
}
