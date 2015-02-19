package com.bsuir.wtlab2.source.factory;

import com.bsuir.wtlab2.source.entity.GiftElement;
import com.bsuir.wtlab2.source.entity.Sweet;
import com.bsuir.wtlab2.source.entity.Wrapping;

public class GiftFactory {
	private static final String SPACE_REGEXP = "[\\t ]+";


	public GiftElement getGiftElement(final String objectType,final String params) {
		//String objectType = parser.getObjectType(requaredGiftElement);
		//String params = parser.getParams(requaredGiftElement);
		switch (objectType) {
		case "Sweet":
			int sweethess = Integer.valueOf(params);
			return new Sweet(sweethess);
		case "Wrapping":
			String[] patternAndColor = params.split(SPACE_REGEXP);
			String pattern = patternAndColor[0];
			String color = patternAndColor[1];
			return new Wrapping(pattern, color);
		default:
			return null;
		}
	}
}
