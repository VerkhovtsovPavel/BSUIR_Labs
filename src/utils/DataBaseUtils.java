package utils;

import java.io.IOException;
import java.net.UnknownHostException;

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
		DBCursor cursor = collection.find();
		try {
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}
		} finally {
			cursor.close();
		}
	}

	public void connection(String dbName, String collectionName) {
		try {
			mongo = new MongoClient("localhost", 27017);
			db = mongo.getDB(dbName);
			collection = db.getCollection(collectionName);
		} catch (UnknownHostException e) {
			System.out.println("Error when connecting to the database");
		}

	}
}
