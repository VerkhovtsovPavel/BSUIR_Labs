package main;

import java.util.Scanner;

import subjects.SampleSet;
import subjects.Subject;

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

		double[][] answer = perceptron(sampleSet, classCount, studySampleSize, featuresCount);
		
		printAnswer(answer);

		while (true) {
			System.out.print("Enter features:\t");
			int[] subj = new int[featuresCount];
			for (int i = 0; i < featuresCount; i++) {
				subj[i] = in.nextInt();
			}

			Subject subject = new Subject(subj);
			System.out.println(classifySubject(answer, subject));
		}
	}

	private static double multiplicationOfVectors(double[] firstVector, Subject secondVector) {
		double tmp;
		tmp = 0;
		for (int i = 0; i < firstVector.length-1; i++) {
			tmp += firstVector[i] * secondVector.getFeature(i);
		}
		return tmp;
	}
	
	private static void printAnswer(double[][] answer){
		for(int i=0; i<answer.length; i++){
			for(int j=0; j< answer[0].length-1; j++){
				System.out.print(answer[i][j]+"*x"+j);
			}
			System.out.println(answer[i][answer.length]);
			
		}
	}

	private static int classifySubject(double[][] weight, Subject subject) {
		double max = -(Double.MAX_VALUE - 1);
		int maxIndex = 0;

		for (int i = 0; i < weight.length; i++) {
			double currentResult = multiplicationOfVectors(weight[i], subject);
			if (currentResult > max) {
				max = currentResult;
				maxIndex = i;
			}
		}
		return maxIndex;
	}

	private static double[][] perceptron(SampleSet sampleSet, int classCount, int studySampleSize, int featuresCount) {
		double[][] weightFactors = new double[classCount][featuresCount+1];
		for (int i = 0; i < weightFactors.length; i++) {
			for (int j = 0; j < weightFactors[0].length; j++) {
				weightFactors[i][j] = 0;
			}
		}

		double[] current = new double[classCount];

		int max;
		boolean eq, WasPun;

		int i = 1;
		int sur = 0;
		WasPun = false;
		do {
			int CurObj = 0;
			do {
				int CurCl = 0;
				do {
					WasPun = false;
					for (int j = 0; j < current.length/* ????? */; j++) {
						current[j] = multiplicationOfVectors(weightFactors[j], sampleSet.getSample(CurCl).getSubject(CurObj));
						current[j] = current[j] + weightFactors[j][featuresCount - 1];
					}
					max = 1;
					for (int j = 0; j < current.length; j++) {
						if (current[j] > current[max])
							max = j;
					}
					eq = false;
					for (int j = 0; j < current.length; j++) {
						if ((current[j] == current[max]) && (j != max))
							eq = true;
					}
					if ((max != CurCl) || (eq)) // punishment
					{
						for (int j = 0; j < current.length; j++) {
							if (j == CurCl)
								for (int k = 0; i < featuresCount+1; i++)
									weightFactors[j][k] = weightFactors[j][k] + sampleSet.getFeature(CurCl, CurObj, k);
							else
								for (int k = 0; k < featuresCount+1; k++)
									weightFactors[j][k] = weightFactors[j][k] - sampleSet.getFeature(CurCl, CurObj, k);
						}
						WasPun = true;
					}
					CurCl++;
				} while (CurCl != classCount);
				CurObj++;
			} while (CurObj != studySampleSize);
			sur++;
		} while (WasPun && (sur < 100));
		if (sur == 100) {
			System.out.println("Error");
		}
		return weightFactors;
	}
}
