package main;

import java.util.Scanner;

import controller.Controller;

public class Main {

	private static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args){
		System.out.println("Hello!");
		String choose = null;
		Controller controller = new Controller();
		while(!"Exit".equals(choose)){
			System.out.print("Please input command:\t");
			choose = in.nextLine();
			System.out.println(controller.process(choose));
		}	
		
	}
	
}
