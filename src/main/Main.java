package main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


public class Main {
	private static String inputFile = "plainText.txt";
	private static String outputFile = "encryptedText.txt";
	private static String key;
	
	public static void main(String[] args){
		String plainText = readFromFile(inputFile).toUpperCase();
		for (int i=0; i< plainText.length(); i++)
			System.out.println((int) plainText.charAt(i));
		//System.out.println(plainText);
		writeInFile(plainText, outputFile);
	}
	
	
	//TODO Maybe move methods for working with files in separate class.
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
