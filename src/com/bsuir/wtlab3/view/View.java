package com.bsuir.wtlab3.view;

import org.apache.log4j.Logger;

import com.bsuir.wtlab3.controller.Controller;
import com.bsuir.wtlab3.exception.ControllerException;

//TODO More logging

public class View {
	/**
	 * Logger.
	 */
	private static Logger log = Logger.getLogger(View.class);
	private static Controller controller = new Controller();

	/**
	 * Entry point.
	 * 
	 * @param args
	 *            command line arguments
	 */
	public static void main(final String[] args) {
		log.fatal("Start program");
		sendRequest("load");
		
		sendRequest("add WebTech:wt@gmail.com:25-03-2015 19-57:Web Technology");
		sendRequest("add OSiSP:os@gmail.com:27-04-2015 07-37:Operation system and system programming");
		
		sendRequest("get -a");
				
		sendRequest("sort -t");
		sendRequest("get -a");
		
		sendRequest("find -t \"OSiSP\"");			

		sendRequest("save");
		log.fatal("Stop program");
	}
	
	private static void sendRequest(String choice){
		try{
			log.debug(String.format("View send request '%s'", choice));
			String answer = controller.process(choice);
			log.debug(String.format("View received response '%s'", answer));
			log.info(answer);
		}catch(ControllerException cex){
			log.error(cex.getMessage());
		}
	}
}
