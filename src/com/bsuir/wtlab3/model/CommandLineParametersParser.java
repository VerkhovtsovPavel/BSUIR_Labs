package com.bsuir.wtlab3.model;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.management.RuntimeErrorException;

import org.apache.log4j.Logger;

import com.bsuir.wtlab3.source.entity.Note;

public class CommandLineParametersParser {
	
	private static final String EMAIL_VALIDATE_REGEXP = "/^([\\w\\.\\-_]+)?\\w+@[\\w-_]+(\\.\\w+){1,}$/igm";
	private static final String DATE_TIME_VALIDATE_REGEXP = "/^(((0[1-9]|[12][0-9]|3[01])[- /.](0[13578]|1[02])|(0[1-9]|[12][0-9]|30)[- /.](0[469]|11)|(0[1-9]|1\\d|2[0-8])[- /.]02)[- /.]\\d{4}|29[- /.]02[- /.](\\d{2}(0[48]|[2468][048]|[13579][26])|([02468][048]|[1359][26])00)) ([01][0-9]|2[0-3])[- /.]([0-5][0-9])$";

	private static final Logger log = Logger.getLogger(CommandLineParametersParser.class);

	public String parseSortMethod(String request) {
		Pattern pattern = Pattern.compile("(by)[ \\t]+\\w+");
		Matcher matcher = pattern.matcher(request);
		matcher.find();
		return matcher.group().split("[ \\t]+")[1];
	}

	public HashMap<String, String> parseSearchParameters(String request) {
		throw new RuntimeErrorException(null);
	}


	public Note convertStringToNote(String unparseNote) {
		String[] currentParam = unparseNote.split(":[\t ]*");
		if (currentParam.length < 4 || validateDate(currentParam[2]) || validateEmail(currentParam[1])) {
			log.error("Error while parse string: " + unparseNote);
			return null;
		}
		return new Note(currentParam[0],currentParam[1],currentParam[2], currentParam[3]);
	}
	
	private boolean validateEmail(String eMail){
		Pattern pattern = Pattern.compile(EMAIL_VALIDATE_REGEXP);
		Matcher matcher = pattern.matcher(eMail);
		return matcher.matches();
	}
	
	private boolean validateDate(String date){
		Pattern pattern = Pattern.compile(DATE_TIME_VALIDATE_REGEXP);
		Matcher matcher = pattern.matcher(date);
		return matcher.matches();
	}
}
