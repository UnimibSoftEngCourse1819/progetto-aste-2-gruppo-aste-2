package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import controller.database.select.QueryData;

public class ReadJSON {
	private static JSONParser jsonParser = new JSONParser();
	private static final String PATH = File.separator + "WebContent" + File.separator + "json" + File.separator;
	private static final String FILE_NAME = "queries.json";
	
	public static Map<String, QueryData> read() {
		Map<String, QueryData> map = new HashMap<String, QueryData>();
		
		try (FileReader reader = new FileReader(System.getProperty("user.dir") + PATH + FILE_NAME))
        {
            //Read JSON file
            Object object = jsonParser.parse(reader);
            JSONArray statementList = (JSONArray) object;
             
            for(Object statement : statementList) {
            	JSONObject jObject = (JSONObject) statement;
            	JSONObject sqlObject = (JSONObject) jObject.get("sqlStatement");
            	
            	map.put((String)sqlObject.get("Name"), parseSQLStatement(sqlObject));
            }
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
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
