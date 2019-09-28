package controller.servlet;

import java.io.IOException;

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
 * Servlet implementation class AuctionServlet
 */
@WebServlet("/auction")
public class AuctionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuctionServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			SimpleSelect select = new SimpleSelect("auction", Integer.parseInt(request.getParameter("id")));
			ResultDatabase result = DatabaseManager.executeSelect(select);
			
			if(!result.isEmpty()) {
				String[] auction = new String[3];
				
				auction[0] = Integer.toString((Integer) result.getValue("ID", 0));
				auction[1] = (String) result.getValue("Title", 0);
				auction[2] = (String) result.getValue("Description", 0);
				
				request.setAttribute("auction", auction);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("auction.jsp");
				dispatcher.forward(request, response);
			}
		} catch (SQLiteFailRequestException e) {
			e.printStackTrace();
		}
		
		
	}
}
