package vigenere;

import vigenere.Cryptography;

public class Encoder extends Cryptography {

	public Encoder(String plainText, String key) {
		super(plainText, key);
	}

	public String encryptText() {
		char[] encryptedText = new char[text.length];
		for (int i = 0; i < encryptedText.length; i++) {
			encryptedText[i] = getShiftedSymbol(i);
		}
		return String.valueOf(encryptedText);
	}

	private char getShiftedSymbol(int index) {
		if (text[index] < 0) {
			return convertToChar(text[index]);
		}
		return convertToChar((text[index] + lineShifts[index])
				% (ALPHABET_LENGTH+1));
	}
}
