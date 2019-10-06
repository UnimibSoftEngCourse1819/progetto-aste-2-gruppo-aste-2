package controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DatabaseManager;
import controller.database.ResultDatabase;
import controller.database.select.SimpleSelect;
import exception.SQLiteFailRequestException;

/**
 * Servlet implementation class ReturnObjectServlet
 */
@WebServlet("/returnObject")
public class ReturnObjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		try {
			SimpleSelect select = new SimpleSelect("returnObject", request.getAttribute("id"));
			ResultDatabase result = DatabaseManager.executeSelect(select);
			
			if((Integer) result.getValue("Penalty", 0) == 1) {
				int percentage = (Integer) result.getValue("Percentage", 0);
				int payment = Integer.parseInt(request.getParameter("Price")) * percentage / 100;
			}
			else {
				
			}
		} catch (SQLiteFailRequestException e) {
			e.printStackTrace();
		}
	}

}
