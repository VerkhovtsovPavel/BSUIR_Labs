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
	private static final int DISPERSION = 600;
	private static int objectCount;
	private static int indexOfObjectWithMaxDistanse;

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

		while(findMaximumDistanseFromClassKernel()>calculateNormDistance()){
			objectCenters.add(objectArray[indexOfObjectWithMaxDistanse]);
			divideObjectsOnClasses();
		}

		GraphicsClass.setScreenSize(DISPERSION+100);
		GraphicsClass.setObjectArray(objectArray, objectCenters);
		GraphicsClass.setAreaCount(objectCenters.size());
		GraphicsClass.visualizeClasses();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		while(checkOptimalityDivision()){
			divideObjectsOnClasses();
		}

		GraphicsClass.visualizeClasses();

		System.out.println("Finish");
	}

	private static double getAverageDeviation(int index){
		double sum = 0;
		for (int i = 0; i<objectCount; i++){
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
		double kernel[] = new double[objectCenters.size()];
		for (int i=0; i<kernel.length; i++){
			kernel[i] =  getAverageDeviation(objectCenters.get(i).getIndex());
		}
		for (int i=0; i<objectCount; i++){
			double kernelBidder = getAverageDeviation(i);
			if (kernelBidder<kernel[objectArray[i].getAreaNumber()]){
				objectCenters.set(objectArray[i].getAreaNumber(),objectArray[i]);
				redefinitionClasses = true;
			}
		}
		return redefinitionClasses;
	}

	private static double calculateNormDistance(){
		double normDistance = 0;
		for(int i=0; i<objectCenters.size(); i++){
			for(int j=0; j<objectCenters.size(); j++){
				normDistance+=Math.sqrt(Math.pow(objectCenters.get(i).getX()-objectCenters.get(j).getX(),2)+Math.pow(objectCenters.get(i).getY()-objectCenters.get(j).getY(),2));
			}
		}
		return normDistance/(2*objectCenters.size()*objectCenters.size()-1);
	}

	private static double findMaximumDistanseFromClassKernel(){
		double maxDistance = 0;
 		for (int i=0; i< objectArray.length; i++){
 			double distance = Math.sqrt(Math.pow(objectArray[i].getX()-objectCenters.get(objectArray[i].getAreaNumber()).getX(),2)+Math.pow(objectArray[i].getY()-objectCenters.get(objectArray[i].getAreaNumber()).getY(),2));
 			if (distance>maxDistance){
 				maxDistance = distance;
 				indexOfObjectWithMaxDistanse = i;
 			}
 		}
 		return maxDistance;
	}

}
