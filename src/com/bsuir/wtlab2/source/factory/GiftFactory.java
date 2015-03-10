package com.bsuir.wtlab2.source.factory;

import java.util.HashMap;

import com.bsuir.wtlab2.source.entity.GiftElement;
import com.bsuir.wtlab2.source.entity.sweet.CandiedRoastedNuts;
import com.bsuir.wtlab2.source.entity.sweet.Chocolate;
import com.bsuir.wtlab2.source.entity.sweet.Zephyr;
import com.bsuir.wtlab2.source.entity.wrapping.Wrapping;

public class GiftFactory {

	public GiftElement getGiftElement(final String objectType, final HashMap<String, Object> params) {
		int cost = (int) params.get("Cost");
		int sweetness;
		switch (objectType.toLowerCase()) {
		case "chocolate":
			sweetness = (int) params.get("Sweetness");
			int cocoa = (int) params.get("Cocoa");
			return new Chocolate(sweetness, cocoa, cost);

		case "zephyr":
			sweetness = (int) params.get("Sweetness");
			String fruit = (String) params.get("Fruit");
			return new Zephyr(sweetness, fruit, cost);

		case "candiedroastednuts":
			sweetness = (int) params.get("Sweetness");
			String nuts = (String) params.get("Nuts");
			return new CandiedRoastedNuts(sweetness, nuts, cost);

		case "wrapper":
			String pattern = (String) params.get("Pattern");
			String color = (String) params.get("Color");
			return new Wrapping(pattern, color, cost);
		default:
			return null;
		}
	}
}
