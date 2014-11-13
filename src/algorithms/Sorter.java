package algorithms;

import java.util.ArrayList;

import recipes.Recipe;
import driver.DataBaseDriver;

public class Sorter {
	
	private ArrayList<String> request;
	
	private ArrayList<Recipe> allFoundRicepe;
	private ArrayList<Recipe> firstGeneration;
	private ArrayList<Recipe> secondGeneration;
	private ArrayList<Recipe> thirdGenerartion;

	Sorter(ArrayList<String> request, DataBaseDriver dbDriver){
		this.request = request;
		allFoundRicepe=dbDriver.findByIngredients(request);
		
	}
	
	
	@SuppressWarnings("unchecked")
	private void createGenerations(){
		initialazeGeneration();
		createFirstGeneration();
		createSecondGeration();
		
	}
	
	@SuppressWarnings("unchecked")
	private void createSecondGeration() {
		ArrayList<Recipe> templateRecipe = (ArrayList<Recipe>) thirdGenerartion.clone();
		
		
		for(Recipe recipe: templateRecipe){
			for(String ingredient: request){
				recipe.getIngredients().remove(ingredient);
			}
		}
		
		for(int i=0; i<templateRecipe.size(); i++){
			if(templateRecipe.get(i).getIngredients().size()==1){
				secondGeneration.add(thirdGenerartion.get(i));
				thirdGenerartion.remove(i);
			}
		}
		
	}


	@SuppressWarnings("unchecked")
	private void initialazeGeneration() {
		firstGeneration = new ArrayList<>();
		secondGeneration = new ArrayList<>();
		thirdGenerartion=(ArrayList<Recipe>) allFoundRicepe.clone();
	}


	private void createFirstGeneration() {
		for(Recipe recipe: thirdGenerartion){
			if(request.contains(recipe.getIngredients())){
				firstGeneration.add(recipe);
				thirdGenerartion.remove(recipe);
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
