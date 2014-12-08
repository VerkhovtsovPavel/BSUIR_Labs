package main;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	private static Scanner in = new Scanner(System.in);
	private static ArrayList<Integer> weigths;
	private static PotentialMethod potentialMethod;

	public static void main(String[] args) {
		try {
			System.out.println("Enter study points of first class");
			int x11 = in.nextInt();
			int y11 = in.nextInt();
			int x12 = in.nextInt();
			int y12 = in.nextInt();

			System.out.println("Enter study points of second class");
			int x21 = in.nextInt();
			int y21 = in.nextInt();
			int x22 = in.nextInt();
			int y22 = in.nextInt();

			if ((((x11 == x12 && x21 == x22) && ((y11 < y22 && y12 > y21) || (y12 < y22 && y12 > y21))) || ((y11 == y12 && y21 == y22) && ((x11 < x22 && x12 > x21) || (x12 < x22 && x12 > x21))))) {
				System.out.println("Uncorrect input!");
				System.exit(1);
			} else {
				potentialMethod = new PotentialMethod();
				ArrayList<PotentialClass> classes = new ArrayList<PotentialClass>();
				classes.add(new PotentialClass());
				classes.add(new PotentialClass());
				classes.get(0).vectors.add(new Point(x11, y11));
				classes.get(0).vectors.add(new Point(x12, y21));
				classes.get(1).vectors.add(new Point(x21, y21));
				classes.get(1).vectors.add(new Point(x22, y22));
				boolean correct = true;
				if (correct) {
					potentialMethod.SetObjects(classes);
					weigths = potentialMethod.CalculateFunction();
					potentialMethod.DrawChartGraphics();
					String str;
					str = weigths.get(0).toString();
					str += String.valueOf(weigths.get(1)) + "*x1";
					str += String.valueOf(weigths.get(2)) + "*x2";
					str += String.valueOf(weigths.get(3)) + "*x1*x2";
					System.out.println(str);
				} else {
					System.out.println("Uncorrect input objects features!");
				}
			}
		} catch (Exception ex) {
			System.out.println("Uncorrect input");
			ex.printStackTrace();
		}
		while (true) {
			checkObject();
		}
	}

	private static void checkObject() {
		System.out.println("Enter points");
		int x = in.nextInt();
		int y = in.nextInt();
		if (potentialMethod != null) {
			int res = potentialMethod.DistributeVector(new Point(x, y));
			if (res != 0) {
				System.out.println(String.format("Object in %d class", res));
			} else {
				System.out.println(String.format("Object in zero class!"));
			}
		}
	}
}
