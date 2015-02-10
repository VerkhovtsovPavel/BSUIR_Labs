package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class FileUtils {

	public static HashMap<String, Long> getTreasureList() {
		HashMap<String, Long> result = new HashMap<>();
		File file = new File("treasure.txt");
		try {
			BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
			try {
				String lineFromFile;
				while ((lineFromFile = in.readLine()) != null) {
					String[] currentParam = lineFromFile.split(":[\t ]*");
					if (currentParam.length < 2) {
						System.out.println("Error while parse string: " + lineFromFile);
						throw new IOException();
					} else {
						result.put(currentParam[0].trim(), Long.valueOf(currentParam[1].trim()));
					}
				}
			} finally {
				in.close();
			}
		} catch (IOException e) {
			System.out.println("Error while read file " + file.getAbsolutePath());
		}
		return result;
	}
}
