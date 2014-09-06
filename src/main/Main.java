package main;

import vigenere.Decoder;
import vigenere.Encoder;

public class Main {

	
	public static void main(String[] args){
		String plainText = "АБВГДЕЁЖЭИЙКЛМНОПРСТУФХЦЧШЩЪЭЬЭЮЯ";
		String key = "ааа";

		System.out.println(plainText);
		Encoder encoder = new Encoder(plainText, key);
		System.out.println(encoder.encryptText());
		Decoder decoder = new Decoder(encoder.encryptText(),key);
		System.out.println(decoder.decryptText());
	}
}
// TODO Debug encryption.
// TODO Create parent class for Encoder and Decoder.