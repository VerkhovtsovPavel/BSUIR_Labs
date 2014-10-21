package utils;

import java.util.Date;

import recipes.Recipe;

import database.BasicDBObject;
import database.DB;
import database.DBCollection;
import database.MongoClient;

public class DataBaseUtils {

	public void insert(Recipe recipe) {
		BasicDBObject document = new BasicDBObject();
		document.append("name", recipe.getName());
		document.append("time required", recipe.getTimeRequired());
		document.append("ingredients", recipe.getIngredients());
		collection.insert(document);
	}
	
	public void connection(){
		   MongoClient mongo = new MongoClient("localhost", 27017);
		   
		    // get database 
		    // if database doesn't exists, mongodb will create it for you
		    DB db = mongo.getDB("test");
		  
		    // get collection
		    // if collection doesn't exists, mongodb will create it for you
		    DBCollection collection = db.getCollection("person");
	}
}
