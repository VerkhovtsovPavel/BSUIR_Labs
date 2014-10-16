package main;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import subjects.Subject;
import subjects.SubjectsSet;

public class Main {

	private static Scanner in = new Scanner(System.in);
	private static ArrayList<Subject> subjects;
	private static int backpackCapacity;
	
	private final static int GENERATION = 20;
	private final static int SUBJECTS_IN_GENERATION = 4;
	
	
	public static void main(String args[]){
		System.out.print("Enter the capacity of the backpack:\t");
		backpackCapacity = in.nextInt();
		System.out.print("Enter the number of items:\t");
		int itemsCount = in.nextInt();
		subjects = createSubjectArray(itemsCount);
		
		SubjectsSet cureentGeneration[] = new SubjectsSet[SUBJECTS_IN_GENERATION];
		createFirstGeneration(cureentGeneration, itemsCount);
		
		for (int i=0; i<GENERATION; i++){
			
		}
	}
	
	
	private static ArrayList<Subject> createSubjectArray(int count){
		ArrayList<Subject> subj = new ArrayList<Subject>();
		Random random = new Random();
		for (int i=0; i<count; i++){
			subj.add(new Subject(random.nextInt(100), random.nextInt(100)));
		}
		return subj;
	}
	
	private double fitnesFunction(SubjectsSet subjectsSet){
		int price = 0;
		int weight = 0;
		for(int i=0; i<subjectsSet.getLength(); i++){
			if (subjectsSet.getSet()[i]){
				price+=subjects.get(i).getPrice();
				weight+=subjects.get(i).getSize();
			}
		}
		return price/(Math.abs(backpackCapacity-weight)*2);
	}
	
	private static void createFirstGeneration(SubjectsSet[] generation, int itemsCount){
		Random boolRand = new Random();
		boolean subjset[] = new boolean[itemsCount]; 
		for(int i=0; i<SUBJECTS_IN_GENERATION; i++){
			for(int j=0; j<itemsCount; j++){
				subjset[j]= boolRand.nextBoolean();
			}
			generation[i] = new SubjectsSet(subjset);
		}
	}
	
}
