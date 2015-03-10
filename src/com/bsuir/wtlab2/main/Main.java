package com.bsuir.wtlab2.main;

import com.bsuir.wtlab2.controller.Controller;

public class Main {

	/**
	 * Entry point.
	 * 
	 * @param args
	 *            command line arguments
	 */
	public static void main(final String[] args) {
		Controller controller = new Controller();

		System.out.println(controller.process("Add chocolate with cocoa 50 sweetness 10 cost 100"));
		System.out.println(controller.process("Add zephyr with fruit apple sweetness 10 cost 100"));
		System.out.println(controller.process("Add candiedRoastedNuts with nuts peanut sweetness 10 cost 100"));
		System.out.println(controller.process("Add wrapper with snowball patterns red color cost 100"));

		System.out.println(controller.process("Print present"));
	}
}
