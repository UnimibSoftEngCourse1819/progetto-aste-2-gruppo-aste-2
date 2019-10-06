package controller.servlet;

import java.io.IOException;
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

/**
 * Servlet implementation class ConfigurationServlet
 */
@WebServlet("/configuration")
public class ConfigurationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = MyLogger.getLoggerInstance(ConfigurationServlet.class.getName());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfigurationServlet() {
        super();
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		try {
			SimpleSelect select = new SimpleSelect("auctionConfiguration", "FirstSealed");
			ResultDatabase result = DatabaseManager.executeSelect(select);
			
			request.setAttribute("type", "FirstSealed");
			request.setAttribute("penalty", result.getValue("Percentage", 0));
			request.setAttribute("slot", result.getValue("NSlot", 0));
			request.setAttribute("offers", result.getValue("NOffers", 0));
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("auctionManager.jsp");
			dispatcher.forward(request, response);
			
		} catch (SQLiteFailRequestException e) {
			LOGGER.log(Level.SEVERE, "Non è stato possibile gestire la richiesta", e);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			SimpleSelect select = new SimpleSelect("auctionConfiguration", request.getParameter("type"));
			ResultDatabase result = DatabaseManager.executeSelect(select);
			
			request.setAttribute("type", request.getParameter("type"));
			request.setAttribute("penalty", result.getValue("Percentage", 0));
			request.setAttribute("slot", result.getValue("NSlot", 0));
			request.setAttribute("offers", result.getValue("NOffers", 0));
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("auctionManager.jsp");
			dispatcher.forward(request, response);
		} catch(SQLiteFailRequestException e) {
			LOGGER.log(Level.SEVERE, "Non è stato possibile gestire la richiesta", e);
		}
	}
}
