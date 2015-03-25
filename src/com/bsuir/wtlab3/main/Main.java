package com.bsuir.wtlab3.main;

import org.apache.log4j.Logger;

import com.bsuir.wtlab3.controller.Controller;

//TODO Layers exceptions
//TODO More logging

public class Main {
	/**
	 * Logger.
	 */
	private static Logger log = Logger.getLogger(Main.class);
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
		log.debug(String.format("View send request '%s'", choice));
		String answer = controller.process(choice);
		log.debug(String.format("View received response '%s'", answer));
		System.out.println(answer);
	}
}
