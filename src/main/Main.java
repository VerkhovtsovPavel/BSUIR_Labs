package main;

import graphics.GraphicsClass;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import objects.Object;

/**
 * Main class.
 * @author Pavel_Verkhovtsov
 */
public class Main {
	private static Object[] objectArray;
	private static ArrayList<Object> objectCenters;
	private static final int DISPERSION = 800;
	private static int classCount;
	private static int objectCount;

	/**
	 * Main method.
	 * @param args console arguments
	 */
	public static void main(final String[] args){
		System.out.print("Input count objects: ");
		objectCount = (new Scanner(System.in)).nextInt();
		
		objectCenters = new ArrayList<Object>();
		writeInArrayRandomObjects();
		selectRandomCenters();
		divideObjectsOnClasses();

		GraphicsClass.setScreenSize(DISPERSION);
		GraphicsClass.setObjectArray(objectArray, objectCenters);
		GraphicsClass.setAreaCount(classCount);
		GraphicsClass.visualizeClasses();

		/*while(checkOptimalityDivision()){
			divideObjectsOnClasses();
		}

		GraphicsClass.visualizeClasses();*/

		System.out.println("Finish");
	}

	private static double getAverageDeviation(int index){
		double sum = 0;
		for (int i=0; i<objectCount; i++){
			if (objectArray[i].getAreaNumber()==objectArray[index].getAreaNumber()){
				sum+=Math.pow(Math.abs(objectArray[i].getX()-objectArray[index].getX()),2)+Math.pow(Math.abs(objectArray[i].getY()-objectArray[index].getY()),2);
			}
		}
		return Math.sqrt(sum/objectCount);
	}

	private static void writeInArrayRandomObjects(){
		objectArray = new Object[objectCount];
		Random rand = new Random();
		for(int i=0; i< objectCount; i++){
			objectArray[i] = new Object(rand.nextInt(DISPERSION), rand.nextInt(DISPERSION), i);
		}
	}

	private static void selectRandomCenters(){
		Random rand = new Random();
		objectCenters.add(objectArray[rand.nextInt(objectCount)]);
		objectCenters.add(objectArray[rand.nextInt(objectCount)]);
	}

	private static void divideObjectsOnClasses(){
		for (int i=0; i< objectCount; i++){
			objectArray[i].choiceArea(objectCenters);
		}
	}

	private static boolean checkOptimalityDivision(){
		boolean redefinitionClasses = false;
		for (int i=0; i<objectCount; i++){
			double kernel =  getAverageDeviation(objectCenters.get(objectArray[i].getAreaNumber()).getIndex());
			double kernelBidder = getAverageDeviation(i);
			if (kernelBidder<kernel){
				objectCenters.set(objectArray[i].getAreaNumber(),objectArray[i]);
				redefinitionClasses = true;
			}
		}
		return redefinitionClasses;
	}

}
