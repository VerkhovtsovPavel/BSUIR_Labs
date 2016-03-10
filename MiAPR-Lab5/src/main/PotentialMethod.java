package main;

import graphics.GraphicsClass;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class PotentialMethod {

	private static int FUNCTION_POINT_SIZE = 3;
	private static int OBJECT_POINT_SIZE = 5;

	private ArrayList<Point> vectors = new ArrayList<Point>();
	private ArrayList<Integer> weigths = new ArrayList<Integer>();
	private ArrayList<PotentialClass> classes = new ArrayList<PotentialClass>();

	public void SetObjects(ArrayList<PotentialClass> classList) {
		classes.clear();
		weigths.clear();
		classes = classList;
		for (int i = 0; i < classes.size(); i++) {
			for (int j = 0; j < classes.get(i).vectors.size(); j++) {
				weigths.add(0);
			}
		}
		for (PotentialClass potentialClass : classes) {
			for (Point vector : potentialClass.vectors) {
				vectors.add(vector);
			}
		}
	}

	private void CorrectPotential(ArrayList<Integer> term, int sign) {
		for (int i = 0; i < weigths.size(); i++) {
			int newValue = weigths.get(i) + sign * term.get(i);
			weigths.set(i, newValue);
		}
	}

	private int FindCorrectionFactor(Point vector) {
		int sum = 0;
		sum = weigths.get(0) + weigths.get(1) * vector.x + weigths.get(2) * vector.y + weigths.get(3) * vector.x * vector.y;
		if ((classes.get(0).vectors.contains(vector)) && (sum <= 0)) {
			return 1;
		}
		if ((classes.get(1).vectors.contains(vector)) && (sum > 0)) {
			return -1;
		}
		return 0;
	}

	private ArrayList<Integer> FindCurrPotential(Point vector) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		result.add(1);
		result.add(4 * vector.x);
		result.add(4 * vector.y);
		result.add(16 * vector.x * vector.y);
		return result;
	}

	public ArrayList<Integer> CalculateFunction() {
		boolean FunctionIsIncorrect = true;
		int sign = 1;
		int tryCount = 0;
		while (FunctionIsIncorrect) {
			FunctionIsIncorrect = false;
			for (int i = 0; i < vectors.size(); i++) {
				Point vector = vectors.get(i);
				Point nextVector;
				if (i == vectors.size() - 1) {
					nextVector = vectors.get(0);
				} else {
					nextVector = vectors.get(i + 1);
				}
				CorrectPotential(FindCurrPotential(vector), sign);
				sign = FindCorrectionFactor(nextVector);
				if (sign != 0) {
					FunctionIsIncorrect = true;
				}
				
			}
			tryCount++;
			if (tryCount==10000){
				System.out.println("Error study can posible");
				SetUpChart();
				GraphicsClass.buildGraph();
				//System.exit(1);
			}
		}
		return weigths;
	}

	private double FindY(double x) {
		if ((weigths.get(2) + weigths.get(3) * x) != 0) {
			return (-weigths.get(1) * x - weigths.get(0)) / (weigths.get(2) + weigths.get(3) * x);
		}
		return 100;
	}

	private void SetUpChart() {
		GraphicsClass.buildGraph();
		GraphicsClass.addPoint(Color.blue, vectors.get(0).x, vectors.get(0).y, OBJECT_POINT_SIZE);
		GraphicsClass.addPoint(Color.blue, vectors.get(1).x, vectors.get(1).y, OBJECT_POINT_SIZE);
		GraphicsClass.addPoint(Color.red, vectors.get(2).x, vectors.get(2).y, OBJECT_POINT_SIZE);
		GraphicsClass.addPoint(Color.red, vectors.get(3).x, vectors.get(3).y, OBJECT_POINT_SIZE);
	}

	public void DrawChartGraphics() {
		SetUpChart();
		double x = -10;
		while (x < 10) {

			if (FindY(x) < 10 && FindY(x) > -10) {
				double y = FindY(x);
				GraphicsClass.addPoint(Color.black, x, y, FUNCTION_POINT_SIZE);
			}
			x += 0.01;
		}
	}

	public int DistributeVector(Point vector) {
		int sum = 0;
		sum = weigths.get(0) + weigths.get(1) * vector.x + weigths.get(2) * vector.y + weigths.get(3) * vector.x * vector.y;
		if (sum > 0) {
			GraphicsClass.addPoint(Color.blue, vector.x, vector.y, 5);
			return 1;
		}
		if (sum < 0) {
			GraphicsClass.addPoint(Color.red, vector.x, vector.y, 5);
			return 2;
		}
		GraphicsClass.addPoint(Color.black, vector.x, vector.y, 5);
		return 0;
	}

}
