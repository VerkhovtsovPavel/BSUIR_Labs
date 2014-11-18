package algorithms;

import java.util.ArrayList;
import java.util.Collections;

import recipes.Recipe;
import utils.SizeComparator;

public class Plotter {
	private ArrayList<PlotterSupporter> plotterSupporters;
	private PlotterSupporter graphCenter;
	
	
	
	public Plotter(ArrayList<Recipe> recipes, Recipe centerRecipe) {
		this.graphCenter = new PlotterSupporter(centerRecipe.getName(), centerRecipe.getIngredients(), recipes.size());
		recipes.remove(centerRecipe);
		plotterSupporters = new ArrayList<PlotterSupporter>();
		for(int i = 0; i< recipes.size(); i++){
			plotterSupporters.add(new PlotterSupporter(recipes.get(i).getName(), recipes.get(i).getIngredients(),i+1));
		}
	}
	
	public void buildGraph(){
		removeCenterRecipeIngredients();
		sortPlotterSupporters();
		paveWays();
		
	}
	
	private void paveWays() {
		for(PlotterSupporter recipe: plotterSupporters){
			paveWay(graphCenter, recipe);
		}
		
	}

	private void paveWay(PlotterSupporter currentPlase, PlotterSupporter recipeForPlacement){
		for (ArrayList<String> key: currentPlase.getWayMap().keySet()){
			if(recipeForPlacement.getIngredients().containsAll(key)){
				recipeForPlacement.getIngredients().removeAll((key));
				int nextPlase = currentPlase.getWayMap().get(key);
				paveWay(this.plotterSupporters.get(nextPlase), recipeForPlacement);
			}
		}
		currentPlase.getWayMap().put(recipeForPlacement.getIngredients(), recipeForPlacement.getRecipeNumber());
		//recipeForPlacement.getIngredients().clear();/*Maybe not need*/
	}

	private void sortPlotterSupporters() {
		Collections.sort(this.plotterSupporters, new SizeComparator());
	}

	private void removeCenterRecipeIngredients() {
		for(PlotterSupporter plotterSupporter:this.plotterSupporters){
			plotterSupporter.getIngredients().removeAll(graphCenter.getIngredients());
			//TODO Change to subconta list
			if(plotterSupporter.getIngredients().isEmpty()){
				this.plotterSupporters.remove(plotterSupporter);
			}
		}
	}
}
