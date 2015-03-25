package com.bsuir.wtlab3.model;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.bsuir.wtlab3.entity.Note;

public class CommandLineParametersParser {
	
	private static final String EMAIL_VALIDATE_REGEXP = "^([\\w\\.\\-_]+)?\\w+@[\\w-_]+(\\.\\w+){1,}$";
	private static final String DATE_TIME_VALIDATE_REGEXP = "^(((0[1-9]|[12][0-9]|3[01])[- /.](0[13578]|1[02])|(0[1-9]|[12][0-9]|30)[- /.](0[469]|11)|(0[1-9]|1\\d|2[0-8])[- /.]02)[- /.]\\d{4}|29[- /.]02[- /.](\\d{2}(0[48]|[2468][048]|[13579][26])|([02468][048]|[1359][26])00)) ([01][0-9]|2[0-3])[- /.]([0-5][0-9])$";
	private static final String FIND_TYPE_REGEXP = "\\-%s[\\t ]+[\\w\\W]+";
	private static final String NOTE_FIELD_DELIMETER_REGEXP = ":[\\t ]*"; 
	private static final String SEARCH_PARAMETERS_DELIMETER_REGEXP = "[\\t ]+\""; 
	
	
	private static final Logger log = Logger.getLogger(CommandLineParametersParser.class);

	public String parseSortMethod(String request) {
		request = request.replace("-", "");
		switch (request) {
		case "t":
			return "topic";
		case "e":
			return "e-mail";
		case "m":
			return "text";
		case "d":
			return "date";
		default :
			return request; //TODO Exception
		}
		
	}

	public static HashMap<String, String> parseSearchParameters(String request) {
		HashMap<String, String> result = new HashMap<>();
		result.put("Topic", getSearchParameter("t", request));
		result.put("Text", getSearchParameter("m", request));
		result.put("Date", getSearchParameter("d", request));
		result.put("E-mail", getSearchParameter("e", request));
		
		return result;
	}
	
	private static String getSearchParameter(String searchType, String request){
		Pattern pattern = Pattern.compile(String.format(FIND_TYPE_REGEXP,searchType));
		Matcher matcher = pattern.matcher(request);	
		if(matcher.find()){
			return matcher.group().split(SEARCH_PARAMETERS_DELIMETER_REGEXP)[1].replace("\"", "");
		}
		return null; //TODO Exception
	}


	public static Note convertStringToNote(String unparseNote) {
		String[] currentParam = unparseNote.split(NOTE_FIELD_DELIMETER_REGEXP);
		if (currentParam.length < 4 || !validateDate(currentParam[2]) || !validateEmail(currentParam[1])) {
			log.error("Error while parse string: " + unparseNote);
			return null; //TODO Exception
		}
		return new Note(currentParam[0],currentParam[1],currentParam[2], currentParam[3]);
	}
	
	private static boolean validateEmail(String eMail){
		Pattern pattern = Pattern.compile(EMAIL_VALIDATE_REGEXP);
		Matcher matcher = pattern.matcher(eMail);
		return matcher.matches();
	}
	
	private static boolean validateDate(String date){
		Pattern pattern = Pattern.compile(DATE_TIME_VALIDATE_REGEXP);
		Matcher matcher = pattern.matcher(date);
		return matcher.matches();
	}
}
