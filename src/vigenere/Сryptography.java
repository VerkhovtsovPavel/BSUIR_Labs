package vigenere;

public abstract class Сryptography {
	protected final int ALPHABET_LENGTH = 33;
	
	protected int[] text;
	protected int[] key;
	
	protected int[] lineShifts;
	
	protected Сryptography(String plainText, String key){
		this.text = convertToCharNumber(plainText.toUpperCase());
		this.key = convertToCharNumber(key.toUpperCase());
		this.lineShifts = makeLineShifts();
	}
	
	protected int[] makeLineShifts()
	{
		int [] shiftsLine = new int [text.length];
		int offset = -1;
		int j=0;
		for(int i=0; i<text.length; i++){
			if(text[i]>0){
				if(j%key.length==0){
					offset++;
				}
				shiftsLine[i]=((key[j%key.length]+offset)%ALPHABET_LENGTH)-1;
				j++;
			}
			else{
				shiftsLine[i]=0;
			}
		}
		return shiftsLine;
	}
	
	protected int[] convertToCharNumber(String string){
		int[] charNumberArray = new int[string.length()];
		
		for (int i=0; i < charNumberArray.length; i++){
			charNumberArray[i]=getCharNumber(string.charAt(i));
		}
		return charNumberArray;
	}
	
	protected char convertToChar(int charNumber){
		charNumber+=1039;
		if(charNumber>1046){
			charNumber--;
		}
		if(charNumber==1046){
			charNumber = 1025;
		}
		
		return (char) charNumber;
	}
	
	protected int getCharNumber(char symbol){
		int asciiCode = (int)symbol;
		
		//� symbol problem
		if(asciiCode>1046){
			asciiCode++;
		}
		if(asciiCode==1025){
			asciiCode = 1046;
		}
		return asciiCode-1039;
	}
}
