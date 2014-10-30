package main;

import java.io.IOException;

import driver.DataBaseDriver;

import ui.MainForm;

public class Main {

	public static void main(String[] args) throws IOException {
		DataBaseDriver dataBaseUtils = new DataBaseDriver("CourseProject", "recipes");
		MainForm.create(dataBaseUtils);

		/*
		 * dataBaseUtils.cleanDB(); ArrayList<String> ref = new
		 * ArrayList<String>(); ref.add("Огурец"); Recipe recipe = new
		 * Recipe("Рецепт #1", 25, ref, "Резать, варить");
		 * dataBaseUtils.insert(recipe); ref.clear(); ref.add("Морковь"); recipe
		 * = new Recipe("Рецепт #2", 50, ref, "Резать, жарить");
		 * dataBaseUtils.insert(recipe); dataBaseUtils.getAll();
		 * 
		 * System.out.println(); dataBaseUtils.findByTimeRequired(30);
		 * 
		 * System.out.println(); dataBaseUtils.findByRecipeName("Рецепт #1");
		 * 
		 * System.out.println(); dataBaseUtils.findByIngredients(ref);
		 */
	}
}
