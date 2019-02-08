package com.bsuir.wtlab2.main;

import java.util.Scanner;

import com.bsuir.wtlab2.controller.Controller;


public class Main {
	
	//TODO Add cost parsing and remove hardcode 

	private static Scanner in = new Scanner(System.in);

	/**
	 * Entry point.
	 * @param args command line arguments
	 */
	public static void main(final String[] args) {
		System.out.println("Hello!");
		String choice = null;
		Controller controller = new Controller();
		while (!"Exit".equals(choice)) {
			System.out.print("Please input command:\t");
			choice = in.nextLine();
			System.out.println(controller.process(choice));
		}
	}
}
