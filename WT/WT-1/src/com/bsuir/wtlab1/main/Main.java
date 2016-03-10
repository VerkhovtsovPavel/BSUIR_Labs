package com.bsuir.wtlab1.main;

import com.bsuir.wtlab1.controller.Controller;

/**
 * Class contains entry point.
 *
 * @author Pavel_Verkhovtsov
 *
 */
public abstract class Main {

	/**
	 * Entry point.
	 *
	 * @param args
	 *            command line arguments
	 */
	public static void main(final String[] args) {
		Controller controller = new Controller();

		System.out.println(controller.process("Get all"));
		System.out.println("--------------------------");
		System.out.println(controller.process("Get dearest treaser"));
		System.out.println("--------------------------");
		System.out.println(controller.process("Get treasers on sum:1000"));
	}
}
