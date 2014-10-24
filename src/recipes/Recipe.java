package recipes;

import java.util.ArrayList;

public class Recipe {
	private String name;
	private int timeRequired;
	private ArrayList<String> ingredients;
	private String recipe;
	
	public Recipe(String name, int timeRequired, ArrayList<String> ingredients, String recipe){
		this.setName(name);
		this.setTimeRequired(timeRequired);
		this.setIngredients(ingredients);
		this.recipe = recipe;
	}
	
	
	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}


	public int getTimeRequired() {
		return timeRequired;
	}


	private void setTimeRequired(int timeRequired) {
		this.timeRequired = timeRequired;
	}


	public ArrayList<String> getIngredients() {
		return ingredients;
		//TODO Add convert to string array
	}


	private void setIngredients(ArrayList<String> ingredients) {
		this.ingredients = ingredients;
	}
	
	public String getRecipe(){
		return recipe;
	}
	
	
	
}
