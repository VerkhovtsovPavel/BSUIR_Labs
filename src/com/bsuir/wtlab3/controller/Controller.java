package com.bsuir.wtlab3.controller;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.bsuir.wtlab3.model.Logic;
import com.bsuir.wtlab3.source.entity.Note;

public class Controller {
	private static Logger logger = Logger.getLogger(Controller.class);

	private Logic logic;

	private static final String COMMAND_REGEXP_FORMAT = "(%s)|(%s)|(%s)|(%s)|(upload)|(exit)|\\?";
	private static final String FIND_COMMANDS_VALIDATE_REGEXP = "(find)[ \\t]+(\\-[dmet][\\t ]+\"[\\w\\W]+\"[\\t ]*)+";
	private static final String SORT_COMMANDS_VALIDATE_REGEXP = "(sort)[ \\t]+\\-[tedm]";
	private static final String ADD_COMMANDS_VALIDATE_REGEXP = "(add)[ \\t]+[\\w\\W]+\\:[\\w\\.\\@]+\\:[\\d\\-\\/ \\.]+:[\\w\\W]+";
	private static final String GET_COMMANDS_VALDATE_REGEXP = "(get)[ \\t]+\\-[a]";
	
	private static final String CONTROLLER_RECEIVED_FORMAT = "Controller received request '%s'";
	private static final String CONTROLLER_SEND_FORMAT = "Controller send response '%s'";
	
	private static final String NOTES_NOT_FOUND = "Notes not found";
	private static final String NOTES_SUCCESSFULLY_UPLODED_TO_NOTEPAD = "Notes successfully uploded to notepad";
	private static final String NOTES_SUCCESSFULLY_SAVED_TO_FILE = "Notes successfully saved to file\nGoodbye";
	private static final String INCORRECT_COMMAND = "Incorrect command";

	public Controller() {
		this.logic = new Logic();
	}

	public String process(String request) {
		logger.debug(String.format(CONTROLLER_RECEIVED_FORMAT, request));
		String commantType = request.split("[\t ]+")[0];
		String response = null;
		
		if(!checkCommand(request)){
			commantType = "Error";
		}
		
		request = request.replace(commantType, "").trim();
		switch (commantType) {
		case "add":
			response = logic.addNote(request);
			break;
		case "find":
			ArrayList<Note> foundNotes = logic.findNotes(request);
			if(foundNotes.isEmpty()){
				response = NOTES_NOT_FOUND;
			}
			else{
				response = buildString(foundNotes);
			}
			break;
		case "get":
			response = buildString(logic.getAllNotes());
			break;
		case "upload":
			logic.getNotesFromFile();
			response = NOTES_SUCCESSFULLY_UPLODED_TO_NOTEPAD;
			break;
		case "?":
			response = getHint();
			break;
		case "sort":
			response = logic.sortNotes(request);
			break;
		case "exit":
			logic.saveNotesToFile();
			response = NOTES_SUCCESSFULLY_SAVED_TO_FILE;
			break;
		default:
			response = INCORRECT_COMMAND;
			logger.warn(response.toUpperCase());
		}
		logger.debug(String.format(CONTROLLER_SEND_FORMAT, response));
		return response;

	}

	private String getHint() {
		return "Hint";
	}


	private boolean checkCommand(String request) {
		Pattern pattern = Pattern.compile(String.format(COMMAND_REGEXP_FORMAT, ADD_COMMANDS_VALIDATE_REGEXP, FIND_COMMANDS_VALIDATE_REGEXP, SORT_COMMANDS_VALIDATE_REGEXP, GET_COMMANDS_VALDATE_REGEXP));
		Matcher matcher = pattern.matcher(request);
		return matcher.find();
	}

	private String buildString(final ArrayList<?> list) {
		StringBuilder builder = new StringBuilder();
		for (Object object : list) {
			builder.append(object.toString() + "\n");
		}
		return builder.toString();
	}

}
