package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.database.ResultDatabase;
import controller.database.select.SimpleSelect;
import exception.SQLiteFailRequestException;

/**
 * Singleton Design Pattern Applied
 * @author Vallero
 *
 */
public class CategoryAuction {
	private static CategoryAuction instance = null;
	private final Map<String,Integer> categoryList;
	private final String queryName = "Category";//TODO da aggiungere nel file json deve prendere tutta la tabella category
	
	private CategoryAuction(){
		categoryList = new HashMap<>();
		SimpleSelect select = new SimpleSelect(queryName);
		try {
			ResultDatabase result = DatabaseManager.executeSelect(select);
			
			List<Object> temporaryList = result.getValuesList("Name");
			List<String> listName = ResultDatabase.castListInto(String.class, temporaryList);
			temporaryList = result.getValuesList("ID");
			List<Integer> listID = ResultDatabase.castListInto(Integer.class, temporaryList);
			
			for(int indexValue = 0; indexValue < listID.size(); indexValue++) {
				categoryList.put(listName.get(indexValue), listID.get(indexValue));
			}
			
		} catch (SQLiteFailRequestException e) {
			e.printStackTrace();
		}
	}
	
	public static CategoryAuction getInstance() {
		if (instance == null) {
			instance = new CategoryAuction();
		}
		return instance;
	}
	
	
	
}
