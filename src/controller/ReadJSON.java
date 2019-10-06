package controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import controller.database.select.QueryData;


/**
 * This class for now is used
 * only for reading the json file of
 * to get the queries however this class
 * should be used also to read a list of auction
 * 
 */
public class ReadJSON {
	private static JSONParser jsonParser = new JSONParser();
	private static final String PATH = File.separator + "WebContent" + File.separator + "json" + File.separator;
	private static final String FILE_NAME = "queries.json";
	private static final Logger LOGGER = MyLogger.getLoggerInstance(ReadJSON.class.getName());
	
	private ReadJSON() {
		throw new IllegalStateException("Utility class");
	}
	
	public static Map<String, QueryData> read() {
		Map<String, QueryData> map = new HashMap<>();
		
		try (FileReader reader = new FileReader(System.getProperty("user.dir") + PATH + FILE_NAME))
        {
            //Read JSON file
            Object object = jsonParser.parse(reader);
            JSONArray statementList = (JSONArray) object;
             
            for(Object statement : statementList) {
            	JSONObject jObject = (JSONObject) statement;
            	JSONObject sqlObject = (JSONObject) jObject.get("sqlStatement");
            	
            	map.put(((String)sqlObject.get("name")).toUpperCase(), parseSQLStatement(sqlObject));
            }
 
        } catch (IOException  | ParseException e) {
            LOGGER.log(Level.SEVERE, "Non è stato possibile leggere il file json", e);
        }
		
		return map;
	}
	
	private static QueryData parseSQLStatement(JSONObject object)
    {   
        String statement = (String) object.get("statement");            
        String type = (String) object.get("values"); 
        
        return new QueryData(statement, Arrays.asList(type.split("-")));
    }
}
