package vigenere;

public class Encoder {
	private final int ALPHABET_LENGTH = 33;
	
	private String text;
	private String key;
	
	private String lineShifts;
	
	public Encoder(String plainText, String key){
		this.text = plainText.toUpperCase();
		this.key = key.toUpperCase();
	}
	
	public String encryptText(){
		makeLineShifts();
		char[] encryptedText = text.toCharArray();
		for (int i=0; i<encryptedText.length; i++){
			encryptedText[i] = getShiftedSymbol(i);
		}
		return String.valueOf(encryptedText);
	}
	
	private void makeLineShifts()
	{
		
	}
	
	private char getShiftedSymbol(int index){
		if (!Character.isAlphabetic(text.charAt(index))){
			return text.charAt(index);
		}
		return (char)(((getCharNumber(text.charAt(index))+getCharNumber(lineShifts.charAt(index))) % ALPHABET_LENGTH)+1039);
	}
	
	private int getCharNumber(char symbol){
		int asciiCode = (int)symbol;
		
		//¨ symbol problem
		if(asciiCode>1045){
			asciiCode++;
		}
		if(asciiCode==1025){
			asciiCode = 1046;
		}
		return asciiCode-1039;
	}
}
