package controller.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import controller.AuthenticationService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final AuthenticationService authenticationService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        authenticationService = new AuthenticationService();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		try {
			HashMap<String, Object> userValues = authenticationService.authenticate(request);
			
			if(userValues != null) {
				// Get the current session
				HttpSession oldSession = request.getSession(false);
				if(oldSession != null) {
					oldSession.invalidate(); // invalidates the current session if it exists
				}
				
				HttpSession currentSession = request.getSession(true); // create a new session
				
				currentSession.setAttribute("id", (int) userValues.get("ID"));
				currentSession.setAttribute("name", (String) userValues.get("Name"));
				currentSession.setAttribute("type", (String) userValues.get("Type"));
				
				response.sendRedirect("index"); // got to the next page
			} 
			else {
				// If authentication fails
				request.setAttribute("errorMessage", "Email o password errati!");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
				requestDispatcher.forward(request, response);
			}
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
	}

}
