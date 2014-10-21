package recipes;

import java.util.ArrayList;

public class Recipe {
	private String name;
	private int timeRequired;
	private ArrayList<String> ingredients;
	
	public Recipe(String name, int timeRequired, ArrayList<String> ingredients){
		this.setName(name);
		this.setTimeRequired(timeRequired);
		this.setIngredients(ingredients);
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getTimeRequired() {
		return timeRequired;
	}


	public void setTimeRequired(int timeRequired) {
		this.timeRequired = timeRequired;
	}


	public ArrayList<String> getIngredients() {
		return ingredients;
		//TODO Add convert to string array
	}


	public void setIngredients(ArrayList<String> ingredients) {
		this.ingredients = ingredients;
	}
	
}
