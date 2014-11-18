package algorithms;

import java.util.ArrayList;
import java.util.HashMap;

import utils.Utils;

public class PlotterSupporter {
	private ArrayList<String> ingredients;
	private String recipeName;
	private int recipeNumber;
	private HashMap<ArrayList<String>, Integer> ways;


	public PlotterSupporter(String recipeName, ArrayList<String> ingredients, int recipeNumber) {
		this.recipeNumber = recipeNumber;
		this.recipeName = recipeName;
		this.setIngredients(Utils.deepCloning(ingredients));
		ways = new HashMap<>();
	}

	public int getRecipeNumber(){
		return recipeNumber;
	}
	
	public ArrayList<String> getIngredients() {
		return ingredients;
	}

	@SuppressWarnings("unchecked")
	public void setIngredients(Object ingredients) {
		this.ingredients = (ArrayList<String>)ingredients;
	}
	
	public String getRecipeName(){
		return recipeName;
	}
	
	public HashMap<ArrayList<String>, Integer> getWayMap(){
		return ways;
	}
	
	
}
