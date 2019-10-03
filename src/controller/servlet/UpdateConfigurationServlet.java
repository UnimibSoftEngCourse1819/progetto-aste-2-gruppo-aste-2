package controller.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DatabaseManager;
import controller.database.SQLOperation;
import controller.database.SQLParameter;
import controller.database.UpdateOperation;
import exception.FailRollBackException;
import exception.SQLiteFailRequestException;

/**
 * Servlet implementation class UpdateConfigurationServlet
 */
@WebServlet("/update")
public class UpdateConfigurationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateConfigurationServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<SQLOperation> operations = new ArrayList<>();
		
		LinkedHashMap<String, SQLParameter> clauses = new LinkedHashMap<>();
		clauses.put("Name", new SQLParameter(SQLParameter.VARCHAR, request.getParameter("type")));
		
		LinkedHashMap<String, SQLParameter> valuesToChange = new LinkedHashMap<>();
		valuesToChange.put("Percentage", new SQLParameter(SQLParameter.INTEGER, Integer.parseInt(request.getParameter("penalty"))));
		valuesToChange.put("NSlot", new SQLParameter(SQLParameter.INTEGER, Integer.parseInt(request.getParameter("slot"))));
		valuesToChange.put("NOffers", new SQLParameter(SQLParameter.INTEGER, Integer.parseInt(request.getParameter("offers"))));
		
		UpdateOperation update = new UpdateOperation("auctionConfiguration", clauses, valuesToChange);
		operations.add(update);
		
		try {
			DatabaseManager.execute(operations);
			response.sendRedirect("configuration");
		} catch (SQLiteFailRequestException | FailRollBackException e) {
			e.printStackTrace();
		}
	}

}
