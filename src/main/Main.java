package main;

import vigenere.Decoder;
import vigenere.Encoder;

public class Main {

	
	public static void main(String[] args){
		String plainText = "оюбек опхкефмши ярсдемр";
		String key = "йюью";
		/*for (int i=0; i< temp.length(); i++){
			System.out.println((int)temp.charAt(i));
		}*/
		
		Encoder encoder = new Encoder(plainText, key);
		System.out.println(encoder.encryptText());
		Decoder decoder = new Decoder(encoder.encryptText(),key);
		System.out.println(decoder.decryptText());
	}
}
// TODO Debug encryption.
// TODO Create parent class for Encoder and Decoder.