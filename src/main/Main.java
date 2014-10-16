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

	private final static int GENERATION = 5;
	private final static int SUBJECTS_IN_GENERATION = 4;

	public static void main(String args[]) {
		System.out.print("Enter the capacity of the backpack:\t");
		backpackCapacity = in.nextInt();
		System.out.print("Enter the number of items:\t");
		int itemsCount = in.nextInt();
		subjects = createSubjectArray(itemsCount);

		SubjectsSet cureentGeneration[] = new SubjectsSet[SUBJECTS_IN_GENERATION];
		createFirstGeneration(cureentGeneration, itemsCount);

		// TODO total refactor. Add methods Remove global variable

		int best = 0;
		for (int i = 0; i < GENERATION; i++) {
			best = 0;
			int worst = 0;

			double max = 0;
			double min = Double.MAX_VALUE;
			for (int j = 0; j < SUBJECTS_IN_GENERATION; j++) {
				double fitnes = fitnessFunction(cureentGeneration[j]);

				if (fitnes > max) {
					best = j;
					max = fitnes;
				}

				if (fitnes < min)
					worst = j;
				min = fitnes;
			}

			SubjectsSet nextGeneration[] = new SubjectsSet[SUBJECTS_IN_GENERATION];
			int k = 0;
			for (int j = 0; j < cureentGeneration.length; j++) {
				if (j != best && j != worst) {
					//TODO Add mutation. Refactor for N subject in generation. 
					nextGeneration[k] = cureentGeneration[best].makeChild(cureentGeneration[j]);
					nextGeneration[++k] = cureentGeneration[j].makeChild(cureentGeneration[best]);
					k++;
				}
			}
			cureentGeneration = nextGeneration;
		}

		answer(cureentGeneration, best);
	}

	private static ArrayList<Subject> createSubjectArray(int count) {
		ArrayList<Subject> subj = new ArrayList<Subject>();
		Random random = new Random();
		for (int i = 0; i < count; i++) {
			subj.add(new Subject(random.nextInt(100), random.nextInt(100)));
		}
		return subj;
	}

	private static double fitnessFunction(SubjectsSet subjectsSet) {
		double price = 0;
		double weight = 0;
		for (int i = 0; i < subjectsSet.getLength(); i++) {
			if (subjectsSet.getSet()[i]) {
				price += subjects.get(i).getPrice();
				weight += subjects.get(i).getSize();
			}
		}
		// TODO Review fitness function. Severe punishment for overweight.
		return price / (Math.abs(backpackCapacity - weight) * 5);
	}

	
	private static void createFirstGeneration(SubjectsSet[] generation, int itemsCount) {
		Random boolRand = new Random();
		for (int i = 0; i < SUBJECTS_IN_GENERATION; i++) {
			boolean subjset[] = new boolean[itemsCount];
			for (int j = 0; j < itemsCount; j++) {
				subjset[j] = boolRand.nextBoolean();
			}
			generation[i] = new SubjectsSet(subjset);
		}
	}

	// TODO Rename first parameter. Maybe send only best set
	private static void answer(SubjectsSet[] cureentGeneration, int best) {
		int totalPrice = 0;
		int totalWeight = 0;
		for (int i = 0; i < cureentGeneration[best].getLength(); i++) {
			if (cureentGeneration[best].getSet()[i]) {
				totalPrice += subjects.get(i).getPrice();
				totalWeight += subjects.get(i).getSize();
			}
		}
		System.out.println("Price: " + String.valueOf(totalPrice));
		System.out.println("Weight: " + String.valueOf(totalWeight));
	}

}
