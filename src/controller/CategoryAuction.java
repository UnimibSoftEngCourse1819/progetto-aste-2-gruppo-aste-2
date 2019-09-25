package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.database.ResultDatabase;
import controller.database.SQLOperation;
import controller.database.SQLParameter;
import controller.database.select.SimpleSelect;
import controller.database.utilformodel.SQLiteData;
import controller.database.utilformodel.Storable;
import exception.FailRollBackException;
import exception.SQLiteFailRequestException;
import model.Category;

/**
 * Singleton Design Pattern Applied
 * @author Vallero
 *
 */
public class CategoryAuction {
	private static CategoryAuction instance = null;
	private static Map<String,Integer> categoryList;
	private static final String QUERY_NAME = "categories";
	
	private CategoryAuction(){
		loadData();
	}
	
	private static void loadData() {
		categoryList = new HashMap<>();
		SimpleSelect select = new SimpleSelect(QUERY_NAME);
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

	public void addMissing(List<String> auctionCategory) {
		List<String> newCategory = new ArrayList<>();
		for(String singleAuction : auctionCategory) {
			if(!categoryList.containsKey(singleAuction)) {
				newCategory.add(singleAuction);
			}
		}
		if(!newCategory.isEmpty()) {
			List<Storable> categoriesToAdd = new ArrayList<>();

			for(String singleCategory : newCategory) {
				categoriesToAdd.add(new Category(singleCategory));
			}
			
			try {
				DatabaseManager.create(categoriesToAdd);
			} catch (SQLiteFailRequestException | FailRollBackException e) {
				// This shouldn't happen
				e.printStackTrace();
			}
			
			loadData();
		}
	}

	public static List<SQLOperation> getSQLInsert(List<String> auctionCategory, Integer auction) {
		List<SQLOperation> relations = new ArrayList<>();

		for(String singleAuction : auctionCategory) {
			SQLiteData temporarySQLData = new SQLiteData("auctionCategory");
			temporarySQLData.add("auction", SQLParameter.INTEGER , auction);
			Integer idAuction = categoryList.get(singleAuction);
			temporarySQLData.add("category", SQLParameter.INTEGER , idAuction);
			
			relations.add(temporarySQLData);
		}
		return relations;
	}
	
	
}
