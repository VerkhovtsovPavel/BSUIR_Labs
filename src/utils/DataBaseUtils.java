package utils;

import java.util.Date;

import recipes.Recipe;

import database.BasicDBObject;
import database.DB;
import database.DBCollection;
import database.DBCursor;
import database.MongoClient;

public class DataBaseUtils {
	private MongoClient mongo;
	private DB db;
	private DBCollection collection;

	public void insert(Recipe recipe) {
		BasicDBObject document = new BasicDBObject();
		document.append("name", recipe.getName());
		document.append("time required", recipe.getTimeRequired());
		document.append("ingredients", recipe.getIngredients());
		document.append("recipe", recipe.getRecipe());
		collection.insert(document);
	}
	
	public void getAll(){
		DBCursor cursor = collection.find();
	    try {
	       while(cursor.hasNext()) {
	           System.out.println(cursor.next());
	       }
	    } finally {
	       cursor.close();
	    }
	}
	
	public void connection(String dbName, String collectionName){
		   mongo = new MongoClient("localhost", 27017);
		   db = mongo.getDB(dbName);
		   collection = db.getCollection(collectionName);
	}
}
