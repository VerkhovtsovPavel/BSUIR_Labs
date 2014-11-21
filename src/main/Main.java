package main;

import utils.FileUtils;


public class Main {
	private static String inputFile = "plainText.txt";
	private static String outputFile = "encryptedText.txt";
	
	private static int[] startPositionOfFirstLFSR ={1,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,1};
	private static int[] startPositionOfSecondLFSR ={1,0,0,0,0,1,0,0,0,0,0,1,1,0,1,0,1};
	private static int[] startPositionOfThierdLFSR = {0,0,0,1,0,1,0,0,0,0,0,0,1,1,1,0,0};
	
	private static int[] polinom = {2,7,9};
	
	public static void main(String[] args){
		byte[] file = FileUtils.readFromFileInByteArray("text.txt");
		/*String plainText = FileUtils.readFromFileInString(inputFile);
		for (int i=0; i< plainText.length(); i++)
			System.out.println((int) plainText.charAt(i));
		//System.out.println(plainText);
		FileUtils.writeInFile(plainText, outputFile);*/
	}
}
