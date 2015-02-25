package com.bsuir.wtlab3.controller;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.bsuir.wtlab3.model.Logic;

public class Controller {
	private static Logger logger = Logger.getLogger(Controller.class);

	private Logic logic;

	private static final String COMMAND_REGEXP_FORMAT = "(%s)|(%s)|(%s)|(%s)";
	private static final String FIND_COMMANDS_VALIDATE_REGEXP = "(find)[ \\t]+(-[dmef][\\t ]+\"[\\d \\-\\.\\w\\@]+\"[\\t ]*)+";
	private static final String SORT_COMMANDS_VALIDATE_REGEXP = "(sort)[ \\t]+\\-[tedm]";
	private static final String ADD_COMMANDS_VALIDATE_REGEXP = "(add)[ \\t]+[\\w \\.]+\\:[\\w\\.\\@]+\\:[\\d-\\/ \\.]+:[\\w \\.]+";
	private static final String GET_COMMANDS_VALDATE_REGEXP = "(get)[ \\t]+\\-[a]";

	public Controller() {
		this.logic = new Logic();
	}

	public String process(String request) {
		logger.debug(String.format("Controller received request '%s'", request));
		String commantType = request.split("[\t ]+")[0];
		String response = null;

		if(!checkCommand(request)){
			request = "Incorrect command";
		}
		switch (commantType) {
		case "Add":
		case "add":
			response = logic.addNote(request);
			break;
		case "Find":
		case "find":
			response = buildString(logic.findNotes(request));
			break;
		case "Upload":
			logic.getNotesFromFile();
			response = "Notes successfully uploded to notepad";
			break;
		case "?":
			response = getHint();
			break;
		case "Exit":
		case "exit":
			logic.saveNotesToFile();
			response = "Notes successfully saved to file\nGoodbye";
			break;
		default:
			response = "Incorrect command";
			logger.warn(response.toUpperCase());
		}
		logger.debug(String.format("Controller send response '%s'", response));
		return response;

	}

	private String getHint() {
		return "Hint";
	}


	private boolean checkCommand(String request) {
		Pattern pattern = Pattern.compile(String.format(COMMAND_REGEXP_FORMAT, ADD_COMMANDS_VALIDATE_REGEXP, FIND_COMMANDS_VALIDATE_REGEXP, SORT_COMMANDS_VALIDATE_REGEXP, GET_COMMANDS_VALDATE_REGEXP));
		Matcher matcher = pattern.matcher(request);
		return matcher.matches();
	}

	private String buildString(final ArrayList<?> list) {
		StringBuilder builder = new StringBuilder();
		for (Object object : list) {
			builder.append(object.toString() + "\n");
		}
		return builder.toString();
	}

}
