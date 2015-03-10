package com.bsuir.wtlab2.controller;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bsuir.wtlab2.logic.Logic;

public class Controller {

	private static final String COMMAND_VALIDATE_REGEXP = "(((Add)|(Delete)) +(((wrapper with) +\\w+ +(patterns) +\\w+ +(color))|((chocolate with cocoa \\d+)|(zephyr with fruit \\w+)|(candiedRoastedNuts with nuts \\w+)) sweetness \\d+) cost \\d+)|(((Clear)|(Print)) +(present))";
	private static final String SPACE_REGEXP = "[\\t ]+";

	private Logic logic;

	public Controller() {
		this.logic = new Logic();
	}

	public String process(String request) {
		if (!checkCommand(request)) {
			return "Incorrect command";
		}
		String commantType = request.split(SPACE_REGEXP)[0];
		switch (commantType) {
		case "Add":
			return addCommands(request);
		case "Delete":
			return deleteCommands(request);
		case "Print":
			return buildString(logic.getGift()) + String.format("Total cost = %d", logic.getTotalCost());
		case "Clear":
			logic.clearGift();
			return "All gift elements deleted";
		default:
			return "Incorrect command";
		}
	}

	public String buildString(final ArrayList<?> list) {
		StringBuilder builder = new StringBuilder();
		for (Object object : list) {
			builder.append(object.toString() + "\n");
		}
		return builder.toString();
	}

	private String addCommands(String request) {
		if (logic.addElementOnGift(request)) {
			return "Element add to gift";
		} else {
			return "Element don't added";
		}
	}

	private String deleteCommands(String request) {
		if (logic.removeElementFromGift(request)) {
			return "Element deleted";
		} else {
			return "Element don't find in gift";
		}
	}

	private boolean checkCommand(String request) {
		Pattern functionPattern = Pattern.compile(COMMAND_VALIDATE_REGEXP);
		Matcher matcher = functionPattern.matcher(request);
		return matcher.find();
	}

}
