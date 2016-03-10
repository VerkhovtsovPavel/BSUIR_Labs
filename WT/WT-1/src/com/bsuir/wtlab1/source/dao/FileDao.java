package com.bsuir.wtlab1.source.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class FileDao {
	
	private final String dataSource = "treasure.txt";
	
	/**
	 * Read treasures from file.
	 *
	 * @return map treasures
	 */
	public HashMap<String, Long> getDate() {
		HashMap<String, Long> result = new HashMap<>();
		File file = new File(dataSource);
		try {
			BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
			try {
				String lineFromFile;
				while ((lineFromFile = in.readLine()) != null) {
					String[] currentParam = lineFromFile.split(":[\t ]*");
					if (currentParam.length < 2) {
						throw new IOException("Error while parse string: " + lineFromFile);
					} else {
						result.put(currentParam[0].trim(), Long.valueOf(currentParam[1].trim()));
					}
				}
			} finally {
				in.close();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
