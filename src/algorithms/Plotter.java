package algorithms;

import java.util.ArrayList;
import java.util.Collections;

import recipes.Recipe;
import utils.SizeComparator;

public class Plotter {
	private ArrayList<PlotterSupporter> plotterSupporters;
	private Recipe graphCenter;
	
	
	
	public Plotter(ArrayList<Recipe> recipes, Recipe centerRecipe) {
		this.graphCenter = centerRecipe;
		plotterSupporters = new ArrayList<PlotterSupporter>();
		for(int i = 0; i< recipes.size(); i++){
			plotterSupporters.add(new PlotterSupporter(recipes.get(i).getName(), recipes.get(i).getIngredients(),i+1));
		}
	}
	
	public void buildGraph(){
		removeCenterRecipeIngredients();
		sortPlotterSupporters();
		
	}
	
	private void paveWay(PlotterSupporter currentPlase, PlotterSupporter recipeForPlacement){
		while(!recipeForPlacement.getIngredients().isEmpty()){
			if(recipeForPlacement.getIngredients().containsAll(currentPlase.getIngredients())){
				recipeForPlacement.getIngredients().removeAll((currentPlase.getIngredients()));
				//TODO Iterate all HashMap currentPlase and find possible ways
			}
		}
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
