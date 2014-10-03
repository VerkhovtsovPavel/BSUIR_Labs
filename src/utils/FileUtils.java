package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import shapes.BaseShape;

import drawing.Painter;

public class FileUtils {

	public static void saveObjectListInObjectiveFile(String filePath) {
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
	public static void readObjectListFromObjectiveFile(String filePath) {
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
	
	public static void saveObjectListInXMLFile(String filePath) {};
	public static void readObjectListInXMLFile(String filePath) {};
}
