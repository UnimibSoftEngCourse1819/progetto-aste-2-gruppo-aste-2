package controller.servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.AuctionRequestManager;
import controller.ImageUploader;
import controller.MyLogger;
import exception.FailRollBackException;
import exception.InexistentTypeParameterException;
import exception.SQLiteFailRequestException;

/**
 * Servlet implementation class AuctionCreation
 */
@WebServlet("/auctionCreation")
@MultipartConfig
public class AuctionCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = MyLogger.getLoggerInstance(AuctionCreationServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuctionCreationServlet() {
        super();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	boolean successfulOperation = false;
    	
		try {						
			LinkedHashMap<String, String> values = ImageUploader.upload(request, getServletContext());
			AuctionRequestManager.createAuction(request, values);
			successfulOperation = true;
		} catch (SQLiteFailRequestException | InexistentTypeParameterException | FailRollBackException e) {
			LOGGER.log(Level.SEVERE, "Non è stato possibile gestire la richiesta", e);
		}
		
		if(successfulOperation) {
			try {
				response.sendRedirect("index");		
			}catch(IOException e) {
				//This shouldn't happen
			}
		}
    }
}
