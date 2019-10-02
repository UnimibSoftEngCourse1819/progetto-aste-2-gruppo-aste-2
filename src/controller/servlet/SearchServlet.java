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
		try {
			SelectComponent select = new SimpleSelect("auctions");
			
			List<SQLParameter> whereClause = new ArrayList<>();
			whereClause.add(new SQLParameter(SQLParameter.VARCHAR, Auction.ON_GOING));
			whereClause.add(new SQLParameter(SQLParameter.VARCHAR, Auction.STANDBY));
			select = new Where(select, "AND (auction.Status = ?  OR auction.Status = ? ", whereClause);
			
			OrderBy orderedSelect = new OrderBy(select, "Creation");
			
			ResultDatabase result = DatabaseManager.executeSelect(orderedSelect);
			
			if(!result.isEmpty()) {
				int index = 0;
				List<String[]> auctions = new ArrayList<>();
				
				while(index < MAX_ITEMS_ON_PAGE && result.getValue("ID", index) != null) {
					String[] temporaryArray = new String[3];
					temporaryArray[0] = Integer.toString((Integer) result.getValue("ID", index));
					temporaryArray[1] = (String) result.getValue("Title", index);
					temporaryArray[2] = (String) result.getValue("Description", index);
					index++;
				}
				
				request.setAttribute("auctions", auctions.toArray());
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
			}
		} catch (SQLiteFailRequestException e) {
			e.printStackTrace();
		}
	}
}
