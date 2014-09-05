package main;

import vigenere.Encoder;

public class Main {

	
	public static void main(String[] args){
		String temp = "ÀÁÂÃÄÅ¨ÆÇÈÉÊËÌÍÎÏĞÑÒÓÔÕÖ×ØÙÚÛÜİŞß";
		
		/*for (int i=0; i< temp.length(); i++){
			System.out.println((int)temp.charAt(i));
		}*/
		
		Encoder encoder = new Encoder("ÀÀÀÀÀÀ", "ÀÀÀ");
		System.out.println(encoder.encryptText());
				
	}
}
