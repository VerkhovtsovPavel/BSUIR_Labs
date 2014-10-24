package utils;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import recipes.Recipe;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class DataBaseUtils {
	private MongoClient mongo;
	private DB db;
	private DBCollection collection;
	
	public DataBaseUtils(String dbName, String collectionName){
		this.connection(dbName, collectionName);
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

	public void getAll() throws IOException {
		DBCursor allData = collection.find();
		printResult(allData);
	}

	public void findByRecipeName(String recipeName){
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("name", recipeName);
		DBCursor cursor = collection.find(whereQuery);
		printResult(cursor);
	}
	
	public void findByIngredients(ArrayList<String> ingredients){
		 System.out.println("\n2. Find where number in 2,4 and 5");
		  BasicDBObject inQuery = new BasicDBObject();
		  inQuery.put("ingredients", new BasicDBObject("$in", ingredients));
		  DBCursor cursor4 = collection.find(inQuery);
		 printResult(cursor4);
	}
	
	public void findByTimeRequired(int maxTime){
		BasicDBObject gtQuery = new BasicDBObject();
		gtQuery.put("time requiredr", new BasicDBObject("$lt", maxTime));
		DBCursor cursor = collection.find(gtQuery);
		printResult(cursor);
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
	
	private void printResult(DBCursor searchResult){
		try {
			while (searchResult.hasNext()) {
				System.out.println(searchResult.next());
			}
		} finally {
			searchResult.close();
		}
	}
}
