package controller.servlet;

import java.io.IOException;
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

import controller.CategoryAuction;
import controller.DatabaseManager;
import controller.MyLogger;
import controller.database.ResultDatabase;
import controller.database.SQLParameter;
import controller.database.select.SelectComponent;
import controller.database.select.SimpleSelect;
import controller.database.select.decorator.OrderBy;
import controller.database.select.decorator.Where;
import exception.SQLiteFailRequestException;
import model.auction.Auction;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int MAX_ITEMS_ON_PAGE = 50;
	private static final String ALL_TYPE = "tutte";
    private static final String CATEGORIES = "categories";
	private static final String SEARCH = "search";
	private static final String NOT_OPENED = "notOpened";
	private static final Logger LOGGER = MyLogger.getLoggerInstance(SearchServlet.class.getName());
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(CATEGORIES, CategoryAuction.getInstance().getCategoryList());
		
		if(request.getParameter(SEARCH) != null) {
			try {
				SelectComponent select = getSelectWithFilter(request);
				
				ResultDatabase result = DatabaseManager.executeSelect(select);
				
				if(!result.isEmpty()) {
					int index = 0;
					List<String[]> auctions = new ArrayList<>();
					
					while(index < MAX_ITEMS_ON_PAGE && result.getValue("ID", index) != null) {
						String[] temporaryArray = new String[4];
						temporaryArray[0] = Integer.toString((Integer) result.getValue("ID", index));
						temporaryArray[1] = (String) result.getValue("Title", index);
						temporaryArray[2] = (String) result.getValue("Description", index);
						temporaryArray[3] = (String) result.getValue("Image", index);
						
						auctions.add(temporaryArray);
						index++;
					}
					
					request.setAttribute("auctions", auctions);
				}
			} catch (SQLiteFailRequestException e) {
				LOGGER.log(Level.SEVERE, "Non è stato possibile gestire la richiesta", e);
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("displayAuctions.jsp");
		dispatcher.forward(request, response);
	}

	private SelectComponent getSelectWithFilter(HttpServletRequest request) {
		SelectComponent select = new SimpleSelect("auctionsSearch");
		
		if(!request.getParameter(SEARCH).isEmpty()) {
			select = new Where(select, "AND (auction.title Like (?) OR auction.description Like (?)) ",
					new SQLParameter(SQLParameter.VARCHAR, request.getParameter(SEARCH)),
					new SQLParameter(SQLParameter.VARCHAR, request.getParameter(SEARCH)));
		}
		
		if(!request.getParameter("auctionType").equals(ALL_TYPE)) {
			select = new Where(select, "AND auction.Type = ? ", 
					 new SQLParameter(SQLParameter.VARCHAR, request.getParameter("auctionType")));
		}
		
		if(!request.getParameter("category").equals(ALL_TYPE)) {
			select = new Where(select, "AND auction.Type = ? ", 
					 new SQLParameter(SQLParameter.VARCHAR, request.getParameter(CATEGORIES)));
		}
		
		if(request.getParameter(NOT_OPENED) != null || request.getParameter("opened") != null) {
			select = applyStatusFilter(select, request);
		}
		
		select = new OrderBy(select, "Conclusion");
		
		return select;
	}

	private SelectComponent applyStatusFilter(SelectComponent select, HttpServletRequest request) {
		if(request.getParameter(NOT_OPENED) != null && request.getParameter("opened") != null) {
			select = new Where(select, "AND (auction.Status = ? OR auction.Status = ?) ", 
					new SQLParameter(SQLParameter.VARCHAR, Auction.STANDBY),
					new SQLParameter(SQLParameter.VARCHAR, Auction.ON_GOING));
		}else {
			if(request.getParameter(NOT_OPENED) != null ){
				select = new Where(select, "AND auction.Status = ? ", 
						new SQLParameter(SQLParameter.VARCHAR, Auction.STANDBY));
			}else {
				select = new Where(select, "AND auction.Status = ? ", 
						new SQLParameter(SQLParameter.VARCHAR, Auction.ON_GOING));
			}
		}
		return select;
	}
}
