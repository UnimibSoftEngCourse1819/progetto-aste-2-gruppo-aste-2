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
import controller.database.select.SimpleSelect;
import exception.SQLiteFailRequestException;

/**
 * Servlet implementation class personalAreaServlet
 */
@WebServlet("/personalArea")
public class PersonalAreaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonalAreaServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			SimpleSelect select = new SimpleSelect("userAuctions", request.getSession(false).getAttribute("id"));
			ResultDatabase result = DatabaseManager.executeSelect(select);
			
			if(!result.isEmpty()) {
				int index = 0;
				List<String[]> userAuctions = new ArrayList<>();			
						
				while(result.getValue("Seller", index) != null) {
					String[] data = new String[4];
					
					data[0] = Integer.toString((Integer) result.getValue("ID", index));
					data[1] = (String) result.getValue("Title", index);
					data[2] = (String) result.getValue("Description", index);
					data[3] = result.getValue("Creation", index).toString();
					
					userAuctions.add(data);
					++index;
				}
				
				request.setAttribute("userAuctions", userAuctions);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("personalArea.jsp");
				dispatcher.forward(request, response);
			}
		} catch (SQLiteFailRequestException e) {
			e.printStackTrace();
		}
	}

}
