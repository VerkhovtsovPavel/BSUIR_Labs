package main;

import vigenere.Encoder;

public class Main {

	
	public static void main(String[] args){
		String temp = "�����Ũ��������������������������";
		
		/*for (int i=0; i< temp.length(); i++){
			System.out.println((int)temp.charAt(i));
		}*/
		
		Encoder encoder = new Encoder("������", "���");
		System.out.println(encoder.encryptText());
				
	}
}
