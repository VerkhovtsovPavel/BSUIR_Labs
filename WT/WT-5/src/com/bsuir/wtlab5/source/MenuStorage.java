package com.bsuir.wtlab5.source;

import java.util.ArrayList;

import com.bsuir.wtlab5.entity.Dish;


public class MenuStorage {

	private static MenuStorage instance;
	private ArrayList<Dish> menu = new ArrayList<Dish>();
	
	public static MenuStorage getInstanse() {
		if(instance == null){
			instance = new MenuStorage();
		}
		return instance;
	}

	public ArrayList<Dish> getMenu() {
		return menu;
	}

	public void setMenu(ArrayList<Dish> menu) {
		this.menu = menu;
	}
	
	private MenuStorage(){};
}
