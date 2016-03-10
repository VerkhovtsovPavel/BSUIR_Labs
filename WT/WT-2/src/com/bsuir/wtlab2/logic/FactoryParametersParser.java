package com.bsuir.wtlab2.logic;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class FactoryParametersParser {
	private static final String SPACE_REGEXP = "[\\t ]+";
	private static final String OBJECT_TYPE_REGEXP = String.format("((Add)|(Delete))%s//w+)",SPACE_REGEXP);
	private static final String WRAPPER_PATTERN_REGEXP = String.format("\\w+%spatterns", SPACE_REGEXP);
	private static final String WRAPPER_COLOR_REGEXP = String.format("\\w+%scolor", SPACE_REGEXP);
	private static final String NOT_DIGIT_REGEXP = "[^\\d]";
	private static final String COST_REGEXP = String.format("cost%s\\d+", SPACE_REGEXP);
	private static final String NUTS_TYPE_REGEXP = String.format("nuts%s//w+", SPACE_REGEXP);
	private static final String COCOA_PERSENT_REGEXP = String.format("cocoa%s//d+", SPACE_REGEXP);
	private static final String FRUIT_REGEXP = String.format("friut%s//w+", SPACE_REGEXP);

	public static HashMap<String, Object> getParams(String request) {
		switch (getObjectType(request)) {
		case "wrapping":
			return getWrappingParams(request);
		case "chocolate":
			return getChocolateParams(request);
		case "zephyr":
			return getZephyrParams(request);
		case "candiedRoastedNuts":
			return getCandiedRoastedNutsParams(request);
		default:
			return null;
		}
	}
	
	private static HashMap<String, Object> getCandiedRoastedNutsParams(
			String request) {
		HashMap<String, Object> candiedRoastedNutsParameters = getSweetParams(request);
		candiedRoastedNutsParameters.put("Nuts", getNutsParams(request));
		return candiedRoastedNutsParameters;
	}

	private static HashMap<String, Object> getZephyrParams(String request) {
		HashMap<String, Object> zephyrParameters = getSweetParams(request);
		zephyrParameters.put("Fruit", getFruitParams(request));
		return zephyrParameters;
	}

	private static HashMap<String, Object> getChocolateParams(String request) {
		HashMap<String, Object> chocolateParameters = getSweetParams(request);
		chocolateParameters.put("Cocoa", getCocoaParams(request));
		return chocolateParameters;
	}
	
	public static String getObjectType(String request){
		Pattern functionPattern = Pattern.compile(OBJECT_TYPE_REGEXP);
		Matcher matcher = functionPattern.matcher(request);
		matcher.find();
		String objectType = matcher.group().split(SPACE_REGEXP)[1];
		return objectType;
	}
	
	private static Integer getCost(String request){
		Pattern functionPattern = Pattern.compile(COST_REGEXP);
		Matcher matcher = functionPattern.matcher(request);
		matcher.find();
		Integer cost = Integer.parseInt(matcher.group().split(SPACE_REGEXP)[1]);
		return cost;
	}
	
	private static HashMap<String, Object> getWrappingParams(String request){
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("Pattern", getWrapperPattern(request));
		parameters.put("Color", getWrapperColor(request));
		parameters.put("Cost", getCost(request));
		
		return parameters;
	}
	
	private static String getWrapperColor(String request){
		Pattern functionPattern = Pattern.compile(WRAPPER_COLOR_REGEXP);
		Matcher matcher = functionPattern.matcher(request);
		matcher.find();
		String color = matcher.group().split(SPACE_REGEXP)[0];
		return color;
	}
	
	private static String getWrapperPattern(String request){
		Pattern functionPattern = Pattern.compile(WRAPPER_PATTERN_REGEXP);
		Matcher matcher = functionPattern.matcher(request);
		matcher.find();
		String pattern = matcher.group().split(SPACE_REGEXP)[0];
		return pattern;
	}
	
	//TODO Refactor use regExp
	private static HashMap<String, Object> getSweetParams(String request){
		HashMap<String, Object> parameters = new HashMap<>();
		parameters.put("Sweetness", request.replaceAll(NOT_DIGIT_REGEXP, ""));
		parameters.put("Cost", getCost(request));
		
		return parameters;
	}
	
	private static String getNutsParams(String request){
		Pattern functionPattern = Pattern.compile(NUTS_TYPE_REGEXP);
		Matcher matcher = functionPattern.matcher(request);
		matcher.find();
		String nutsType = matcher.group().split(SPACE_REGEXP)[1];
		return nutsType;
	}
	
	private static Integer getCocoaParams(String request){
		Pattern functionPattern = Pattern.compile(COCOA_PERSENT_REGEXP);
		Matcher matcher = functionPattern.matcher(request);
		matcher.find();
		Integer cocoaPersent = Integer.parseInt(matcher.group().split(SPACE_REGEXP)[1]);
		return cocoaPersent;
	}
	
	private static String getFruitParams(String request){
		Pattern functionPattern = Pattern.compile(FRUIT_REGEXP);
		Matcher matcher = functionPattern.matcher(request);
		matcher.find();
		String fruit = matcher.group().split(SPACE_REGEXP)[1];
		return fruit;
	}
}