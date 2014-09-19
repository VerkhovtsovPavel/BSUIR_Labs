package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
			String action = takeWord();
			if (action.equals("Draw")){
				Painter.drawObjects();
			}
			else if (action.equals("Add")){
				String shapeName = takeWord();
				ArrayList<Integer> paramList = parseParam();
				
				Class<?> shapeClass = Class.forName("shapes."+shapeName);
				Constructor<?> shapeConstructor = shapeClass.getDeclaredConstructors()[0];
				BaseShape shape = (BaseShape) shapeConstructor.newInstance(paramList);
				Painter.addObjectInList(shape);
			}
			
			else if (action.equals("Save")){
				String filePath = takeWord();
				FileOutputStream fileStream = new FileOutputStream(filePath);
				ObjectOutputStream serialazer = new ObjectOutputStream(fileStream);
				serialazer.writeObject(Painter.getObjectList());
				serialazer.flush();
				serialazer.close();
			}
			
			else if (action.equals("Open")){
				String filePath = takeWord();
				FileInputStream fileStream = new FileInputStream(filePath);
				ObjectInputStream deserialazer = new ObjectInputStream(fileStream);
				Painter.raiseList( (ArrayList<BaseShape>) deserialazer.readObject());
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
		String word;
		if (commandLine.indexOf(" ")!=-1){
			word = commandLine.substring(0,commandLine.indexOf(" "));
			commandLine=commandLine.substring(commandLine.indexOf(" ")+1);
		}
		else
		{
			word = commandLine;
			commandLine = "";
		}
		return word;
	}
}
