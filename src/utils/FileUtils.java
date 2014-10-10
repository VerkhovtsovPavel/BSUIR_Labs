package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class FileUtils {

	private static String readFromFile(String fileName){
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
			}finally{
				in.close();
			}
		
		}catch (IOException e) {
			e.printStackTrace();
		}
		return stringBuilder.toString();
	 }

	
	private static void writeInFile(String text, String fileName){
		File file = new File(fileName);
		try{
			if(!file.exists()){
	            file.createNewFile();
	        }
	        PrintWriter out = new PrintWriter(file.getAbsoluteFile());
	        try {
	            out.print(text);
	        }finally {
	            out.close();
	        }
	    } catch(IOException e) {
	        throw new RuntimeException(e);
	    }
	}
}
