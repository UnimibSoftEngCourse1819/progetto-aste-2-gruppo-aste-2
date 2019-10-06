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

import controller.DatabaseManager;
import controller.MyLogger;
import controller.database.ResultDatabase;
import controller.database.select.SelectComponent;
import controller.database.select.SimpleSelect;
import controller.database.select.decorator.OrderBy;
import exception.SQLiteFailRequestException;

/**
 * Servlet implementation class CatalogueServlet
 */
@WebServlet("/catalogue")
public class CatalogueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = MyLogger.getLoggerInstance(CatalogueServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CatalogueServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		try {
			SelectComponent select = new SimpleSelect("categories");
			select = new OrderBy(select, "Name");
			ResultDatabase result = DatabaseManager.executeSelect(select);
			
			if(!result.isEmpty()) {
				List<String> categories = new ArrayList<>();
				int index = 0;
				
				while(result.getValue("ID", index) != null) {
					categories.add((String) result.getValue("Name", index));
					++index;
				}
				
				request.setAttribute("categories", categories);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("displayAuctions.jsp");
				dispatcher.forward(request, response);
			}
		} catch (SQLiteFailRequestException e) {
			LOGGER.log(Level.SEVERE, "Non è stato possibile gestire la richiesta", e);
		}
	}

}
