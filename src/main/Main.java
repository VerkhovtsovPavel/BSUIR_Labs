package main;

import graphics.GraphicsClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import objects.Object;

/**
 * Main class.
 * @author Pavel_Verkhovtsov
 */
public class Main {

	private static Object[] objectArray;
	private static Object[] objectCenters;
	private static final int DISPERSION = 1000;
	private static int classCount;
	private static int objectCount;

	/**
	 * Main method.
	 * @param args console arguments
	 */
	@SuppressWarnings("resource")
	public static void main(final String[] args){
		System.out.print("Input count objects: ");
		objectCount = (new Scanner(System.in)).nextInt();

		objectArray = new Object[objectCount];
		Random rand = new Random();
		for(int i=0; i< objectCount; i++){
			objectArray[i] = new Object(rand.nextInt(DISPERSION), rand.nextInt(DISPERSION));
		}

		System.out.print("Input count classes: ");
		classCount = (new Scanner(System.in)).nextInt();

		objectCenters = new Object[classCount];
		for(int i=0; i< classCount; i++){
			objectCenters[i]=objectArray[rand.nextInt(objectCount)];
			// TODO Add check double selected.
		}

		for (int i=0; i< objectCount; i++){
			objectArray[i].choiceArea(objectCenters);
		}
		
		ArrayList <ArrayList> objectLists = new ArrayList<ArrayList>();
		
		

		GraphicsClass.setScreenSize(DISPERSION);
		GraphicsClass.setObjectArray(objectArray);
		GraphicsClass.visualizeClasses();

		System.out.println("Finish");
		// TODO Best choice centers

	}
}
