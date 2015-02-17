package controller;

import utils.FileUtils;

public class Controller {

	
	public String process(String request){
		String commantType = request.split("[\t ]+")[0];
		switch (commantType) {
		case "Add":
			return addCommands(request);
		case "Find":
			return findCommands(request);
		case "Save":
			FileUtils.saveNotesToFile("");
			return "Notes successfully saved to file";
		case "Open":
			FileUtils.getNotesFromFile("");
			return "Notes successfully uploded to notepad";
		case "Exit":
			return "Goodbye";
		default:
			return "Incorrect command";
		}
		
	}

	private String addCommands(String request) {
		// TODO Auto-generated method stub
		return null;
	}

	private String findCommands(String request) {
		// TODO Auto-generated method stub
		return null;
	}
}
