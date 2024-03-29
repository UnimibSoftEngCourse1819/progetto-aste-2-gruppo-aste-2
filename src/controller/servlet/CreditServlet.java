package controller.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DatabaseManager;
import controller.MyLogger;
import controller.database.SQLOperation;
import controller.database.SQLParameter;
import controller.database.UpdateOperation;
import exception.FailRollBackException;
import exception.SQLiteFailRequestException;
import model.User;

/**
 * Servlet implementation class CreditServlet
 */
@WebServlet("/addCredit")
public class CreditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = MyLogger.getLoggerInstance(CreditServlet.class.getName());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreditServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User((Integer) request.getSession().getAttribute("id"));
		try {
			user.loadCredit();
		} catch (SQLiteFailRequestException e1) {
			LOGGER.log(Level.SEVERE, "Non � stato possibile gestire la richiesta", e1);
		}
		
		int currentCredit = user.getPortfolio();
		int creditToAdd = Integer.parseInt(request.getParameter("nCredit"));
		
		LinkedHashMap<String, SQLParameter> valueToChange = new LinkedHashMap<>();
		valueToChange.put("Credit", new SQLParameter(SQLParameter.INTEGER, currentCredit + creditToAdd));
		
		LinkedHashMap<String, SQLParameter> clauses = new LinkedHashMap<>();
		clauses.put("ID", new SQLParameter(SQLParameter.INTEGER, user.getId()));
		
		UpdateOperation update = new UpdateOperation("user", clauses, valueToChange);
		List<SQLOperation> operations = new ArrayList<>();
		operations.add(update);
		
		try {
			DatabaseManager.execute(operations);
		} catch (SQLiteFailRequestException | FailRollBackException e) {
			LOGGER.log(Level.SEVERE, "Non � stato possibile gestire la richiesta", e);
		} 
		
		response.sendRedirect("personalArea");
	}

}
