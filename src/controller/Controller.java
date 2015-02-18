package controller;

import org.apache.log4j.Logger;

import utils.FileUtils;

public class Controller {
	private static Logger logger = Logger.getLogger(Controller.class);
	
	private static final String addCom = "[Aa](dd note)[ \\t]+\\w+\\:\\w+\\:\\w+$";
	private static final String findCom = "[Ff](ind)[ \\t]+(by)[ \\t]+(((email)|(text)|(date)|(topic))[ \\t]+[\\w\\@\\.\\-\\d]+[ \\t]*)+$";
	private static final String sortCom = "[sS](ort)[ \\t]+(by)[ \\t]+((email)|(text)|(date)|(topic))[ \\t]+((up)|(down))$";
	
	public String process(String request){
		logger.debug(String.format("Controller received request '%s'", request));
		String commantType = request.split("[\t ]+")[0];
		String response = null;
		
		switch (commantType) {
		case "Add":
			response = addCommands(request);
			break;
		case "Find":
			response = findCommands(request);
			break;
		case "Save":
			FileUtils.saveNotesToFile("");
			response = "Notes successfully saved to file";
			break;
		case "Open":
			FileUtils.getNotesFromFile("");
			response = "Notes successfully uploded to notepad";
			break;
		case "Exit":
			response = "Goodbye";
			break;
		default:
			response = "Incorrect command";
			logger.warn(response.toUpperCase());
		}
		logger.debug(String.format("Controller send response '%s'", response));
		return response;
		
	}

	private String addCommands(String request) {
		// TODO Auto-generated method stub
		return request;
	}

	private String findCommands(String request) {
		// TODO Auto-generated method stub
		return request;
	}
}
