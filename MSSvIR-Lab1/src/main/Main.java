package main;

import metrics.Halstead;

public class Main {

	public static void main(String[] args){
		Halstead halstead = new Halstead("phpSourceCode.txt");
		halstead.analyzeCode();
	}
}
