package controller.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.AuctionRequestManager;
import controller.ImageUploader;
import exception.FailRollBackException;
import exception.InexistentTypeParameterException;
import exception.SQLiteFailRequestException;

/**
 * Servlet implementation class AuctionCreation
 */
@WebServlet("/auctionCreation")
@MultipartConfig
public class AuctionCreation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuctionCreation() {
        super();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	boolean successfulOperation = false;
    	
		try {						
			ImageUploader.upload(request, getServletContext());
			AuctionRequestManager.createAuction(request);
			successfulOperation = true;
		} catch (SQLiteFailRequestException | InexistentTypeParameterException | FailRollBackException e) {
			e.printStackTrace();
		}
		
		if(successfulOperation) {
			try {
				response.sendRedirect("index");		
			}catch(Exception e) {
				//This shouldn't happen
				e.printStackTrace();
			}
		}
    }
}
