package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Class contains helpful methods for files.
 *
 * @author Pavel_Verkhovtsov
 *
 */
public abstract class FileUtils {

	/**
	 * Read treasures from file.
	 *
	 * @return map treasures
	 */
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
		} catch (NumberFormatException e) {
			System.out.println("After ':' not number");
		} catch (IOException e) {
			System.out.println("Error while read file " + file.getAbsolutePath());
		}
		return result;
	}
}
