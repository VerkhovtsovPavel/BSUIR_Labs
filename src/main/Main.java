package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

		while(true){
			commandLine = new Scanner(System.in).nextLine();
			String action = takeWord();
			//TODO Install jre1.7 and use switch 
			if (action.equals("Exit")){
				System.exit(0);
			}
			else if (action.equals("Draw")){
				Painter.drawObjects();
			}
			else if (action.equals("Add")){
				addShape();
			}
			else if (action.equals("Save")){
				saveObjectListInFile();
			}
			else if (action.equals("Open")){
				readObjectListFromFile();
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
	
	private static void addShape() throws Exception{
		String shapeName = takeWord();
		ArrayList<Integer> paramList = parseParam();
		Class<?> shapeClass = Class.forName("shapes."+shapeName);
		Constructor<?> shapeConstructor = shapeClass.getDeclaredConstructors()[0];
		BaseShape shape = (BaseShape) shapeConstructor.newInstance(paramList);
		Painter.addObjectInList(shape);
	}
	
	private static void saveObjectListInFile(){
		String filePath = takeWord();
		FileOutputStream fileStream;
		try {
			fileStream = new FileOutputStream(filePath);
			ObjectOutputStream serialazer = new ObjectOutputStream(fileStream);
			serialazer.writeObject(Painter.getObjectList());
			serialazer.flush();
			serialazer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void readObjectListFromFile(){
		String filePath = takeWord();
		FileInputStream fileStream;
		try {
			fileStream = new FileInputStream(filePath);
			ObjectInputStream deserialazer;
			deserialazer = new ObjectInputStream(fileStream);
			Painter.raiseList( (ArrayList<BaseShape>) deserialazer.readObject());
		} catch (ClassNotFoundException e) {
				e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
