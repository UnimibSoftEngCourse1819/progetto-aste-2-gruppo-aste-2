package controller;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class ImageUploader {
	private static final String UPLOAD_DIRECTORY = "pictures";
	private static final String PATH = File.separator + "WebContent" + File.separator;
	private static final int THRESHOLD_SIZE = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
	
	private static DiskFileItemFactory configureUpload() {
		// configures upload settings
    	DiskFileItemFactory factory = new DiskFileItemFactory();
    	factory.setSizeThreshold(THRESHOLD_SIZE);
    	factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
    	
    	return factory;
	}
	
	public static boolean upload(HttpServletRequest request, ServletContext context) {
		boolean success = false;
		DiskFileItemFactory factory = configureUpload();
		
		ServletFileUpload upload = new ServletFileUpload(factory);
    	upload.setFileSizeMax(MAX_FILE_SIZE);
    	upload.setSizeMax(MAX_REQUEST_SIZE);
    	
    	// constructs the directory path to store upload file
    	String uploadPath = context.getRealPath("") + File.separator + UPLOAD_DIRECTORY;
    	
    	// creates the directory if it does not exists
    	File uploadDir =  new File(uploadPath);
    	if(!uploadDir.exists()) {
    		uploadDir.mkdir();
    	}
    	
    	try {
    		// parses the request's content to extract file data
            List<FileItem> formItems = upload.parseRequest(request);
            Iterator<FileItem> iter = formItems.iterator();
            
            // iterates over form's fields
            while(iter.hasNext()) {
            	FileItem item = (FileItem) iter.next();
            	
            	// processes only fields that are not fields
            	if(!item.isFormField()) {
            		String fileName = new File(item.getName()).getName();
            		String filePath = uploadPath + File.separator + fileName;
            		File storeFile = new File(filePath);
            		
            		// saves the file on disk
            		item.write(storeFile);
            		
            		success = true;
            	}
            }
            
    	} 
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	
		return success;
	}
}
