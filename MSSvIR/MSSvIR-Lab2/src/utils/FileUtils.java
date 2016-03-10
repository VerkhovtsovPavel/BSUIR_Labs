package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileUtils {
	
	private static String allJavaCode="";

	public static String readFromFileToString(String fileName) {
		File file = new File(fileName);
		StringBuilder stringBuilder = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
			try {
				String lineFromFile;
				while ((lineFromFile = in.readLine()) != null) {
					stringBuilder.append(lineFromFile);
					stringBuilder.append("\n");
				}
			} finally {
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBuilder.toString();
	}

	public static ArrayList<String> readFromFileToList(String fileName) {
		File file = new File(fileName);
		ArrayList<String> result = new ArrayList<String>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
			try {
				String lineFromFile;
				while ((lineFromFile = in.readLine()) != null) {
					result.add(lineFromFile);
				}
			} finally {
				in.close();
			}

		} catch (IOException e) {
			System.out.println("Error while reading file " + file.getAbsolutePath());
		}
		return result;
	}

	public static String getSelfCode(String directoryName) {
		selfCodeReader(directoryName);
		return allJavaCode;
	}
	
	private static void selfCodeReader(String directoryName){
		File[] fileList;
		File currentDirectory = new File(directoryName);

		fileList = currentDirectory.listFiles();

		for (File currentFile : fileList) {
			if (currentFile.isFile())
			allJavaCode+=readFromFileToString(currentFile.getAbsolutePath());
			else if (currentFile.isDirectory()) {
				selfCodeReader(currentFile.getAbsolutePath());
			}
		}
	}
}
