package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import vigenere.Decoder;
import vigenere.Encoder;
import kasiski.Kasiski;


public class Main {
	
	private static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args){
		System.out.println("Input file name: ");
		String fileName = in.next();
		String plainText = readFromFile(fileName);
		
		System.out.println("Input key: ");
		String key = in.next();
		
		System.out.println("Output file name: ");
		fileName = in.next();

		//Encoder and Decoder with progressive key
		
		Encoder encoder = new Encoder(plainText, key);
		writeInFile(encoder.encryptText(), fileName);
				
		Decoder decoder = new Decoder(readFromFile(fileName),key);
		System.out.println(decoder.decryptText());
		
		// Test data for Kasiski test.
		
		Kasiski kasiski = new Kasiski(readFromFile(fileName));
		System.out.println("Key length: "+String.valueOf(kasiski.kasiskiAlhoritm()));
	}
	
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
