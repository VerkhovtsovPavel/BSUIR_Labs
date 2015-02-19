package com.bsuir.wtlab1.controller;

import java.util.ArrayList;

import com.bsuir.wtlab1.logic.Logic;
import com.bsuir.wtlab1.source.entity.Treasure;
/**
 * Class routing requests from view layer to proper methods.
 * @author Pavel_Verkhovtsov
 */
public class Controller {
	
	private Logic logic;

	/**
	 * Initialize list of dragon treasures.
	 */
	public Controller() {
		this.logic = new Logic();
	}

	/**
	 * Main method realize routing requests between methods.
	 * @param choice Required action
	 * @return Response
	 */
	public final String process(final String choice) {
		try {
			String[] parseChoice = choice.split(":[\t ]*");
			String request = parseChoice[0].trim();
			switch (request) { 
			case "Get all":
				return buildString(logic.getAllTreasures());
			case "Get dearest treaser":
				return "Dearest treaser: "+buildString(logic.findDearestTreaser());
			case "Get treasers on sum":
				long parameter = Long.valueOf(parseChoice[1].trim());
				return "Treasers on sum "+parameter+" :\n"+buildString(logic.findTreasuresOnSum(parameter));
			case "Exit":
				return "Goodbye";
			default:
				return "Incorrect command";
			}
		} catch (NumberFormatException e) {
			return "Incorrect command";
		}
	}
	
	/**
	 * Convert list to string.
	 * @param list list
	 * @return string
	 */
	private String buildString(final ArrayList<?> list) {
		StringBuilder builder = new StringBuilder();
		for (Object object : list) {
			builder.append(object.toString() + "\n");
		}
		return builder.toString();
	}
	
	/**
	 * Convert treasure to string.
	 * @param treasure treasure to convert
	 * @return string
	 */
	private String buildString(final Treasure treasure){
		return treasure.toString();
	}

}
