package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import notepad.Note;

import org.apache.log4j.Logger;

/**
 * Class contains helpful methods for files.
 *
 * @author Pavel_Verkhovtsov
 *
 */
public abstract class FileUtils {
	private  static Logger log = Logger.getLogger(FileUtils.class);

	/**
	 * Read treasures from file.
	 *
	 * @return map treasures
	 */
	public static ArrayList<Note> getTreasureList() {
		ArrayList<Note> result = new ArrayList<Note>();
		File file = new File("treasure.txt");
		try {
			BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
			log.debug("File "+file.getAbsoluteFile()+" opened");
			try {
				String lineFromFile;
				while ((lineFromFile = in.readLine()) != null) {
					String[] currentParam = lineFromFile.split(":[\t ]*");
					if (currentParam.length < 4) {
						log.error("Error while parse string: " + lineFromFile);
						throw new IOException();
					} else {
						result.add(new Note());
					}
				}
			} finally {
				in.close();
				log.debug("File "+file.getAbsoluteFile()+" closed");
			}
		} catch (NumberFormatException e) {
			log.error("After ':' not number", e);
		} catch (IOException e) {
			log.error("Error while read file " + file.getAbsolutePath(), e);
		}
		return result;
	}
}
