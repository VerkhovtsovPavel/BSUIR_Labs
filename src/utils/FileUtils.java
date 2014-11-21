package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class FileUtils {

	public static byte[] readFileInByteArray(String fileName){
		byte[] data = null;
		Path path = Paths.get(fileName);
		try {
			data = Files.readAllBytes(path);
		} catch (IOException e) {
			System.out.println("Error while read file " + path.toAbsolutePath().toString());
		}
		
		return data;
	}
}
