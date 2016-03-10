package com.bsuir.wtlab5.view;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.bsuir.wtlab5.controller.ControllerException;
import com.bsuir.wtlab5.controller.MenuController;

public class Main {

	private static MenuController controller;
	private static Logger log = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		controller = new MenuController();
		
		sendRequest("get", "db");
		sendRequest("show", null);
		sendRequest("save", "xml");
	}

	private static void sendRequest(String command, String source) {
		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put("command", command);
		parameters.put("source", source);

		try {
			System.out.println(controller.process(parameters));
		} catch (ControllerException e) {
			log.error("Error", e);
		}
	}
}
