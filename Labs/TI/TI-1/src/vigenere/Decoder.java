package vigenere;

public class Decoder extends Cryptography {

	public Decoder(String plainText, String key) {
		super(plainText, key);
	}

	public String decryptText() {
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
		return convertToChar(((ALPHABET_LENGTH+1) + text[index] - lineShifts[index])
				% (ALPHABET_LENGTH+1));
	}
}
