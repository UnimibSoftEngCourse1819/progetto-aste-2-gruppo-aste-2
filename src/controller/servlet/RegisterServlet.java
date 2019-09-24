package controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.AuthenticationService;
import exception.InvalidParameterException;
import exception.SQLiteFailRequestException;
import model.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AuthenticationService authService;   
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
    	super.init();
    	
    	authService = new AuthenticationService();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			areValidParameter(request);

			User user = new User(request);
			authService.registerUser(user);
			
			response.sendRedirect("index.jsp");
		} catch (InvalidParameterException | SQLiteFailRequestException e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("registration.jsp").forward(request, response);
		}
	}

	private void areValidParameter(HttpServletRequest request) throws InvalidParameterException {
		String password = request.getParameter("password");
		String passwordRep = request.getParameter("passwordRep");
		
		if(!password.equals(passwordRep)) {			
			throw new InvalidParameterException("Le password non coincidono!");
		}
	}
}
