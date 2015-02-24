package com.bsuir.wtlab3.model;

import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.management.RuntimeErrorException;

import org.apache.log4j.Logger;

import com.bsuir.wtlab3.source.entity.Note;

public class CommandLineParametersParser {

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

	@SuppressWarnings("deprecation")
	public Note convertStringToNote(String unparseNote) {
		String[] currentParam = unparseNote.split(":[\t ]*");
		if (currentParam.length < 4) {
			log.error("Error while parse string: " + unparseNote);
			return null;
		}
		
		return new Note(currentParam[0],currentParam[1],new Date(currentParam[2]), currentParam[3]);
		
		
	}
}
