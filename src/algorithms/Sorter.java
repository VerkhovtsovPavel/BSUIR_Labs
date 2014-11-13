package algorithms;

import java.util.ArrayList;
import java.util.Collections;

import recipes.Recipe;
import utils.Utils;
import driver.DataBaseDriver;

public class Sorter {

	private ArrayList<String> request;

	private ArrayList<Recipe> allFoundRicepe;
	private ArrayList<Recipe> firstGeneration;
	private ArrayList<Recipe> secondGeneration;
	private ArrayList<Recipe> thirdGenerartion;

	public Sorter(ArrayList<String> request) {
		this.setRequest(request);
		allFoundRicepe = DataBaseDriver.getInstanse()
				.findByIngredients(request);
		createGenerations();

	}

	private void setRequest(ArrayList<String> request) {
		this.request = request;
		Collections.sort(this.request);
	}

	private void createGenerations() {
		initialazeGeneration();
		createFirstGeneration();
		createSecondGeration();

	}

	@SuppressWarnings("unchecked")
	private void createSecondGeration() {

		ArrayList<Recipe> templateRecipe = (ArrayList<Recipe>) Utils
				.deepCloning(thirdGenerartion);

		for (Recipe recipe : templateRecipe) {
			for (String ingredient : request) {
				recipe.getIngredients().remove(ingredient);
			}
		}

		for (int i = 0; i < templateRecipe.size(); i++) {
			if (templateRecipe.get(i).getIngredients().size() == 1) {
				secondGeneration.add(thirdGenerartion.get(i));
				thirdGenerartion.remove(i);
			}
		}

	}

	@SuppressWarnings("unchecked")
	private void initialazeGeneration() {
		firstGeneration = new ArrayList<>();
		secondGeneration = new ArrayList<>();
		thirdGenerartion = (ArrayList<Recipe>) allFoundRicepe.clone();
	}

	private void createFirstGeneration() {
		for (int i = 0; i < thirdGenerartion.size(); i++) {
			if (request.containsAll(thirdGenerartion.get(i).getIngredients())) {
				firstGeneration.add(thirdGenerartion.get(i));
				thirdGenerartion.remove(i);
			}
		}
	}

	public ArrayList<Recipe> getAllFoundRicepe() {
		return allFoundRicepe;
	}

	public ArrayList<Recipe> getFirstGeneration() {
		return firstGeneration;
	}

	public ArrayList<Recipe> getSecondGeneration() {
		return secondGeneration;
	}

	public ArrayList<Recipe> getThirdGenerartion() {
		return thirdGenerartion;
	}
}
