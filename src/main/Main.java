package main;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Scanner;

import shapes.BaseShape;

import drawing.Painter;

public class Main {	
	
	private static String commandLine = "";

	public static void main(String[] args) throws Exception{
		System.out.println("Shape drawing shell");

		while(!commandLine.equals("Exit")){
			commandLine = new Scanner(System.in).nextLine();

			if (commandLine.equals("Draw")){
				Painter.drawObjects();
			}
			else if (takeWord().equals("Add")){
				String shapeName = takeWord();
				ArrayList<Integer> paramList = parseParam();
				
				Class<?> shapeClass = Class.forName("shapes."+shapeName);
				Constructor<?> shapeConstructor = shapeClass.getDeclaredConstructors()[0];
				BaseShape shape = (BaseShape) shapeConstructor.newInstance(paramList);
				Painter.addObjectInList(shape);
			}
			
			else if (takeWord().equals("Save")){
				//TODO Get file path
				//TODO Serialize shape list
			}
			
			else if (takeWord().equals("Open")){
				//TODO Get file path
				//TODO Deserialize shape list
			}
		}
	}

	private static ArrayList<Integer> parseParam(){
		ArrayList<Integer> paramArray = new ArrayList<Integer>();
		while(commandLine.lastIndexOf(" ")!=-1){
			paramArray.add(Integer.valueOf(takeWord()));
		}
		paramArray.add(Integer.valueOf(commandLine));
		return paramArray;
	}
	
	private static String takeWord(){
		String word = commandLine.substring(0,commandLine.indexOf(" "));
		commandLine=commandLine.substring(commandLine.indexOf(" ")+1);
		return word;
	}
}
