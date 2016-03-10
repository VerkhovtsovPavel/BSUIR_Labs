package com.bsuir.wtlab5.controller;

import java.util.HashMap;

import com.bsuir.wtlab5.exception.LogicException;
import com.bsuir.wtlab5.model.MenuLogic;

public class MenuController {

	private MenuLogic logic;

	public MenuController() {
		logic = new MenuLogic();
	}

	public String process(HashMap<String, String> request)
			throws ControllerException {
		// checkCommand(request);
		try {
			switch (request.get("command")) {
			case "get":
				logic.getMenu(request.get("source"));
				return "Menu get from " + request.get("source");
			case "save":
				logic.saveMenu(request.get("source"));
				return "Menu save to " + request.get("source");
			case "show":
				return logic.showMenu();
			default:
				throw new ControllerException("Incorrect command");
			}
		} catch (LogicException e) {
			throw new ControllerException("Error while process command "
					+ request.get("command") + " " + request.get("source"), e);
		}
	}

}
