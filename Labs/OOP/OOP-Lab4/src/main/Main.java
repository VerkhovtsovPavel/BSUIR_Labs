package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Scanner;

import shapes.BaseShape;

import drawing.Painter;

public class Main {

	private static String commandLine = "";
	private static Scanner in = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
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
			case "Load":
				loadJar();
				break;
			case "Save":
				saveObjectListInFile();
				break;
			case "Open":
				readObjectListFromFile();
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
		try{
		
		ArrayList<Integer> paramList = parseParam();
		Class<?> shapeClass = Class.forName("shapes." + shapeName);
		Constructor<?> shapeConstructor = shapeClass.getDeclaredConstructors()[0];
		BaseShape shape = (BaseShape) shapeConstructor.newInstance(paramList);
		Painter.addObjectInList(shape);
		}catch(ClassNotFoundException e){
			System.out.println("Class "+shapeName+" not found");
		}
	}

	private static void saveObjectListInFile() {
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

	@SuppressWarnings("unchecked")
	private static void readObjectListFromFile() {
		String filePath = takeWord();
		FileInputStream fileStream;
		ObjectInputStream deserialazer;
		try {
			fileStream = new FileInputStream(filePath);
			deserialazer = new ObjectInputStream(fileStream);
			Painter.raiseList((ArrayList<BaseShape>) deserialazer.readObject());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void loadJar() throws Exception {
		File libPath = new File(takeWord());

		File[] jarFiles = libPath.listFiles();

		if (jarFiles != null) {
			Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
			boolean accessible = method.isAccessible();

			if (accessible == false) {
				method.setAccessible(true);
			}

			URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
			for (File file : jarFiles) {
				URL url = file.toURI().toURL();
				try {
					method.invoke(classLoader, url);

				} finally {
					method.setAccessible(accessible);
				}
			}
		}
	}
}
