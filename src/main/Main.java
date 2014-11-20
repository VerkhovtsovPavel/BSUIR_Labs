package main;

import graphics.GraphicsClass;

import java.awt.Color;
import java.util.Scanner;

public class Main {
	double[] ErmithQuot = { 1, 4, 4, 16 };
	static double[] WeightFactors = new double[4];
	static double[] CurW = new double[4];
	static double[] tmp = new double[4];
	static double[][] StudySample = new double[4][2];
	static double[] TestSample = new double[2];
	static int IterCount;
	static double minX;
	static double maxX;
	static double minY;
	static double maxY;
	static double ComX;
	static double ComY;
	double shx;
	double shy;
	static double it;
	static double jt;
	double previt;
	double prevjt;

	private static Scanner in = new Scanner(System.in);
	
	
	public static void main(String[] args){
		calculateFunction();
	}

	private static void calculateFunction() {
		int i;
		boolean fnd;
		double cks;
		String func;

		for (i = 0; i < 4; i++) {
			WeightFactors[i] = 0;
		}

		System.out.println("Enter study points of first class");
		StudySample[0][0] = in.nextInt();
		StudySample[0][1] = in.nextInt();
		StudySample[1][0] = in.nextInt();
		StudySample[1][1] = in.nextInt();
		System.out.println("Enter study points of second class");
		StudySample[2][0] = in.nextInt();
		StudySample[2][1] = in.nextInt();
		StudySample[3][0] = in.nextInt();
		StudySample[3][1] = in.nextInt();

		i = 0;
		IterCount = 0;
		WeightFactors[0] = 1;
		WeightFactors[1] = 4 * StudySample[i][0];
		WeightFactors[2] = 4 * StudySample[i][1];
		WeightFactors[3] = 16 * StudySample[i][0] * StudySample[i][1];
		do {
			i = 0;
			// if IterCount=0 i=0 else i=1;
			fnd = false;
			do {
				CurW[0] = WeightFactors[0];
				CurW[1] = WeightFactors[1] * StudySample[i + 1][0];
				CurW[2] = WeightFactors[2] * StudySample[i + 1][1];
				CurW[3] = WeightFactors[3] * StudySample[i + 1][0] * StudySample[i + 1][1];
				cks = 0;
				for (int k = 0; k < 4; k++)
					cks = cks + CurW[k];
				tmp[0] = 1;
				tmp[0] = 4 * StudySample[i + 1][0];
				tmp[0] = 4 * StudySample[i + 1][1];
				tmp[0] = 16 * StudySample[i + 1][0] * StudySample[i + 1][1];
				if ((i + 1 <= 1/* ??? */) && (cks < 0/* ??? */)) {
					fnd = true;
					for (int j = 0; j < 4; j++)
						WeightFactors[j] = WeightFactors[j] + tmp[j];
				} else if ((i + 1 > 1/* ??? */) && (cks > 0/* ??? */)) {
					fnd = true;
					for (int j = 0; j < 4; j++)
						WeightFactors[j] = WeightFactors[j] - tmp[j];
				}
				;
				if (i < 2/* ??? */)
					i++;
				else
					i = 0;
			} while (i == 1);/* ??? */
			IterCount++;
		} while ((!fnd) || (IterCount == 50));
		if ((cks <= 0) || (IterCount > 49)) {/* ??? */
			System.out.println("With this training set build a separating function impossible");
		}
		// else
		{
			// for i=1 to 4 ShowMessage(FloatToStr(WeightFactors[i]));
			minX = StudySample[0][0];
			maxX = minX;
			minY = StudySample[0][1];
			maxY = minY;
			for (i = 1; i < 4; i++) {
				if (minX > StudySample[i][0])
					minX = StudySample[i][0];
				if (minY > StudySample[i][1])
					minY = StudySample[i][1];
				if (maxX < StudySample[i][0])
					maxX = StudySample[i][0];
				if (maxY < StudySample[i][1])
					maxY = StudySample[i][1];
			}
			maxX = maxX + 3;
			minX = minX - 3;
			maxY = maxY + 3;
			minY = minY - 3;
			ComX = 545 / (maxX - minX);
			ComY = 369 / (maxY - minY);

			it = minX;
			if ((WeightFactors[2] != 0) || (WeightFactors[3] != 0))
				jt = -(WeightFactors[0] + WeightFactors[1] * it) / (WeightFactors[2] + WeightFactors[3] * it);
			else
				jt = -(WeightFactors[0] + WeightFactors[1] * it);

			GraphicsClass.graphicsInitialaze(WeightFactors, StudySample, minX, maxX, minY, maxY, ComX, ComY, it, jt);
			GraphicsClass.buildGraph();
			
			func = "y=-(" + String.valueOf(WeightFactors[0]);
			if (WeightFactors[1] >= 0)
				func = func + '+' + String.valueOf(WeightFactors[1]);
			else
				func = func + String.valueOf(WeightFactors[1]);
			func = func + "x)";
			if (WeightFactors[2] != 0) {
				func = func + "/(" + String.valueOf(WeightFactors[2]);
				if (WeightFactors[3] < 0)
					func = func + String.valueOf(WeightFactors[3]) + "x";
				else if (WeightFactors[3] > 0)
					func = func + '+' + String.valueOf(WeightFactors[3]) + "x";
			} else if (WeightFactors[3] != 0)
				func = func + "/(" + String.valueOf(WeightFactors[3]) + "x";
			func = func + ')';
		}
		System.out.println(func);
		
		checkPoint();
	}

	private static void checkPoint() {
		double cks;
		int i;

		TestSample[0] = in.nextInt();
		TestSample[1] = in.nextInt();

		CurW[0] = WeightFactors[0];
		CurW[0] = WeightFactors[1] * TestSample[0];
		CurW[0] = WeightFactors[2] * TestSample[1];
		CurW[0] = WeightFactors[3] * TestSample[0] * TestSample[1];
		cks = 0;

		for (i = 0; i < 4; i++)
			cks = cks + CurW[i];
		if (cks <= 0) {
			GraphicsClass.addPoint(Color.RED, (int)((TestSample[0]-minX)*ComX) , (int)((TestSample[1]-minY)*ComY));
			System.out.println("Second class");
		} else {
				GraphicsClass.addPoint(Color.GREEN, (int)((TestSample[0]-minX)*ComX) , (int)((TestSample[1]-minY)*ComY));
			System.out.println("First class");
		}
	}
}
