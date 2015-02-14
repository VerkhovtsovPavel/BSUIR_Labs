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
		log.info("Hello!");
		String choose = null;
		Controller controller = new Controller();
		while (!"Exit".equals(choose)) {
			log.info("Please input command:\t");
			choose = in.nextLine();
			System.out.println(controller.process(choose));
		}
	}
}
