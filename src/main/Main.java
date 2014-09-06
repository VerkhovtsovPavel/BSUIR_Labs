package main;

import vigenere.Decoder;
import vigenere.Encoder;
import кasiski.Kasiski;

public class Main {

	
	public static void main(String[] args){
		String plainText = "Большая птица лететь над рекой, плохие новость нести она, ждать беда белый человек";
		String key = "ААААА";

		System.out.println(plainText);
		Encoder encoder = new Encoder(plainText, key);
		System.out.println(encoder.encryptText());
		Decoder decoder = new Decoder(encoder.encryptText(),key);
		System.out.println(decoder.decryptText());
		System.out.println(Kasiski.kasiskiAlhoritm(encoder.encryptText()));
	}
}
// TODO Kasiski algorithm.