package com.bsuir.wtlab2.logic.factory;

import java.util.HashMap;

import com.bsuir.wtlab2.entity.GiftElement;
import com.bsuir.wtlab2.entity.sweet.Chocolate;
import com.bsuir.wtlab2.entity.sweet.Zephyr;
import com.bsuir.wtlab2.entity.wrapping.Wrapping;

public class GiftFactory {

	public GiftElement getGiftElement(final String objectType,final HashMap<String, Object> params) {
		int cost = (int) params.get("Cost");
		int sweetness;
		switch (objectType) {
		case "Chocolate":
			sweetness = (int) params.get("Sweetness");
			int cocoa = (int) params.get("Cocoa");
			return new Chocolate(sweetness, cocoa, cost);
			
		case "Zephyr":
			sweetness = (int) params.get("Sweetness");
			String fruit = (String) params.get("Fruit");
			return new Zephyr(sweetness, fruit, cost);
		
		case "CandiedRoastedNuts":
			sweetness = (int) params.get("Sweetness");
			String nuts = (String) params.get("Nuts");
			return new Zephyr(sweetness, nuts, cost);
			
		case "Wrapping":
			String pattern = (String) params.get("Pattern");
			String color = (String) params.get("Color");
			return new Wrapping(pattern, color, cost);
		default:
			return null;
		}
	}
}
