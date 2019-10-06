package controller;

import java.io.File;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Applied Singleton Design Pattern
 * Note comment that folderPath if you're testing on Eclipse
 * @see FileLogFormatter
 * 
 */

public class MyLogger {
	private static FileHandler fileLog = null;
    private static final String FILE_LOG_NAME = "AsteOnline.log";
    
    private MyLogger() {
    	//This shouldn't be called
    }
    
    private static void createFileHandler() {
        try {
        	String folderPath = System.getProperty("user.dir") + File.separator;
        	
            fileLog = new FileHandler(folderPath  + FILE_LOG_NAME, true);
            fileLog.setFormatter(new FileLogFormatter());
            fileLog.setLevel(Level.FINEST);
        } catch (Exception e) {
            throw new FileHandlerCreationException(e);
        }

    }

    public static Logger getLoggerInstance(String className) {
    	Logger result = Logger.getLogger(className);
    	if(fileLog == null) {
    		createFileHandler();
    	}
    	result.addHandler(fileLog);
    	for(Handler singleHandler : result.getHandlers()) {
    		singleHandler.setLevel(Level.FINEST);
    	}
        return result;
    }

    public static String getFileLogName() {
        return FILE_LOG_NAME;
    }

    public static class FileHandlerCreationException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public FileHandlerCreationException(Exception e) {
            super(e);
        }
    }
}