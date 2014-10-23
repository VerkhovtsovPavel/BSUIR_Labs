package main;

import java.io.IOException;
import java.util.ArrayList;

import recipes.Recipe;

import utils.DataBaseUtils;

public class Main {

	
	public static void main(String[] args) throws IOException{
		DataBaseUtils dataBaseUtils = new DataBaseUtils();
		dataBaseUtils.connection("test", "person");
		dataBaseUtils.cleanDB();
		ArrayList<String> ref = new ArrayList<String>();
		ref.add("32");
		Recipe recipe = new Recipe("fff", 25, ref, "dfyhdjkl,");
		dataBaseUtils.insert(recipe);
		dataBaseUtils.getAll();
	}
}
