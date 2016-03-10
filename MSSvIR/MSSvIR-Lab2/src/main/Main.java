package main;

import metrics.Halstead;
import metrics.McClure;

public class Main {

	public static void main(String[] args) {
		System.out.println("Halsted:");
		Halstead halstead = new Halstead("src/");
		halstead.analyzeCode();
		
		System.out.println("\nMcClure:");
		McClure mcClure = new McClure("src/");
		mcClure.analyzeCode();
	}
}
