package controller;

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
	
	public static Map<String, QueryData> read() {
		try (FileReader reader = new FileReader("queries.json"))
        {
            //Read JSON file
            Object object = jsonParser.parse(reader);
            Map<String, QueryData> map = new HashMap<String, QueryData>();
            JSONArray statementList = (JSONArray) object;
             
            statementList.forEach( statement -> parseSQLStatement( (JSONObject) statement ) );
            for(Object statement : statementList) {
        		
            }
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
	
	private static QueryData parseSQLStatement(JSONObject object)
    {
        JSONObject statementObject = (JSONObject) object.get("sqlAuthentication");
         
        String statement = (String) statementObject.get("statement");            
        String type[] = (String[]) statementObject.get("values"); 
        
        return new QueryData(statement, Arrays.asList(type));
    }
}
