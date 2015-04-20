package com.bsuir.wtlab5.entity;

import java.util.ArrayList;

public class Dish {
	private ArrayList<String> ingredients;
	private int cost;
	
	
	public ArrayList<String> getIngredients() {
		return ingredients;
	}
	public void setIngredients(ArrayList<String> ingredients) {
		this.ingredients = ingredients;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	
}
