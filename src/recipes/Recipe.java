package recipes;

import java.util.ArrayList;
import java.util.Collections;

public class Recipe {
	private String name;
	private int timeRequired;
	private ArrayList<String> ingredients;
	private String recipe;

	public Recipe(String name, int timeRequired, ArrayList<String> ingredients, String recipe) {
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
	}

	private void setIngredients(ArrayList<String> ingredients) {
		this.ingredients = ingredients;
		Collections.sort(this.ingredients);
	}

	public String getRecipe() {
		return recipe;
	}

}
