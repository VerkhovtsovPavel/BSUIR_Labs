package vigenere;

public class Encoder {
	private final int ALPHABET_LENGTH = 33;
	
	private int[] text;
	private int[] key;
	
	private int[] lineShifts;
	
	public Encoder(String plainText, String key){
		this.text = convertToCharNumber(plainText.toUpperCase());
		this.key = convertToCharNumber(key.toUpperCase());
		this.lineShifts = makeLineShifts();
	}
	
	public String encryptText(){
		char[] encryptedText = new char[text.length];
		for (int i=0; i<encryptedText.length; i++){
			encryptedText[i] = getShiftedSymbol(i);
		}
		return String.valueOf(encryptedText);
	}
	
	private int[] makeLineShifts()
	{
		int [] shiftsLine = new int [text.length];
		int offset = -1;
		int j=0;
		for(int i=0; i<text.length; i++){
			if(text[i]>0){
				if(j%key.length==0){
					offset++;
				}
				shiftsLine[i]=(key[j%key.length]+offset)%ALPHABET_LENGTH;
				j++;
			}
			else{
				shiftsLine[i]=0;
			}
		}
		return shiftsLine;
	}
	
	private int[] convertToCharNumber(String string){
		int[] charNumberArray = new int[string.length()];
		
		for (int i=0; i < charNumberArray.length; i++){
			charNumberArray[i]=getCharNumber(string.charAt(i));
		}
		return charNumberArray;
	}
	
	private char convertToChar(int charNumber){
		charNumber+=1039;
		if(charNumber>1046){
			charNumber--;
		}
		if(charNumber==1046){
			charNumber = 1025;
		}
		
		return (char) charNumber;
	}
	
	private char getShiftedSymbol(int index){
		if (text[index]<0){
			return convertToChar(text[index]);
		}
		return convertToChar((text[index]+lineShifts[index]) % ALPHABET_LENGTH);
	}
	
	private int getCharNumber(char symbol){
		int asciiCode = (int)symbol;
		
		//¨ symbol problem
		if(asciiCode>1046){
			asciiCode++;
		}
		if(asciiCode==1025){
			asciiCode = 1046;
		}
		return asciiCode-1039;
	}
}
