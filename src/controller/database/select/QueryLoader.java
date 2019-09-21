package controller.database.select;

import java.util.Map;

import controller.ReadJSON;
/**
 * <p>
 * Singleton Design Pattern Applied
 * Note : files with the same name 
 * 	despite of their case will cause some 
 *  problems; this is because the key are 
 *  set to uppercase so we can ignore the cases in our code.
 *  
 * This loader use the eager strategy so if you add new
 * files you need to restart the program.
 *  <p>
 */

public class QueryLoader {
	private static QueryLoader instance;
	private static Map<String, QueryData> queries;
	
	private QueryLoader() {
		//TODO something that read from the queries.json
		queries = ReadJSON.read();
	}
	
	public static QueryLoader getInstance() {
		if(instance == null) {
			instance = new QueryLoader();
		}
		
		return instance;
	}
	
	public QueryData getQuery(String nameQuery) {
		return queries.get(nameQuery.toUpperCase());
	}
}
