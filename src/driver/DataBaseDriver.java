package driver;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

import recipes.Recipe;
import utils.Utils;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class DataBaseDriver {
	private static DataBaseDriver instanse;
	private MongoClient mongo;
	private DB db;
	private DBCollection collection;
	
	
	public static DataBaseDriver getInstanse(){
		if (instanse==null){
			instanse=new DataBaseDriver(Utils.getConfigFromFile());
		}
		return instanse;
	}
	
	public DataBaseDriver(String dbName, String collectionName){
		this.connection(dbName, collectionName);
	}

	private DataBaseDriver(HashMap<String, String> configFromFile) {
		// TODO Auto-generated constructor stub
	}

	public void insert(Recipe recipe) {
		BasicDBObject document = new BasicDBObject();
		document.append("name", recipe.getName());
		document.append("time required", recipe.getTimeRequired());
		document.append("ingredients", recipe.getIngredients());
		document.append("recipe", recipe.getRecipe());
		collection.insert(document);
	}
	
	
	
	public void cleanDB(){
		collection.drop();
	}

	public void printAllData(){
		printResult(getAll());
	}
	
	public ArrayList<Recipe> getAll(){
		System.out.println("All date:");
		DBCursor allData = collection.find();
		
		return convertCursorToArrayList(allData);
	}

	public ArrayList<Recipe> findByRecipeName(String recipeName){
		System.out.println("Recipe with name: "+recipeName);
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("name", recipeName);
		DBCursor cursor = collection.find(whereQuery);
		return convertCursorToArrayList(cursor);
	}
	
	public ArrayList<Recipe> findByIngredients(ArrayList<String> ingredients){
		BasicDBObject inQuery = new BasicDBObject();
		inQuery.put("ingredients", new BasicDBObject("$in", ingredients));
		DBCursor result = collection.find(inQuery);
		return convertCursorToArrayList(result);
	}
	
	public ArrayList<Recipe> findByTimeRequired(int maxTime){
		System.out.println("Recipe with time required less: "+String.valueOf(maxTime));
		BasicDBObject timeQuery = new BasicDBObject();
		timeQuery.put("time required", new BasicDBObject("$lt", maxTime));
		DBCursor result = collection.find(timeQuery);
		return convertCursorToArrayList(result);
	}
	
	private void connection(String dbName, String collectionName) {
		try {
			mongo = new MongoClient("localhost", 27017);
			db = mongo.getDB(dbName);
			collection = db.getCollection(collectionName);
		} catch (UnknownHostException e) {
			System.out.println("Error when connecting to the database");
		}

	}
	
	private void printResult(ArrayList<Recipe> searchResult){
		for(Recipe recipe: searchResult){
			System.out.println(recipe);
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Recipe> convertCursorToArrayList(DBCursor cursor){
		ArrayList<Recipe> result = new ArrayList<Recipe>();
		//TODO Real implementation
		try {
			while (cursor.hasNext()) {
				DBObject currentObject = cursor.next();
				Recipe recipe = new Recipe((String)currentObject.get("name"), (Integer)currentObject.get("time required"), (ArrayList<String>)currentObject.get("ingredients"), (String)currentObject.get("recipe"));
				result.add(recipe);
			}
			}finally {
				cursor.close();
			}
		
		return result;
	}
}
