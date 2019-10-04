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
 * Servlet implementation class CreditServlet
 */
@WebServlet("/addCredit")
public class CreditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		int currentCredit = Integer.parseInt(request.getParameter("credit"));
		int creditToAdd = Integer.parseInt(request.getParameter("nCredit"));
		
		LinkedHashMap<String, SQLParameter> valueToChange = new LinkedHashMap<>();
		valueToChange.put("Credit", new SQLParameter(SQLParameter.INTEGER, currentCredit + creditToAdd));
		
		LinkedHashMap<String, SQLParameter> clauses = new LinkedHashMap<>();
		clauses.put("ID", new SQLParameter(SQLParameter.INTEGER, request.getSession().getAttribute("id")));
		
		UpdateOperation update = new UpdateOperation("user", clauses, valueToChange);
		List<SQLOperation> operations = new ArrayList<>();
		operations.add(update);
		
		try {
			DatabaseManager.execute(operations);
		} catch (SQLiteFailRequestException | FailRollBackException e) {
			e.printStackTrace();
		} 
		
		response.sendRedirect("personalArea");
	}

}
