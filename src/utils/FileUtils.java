package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileUtils {

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
		ArrayList<String> result = new ArrayList<>();
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
}
