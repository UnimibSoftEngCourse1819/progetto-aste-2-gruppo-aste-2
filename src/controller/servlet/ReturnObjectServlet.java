package controller.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
import controller.database.SQLOperation;
import controller.database.SQLParameter;
import controller.database.UpdateOperation;
import controller.database.select.SimpleSelect;
import exception.FailRollBackException;
import exception.SQLiteFailRequestException;
import model.User;

/**
 * Servlet implementation class ReturnObjectServlet
 */
@WebServlet("/returnObject")
public class ReturnObjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = MyLogger.getLoggerInstance(ReturnObjectServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReturnObjectServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<SQLOperation> operations = new ArrayList<>();
		boolean operationOk = true;
		
		try {
			SimpleSelect select = new SimpleSelect("returnObject", Integer.parseInt(request.getParameter("id")));
			ResultDatabase result = DatabaseManager.executeSelect(select);
			
			User user = new User((Integer) request.getSession().getAttribute("id"));
			user.loadCredit();
			
			int credit = user.getPortfolio();
			int price = Integer.parseInt(request.getParameter("price"));
			int percentage = (Integer) result.getValue("Percentage", 0);
			int payment = price * percentage / 100;
			int penalty = (Integer) result.getValue("Penalty", 0);
			
			LinkedHashMap<String, SQLParameter> valueToChange = new LinkedHashMap<>();
			
			if(penalty == 0) {
				valueToChange.put("Credit", new SQLParameter(SQLParameter.INTEGER, credit + price));
			}
			
			if(penalty == 1) {
				if(credit >= payment) {
					valueToChange.put("Credit", new SQLParameter(SQLParameter.INTEGER, credit - payment));
				}
				else {
					request.setAttribute("errorMessage", "Credito non sufficiente!");
				}
			}
			
			valueToChange.put("Waiver", new SQLParameter(SQLParameter.VARCHAR, "Rinunciato"));
			
			LinkedHashMap<String, SQLParameter> clauses = new LinkedHashMap<>();
			clauses.put("ID", new SQLParameter(SQLParameter.INTEGER, user.getId()));
			
			UpdateOperation update = new UpdateOperation("user", clauses, valueToChange);
			
			operations.add(update);		
		} catch (SQLiteFailRequestException e) {
			LOGGER.log(Level.SEVERE, "Non è stato possibile gestire la richiesta", e);
		}
		
		if(operationOk) {
			try {
				DatabaseManager.execute(operations);
			} catch (SQLiteFailRequestException | FailRollBackException e) {
				LOGGER.log(Level.SEVERE, "Non è stato possibile gestire la richiesta", e);
			}
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("personalArea");
		requestDispatcher.forward(request, response);
	}

}
