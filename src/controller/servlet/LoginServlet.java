package controller.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import controller.AuthenticationService;
import jdk.javadoc.internal.doclets.toolkit.util.Utils.Pair;

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
			Pair<Integer, String> userValues = authenticationService.authenticate(request);
			
			if(userValues != null && userValues.first > 0 && userValues.second != null) {
				// Get the current session
				HttpSession oldSession = request.getSession(false);
				if(oldSession != null) {
					oldSession.invalidate(); // invalidates the current session if it exists
				}
				
				HttpSession currentSession = request.getSession(true); // create a new session
				
				currentSession.setAttribute("id", userValues.first);
				currentSession.setAttribute("name", userValues.second);
				currentSession.setMaxInactiveInterval(5 * 60); // maximum five minutes of inactivity
				
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
