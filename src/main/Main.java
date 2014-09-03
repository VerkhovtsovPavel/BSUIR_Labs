package main;

import graphics.GraphicsClass;

import java.awt.Graphics;
import java.util.Random;
import java.util.Scanner;
import objects.Object;

public class Main {

	private static Object[] objectArray;
	private static Object[] objectCenters;
	private static int dispersionObjects = 600;
	private static int classCount;
	private static int objectCount;

	@SuppressWarnings("resource")
	public static void main(final String[] args){
		System.out.print("Input count objects: ");
		objectCount = (new Scanner(System.in)).nextInt();

		objectArray = new Object[objectCount];
		Random rand = new Random();
		for(int i=0; i< objectCount; i++){
			objectArray[i] = new Object(rand.nextInt(dispersionObjects), rand.nextInt(dispersionObjects));
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

		GraphicsClass.screenSize = dispersionObjects;
		GraphicsClass.objectArray = objectArray;
		GraphicsClass.visualizeClasses();

		System.out.println("Finish");
		// TODO Best choice centers

	//	graphicsClass.visualizeClasses();
	}
}
