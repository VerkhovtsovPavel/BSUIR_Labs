package main;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Scanner;

import shapes.BaseShape;
import utils.FileUtils;
import drawing.Painter;

public class Main {

	private static String commandLine = "";
	private static Scanner in = new Scanner(System.in);

	public static void main(String[] args) throws Exception {

		//FileUtils.saveObjectListInXMLFile("proxy.xml",Painter.getObjectList());
		
		System.out.println("Shape drawing shell");

		while (true) {
			commandLine = in.nextLine();
			String action = takeWord();
			switch (action) {
			case "Exit":
				System.exit(0);
				break;
			case "Draw":
				Painter.drawObjects();
				break;
			case "Add":
				addShape();
				break;
			case "Save":
				selectFileTypeAndSave();
				break;
			case "Open":
				FileUtils.readObjectListFromObjectiveFile(takeWord());
				break;
			default:
				System.out.println("Incorrect commant");
			}
		}
	}

	private static ArrayList<Integer> parseParam() {
		ArrayList<Integer> paramArray = new ArrayList<Integer>();
		while (commandLine.lastIndexOf(" ") != -1) {
			paramArray.add(Integer.valueOf(takeWord()));
		}
		paramArray.add(Integer.valueOf(commandLine));
		return paramArray;
	}

	private static void selectFileTypeAndSave() {
		switch (takeWord()) {
		case "Objective":
			FileUtils.saveObjectListInObjectiveFile(takeWord());
			break;
		case "XML":
			FileUtils.saveObjectListInXMLFile(takeWord(),Painter.getObjectList());
			break;
		default:
			System.out.println("Incorrect file type");
		}
	}

	private static String takeWord() {
		String word;
		if (commandLine.indexOf(" ") != -1) {
			word = commandLine.substring(0, commandLine.indexOf(" "));
			commandLine = commandLine.substring(commandLine.indexOf(" ") + 1);
		} else {
			word = commandLine;
			commandLine = "";
		}
		return word;
	}

	private static void addShape() throws Exception {
		String shapeName = takeWord();
		ArrayList<Integer> paramList = parseParam();
		Class<?> shapeClass = Class.forName("shapes." + shapeName);
		Constructor<?> shapeConstructor = shapeClass.getDeclaredConstructors()[0];
		BaseShape shape = (BaseShape) shapeConstructor.newInstance(paramList);
		Painter.addObjectInList(shape);
	}

}
