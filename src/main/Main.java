package main;

import java.util.Scanner;

import org.apache.log4j.Logger;

import controller.Controller;

public class Main {
	/**
	 * Logger.
	 */
	private  static Logger log = Logger.getLogger(Main.class);
	private static Scanner in = new Scanner(System.in);
	
	/**
	 * Entry point.
	 * @param args command line arguments
	 */
	public static void main(final String[] args) {
		log.fatal("Start program");
		String choice = null;
		String answer = null;
		Controller controller = new Controller();
		controller.process("Open notes.txt");
		while (!"Exit".equals(choice)) {
			System.out.print("> ");
			choice = in.nextLine();
			log.debug(String.format("View send request '%s'", choice));
			answer = controller.process(choice);
			log.debug(String.format("View received response '%s'", answer));
			System.out.println(answer);
		}
		log.fatal("Stop program");
	}
}
