package com.bsuir.wtlab2.logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class FactoryParametersParser {
	private static final String SPACE_REGEXP = "[\\t ]+";
	private static final String WRAPPER_PATTERN_REGEXP = "\\w+[ \\t]+patterns";
	private static final String WRAPPER_COLOR_REGEXP = "\\w+ color";
	private static final String NOT_DIGIT_REGEXP = "[^\\d]";

	static String getParams(String request) {
		if(request.contains("sweet")){
			return getSweetParams(request);
		}
		else{
			return getWrappingParams(request);
		}
	}
	
	static String getObjectType(String request){
		if(request.contains("sweet")){
			return "Sweet";
		}
		else{
			return "Wrapping";
		}
	}
	
	static String getWrappingParams(String request){
		String params ="%s %s";
		Pattern functionPattern = Pattern.compile(WRAPPER_PATTERN_REGEXP);
		Matcher matcher = functionPattern.matcher(request);
		matcher.find();
		String pattern = matcher.group().split(SPACE_REGEXP)[0];
		
		functionPattern = Pattern.compile(WRAPPER_COLOR_REGEXP);
		matcher = functionPattern.matcher(request);
		matcher.find();
		String color = matcher.group().split(SPACE_REGEXP)[0];
		
		return String.format(params, pattern, color);
	}
	
	static String getSweetParams(String request){
		if(request.contains("max")){
			return "100";
		}else{
			return request.replaceAll(NOT_DIGIT_REGEXP, "");
		}
	}

}
