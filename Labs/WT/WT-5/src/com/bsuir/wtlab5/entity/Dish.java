package com.bsuir.wtlab5.entity;

import java.util.ArrayList;

public class Dish {
	private int id;
	private String name;
	private int cost;
	private ArrayList<String> ingredients;
	private DishClass dishClass;

	public Dish(int id, String name, int cost, ArrayList<String> ingredients,
			String dishClass) {
		this.id = id;
		this.name = name;
		this.cost = cost;
		this.ingredients = ingredients;
		this.setDishClass(dishClass);
	}

	public Dish() {
	}

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getForUserString() {
		StringBuilder dishBuilder = new StringBuilder();
		dishBuilder.append("Dish #");
		dishBuilder.append(this.id);
		dishBuilder.append("\n");

		dishBuilder.append(this.name);
		dishBuilder.append("\n");

		dishBuilder.append("Ingredients:\n");
		for (String ingredient : ingredients) {
			dishBuilder.append(ingredient);
			dishBuilder.append("|");
		}
		dishBuilder.append("\n");

		dishBuilder.append("Class:\t");
		dishBuilder.append(this.dishClass.getUserFormatClass());
		dishBuilder.append("\n");

		dishBuilder.append("Cost:\t");
		dishBuilder.append(this.cost);

		return dishBuilder.toString();
	}

	public String getDishClass() {
		return dishClass.getUserFormatClass();
	}

	public void setDishClass(String dishClass) {
		this.dishClass = DishClass.get(dishClass);
	}

	private enum DishClass {
		FIRST_COURES("First course"), MAIN_COURSE("Main course"), DESSERT(
				"Dessert");

		private final String userFormat;

		DishClass(String locator) {
			userFormat = locator;
		}

		public String getUserFormatClass() {
			return userFormat;
		}

		public static DishClass get(String dishClass) {
			switch (dishClass) {
			case "First course":
				return FIRST_COURES;
			case "Main course":
				return MAIN_COURSE;
			case "Dessert":
				return DESSERT;
			default:
				throw new EnumConstantNotPresentException(DishClass.class,
						dishClass);
			}
		}
	}

}
