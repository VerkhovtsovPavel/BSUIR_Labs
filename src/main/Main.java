package main;

import java.util.Scanner;

import subjects.SampleSet;

public class Main {
	private static final Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Enter count of classes");
		int classCount = in.nextInt();
		
		System.out.println("Enter count of features");
		int featuresCount = in.nextInt();
		
		System.out.println("Enter size of study sample");
		int studySampleSize = in.nextInt();
		
		
		SampleSet sampleSet = new SampleSet(classCount, studySampleSize, featuresCount);
		System.out.println(sampleSet.toString());
		
	}
}
