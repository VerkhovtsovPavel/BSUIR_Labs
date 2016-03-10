package main;

import java.util.Scanner;

import subjects.Generation;
import subjects.SubjectsList;
import subjects.SubjectsSet;

public class Main {

	private static Scanner in = new Scanner(System.in);
	private static SubjectsList subjects;
	private static int backpackCapacity;
	private static int glBest;

	private final static int GENERATION = 10000;
	private final static int SUBJECTS_IN_GENERATION = 4;

	public static void main(String args[]) {
		System.out.print("Enter the capacity of the backpack:\t");
		backpackCapacity = in.nextInt();
		System.out.print("Enter the number of items:\t");
		int itemsCount = in.nextInt();
		subjects = new SubjectsList(itemsCount, 100);
		subjects.printSubjectList();

		Generation cureentGeneration = new Generation(SUBJECTS_IN_GENERATION, backpackCapacity, subjects);
		cureentGeneration.createFirstGeneration(itemsCount);

		// TODO total refactor. Add methods Remove global variable
		// TODO Remake crossing!!!!
		// TODO Global access to subjects

		int best = 0;
		for (int i = 0; i < GENERATION; i++) {
			best = 0;
			int worst = 0;

			double max = 0;
			double min = Double.MAX_VALUE;
			for (int j = 0; j < SUBJECTS_IN_GENERATION; j++) {
				double fitnes = fitnessFunction(cureentGeneration.getMember(j));

				if (fitnes > max) {
					best = j;
					max = fitnes;
				}

				if (fitnes < min) {
					worst = j;
					min = fitnes;
				}
			}

			Generation nextGeneration = new Generation(SUBJECTS_IN_GENERATION, backpackCapacity, subjects);
			int k = 0;
			for (int j = 0; j < cureentGeneration.getPopulation(); j++) {
				if (best == worst) {
					for (int g = 0; g < cureentGeneration.getPopulation(); g++) {
						cureentGeneration.getMember(g).mutartion();
					}
				}
				if (j != best && j != worst) {
					try {
						// TODO Refactor for N subject in generation.
						nextGeneration.setMember(k, cureentGeneration.getMember(best).makeChild(cureentGeneration.getMember(j)));
						nextGeneration.getMember(k).mutartion();
						nextGeneration.setMember(++k, cureentGeneration.getMember(j).makeChild(cureentGeneration.getMember(best)));
						k++;
					} catch (ArrayIndexOutOfBoundsException e) {
					}
				}
			}
			cureentGeneration = nextGeneration;
		}
		glBest = best;
		answer(cureentGeneration);
	}

	private static double fitnessFunction(SubjectsSet subjectsSet) {
		double price = 0;
		double weight = 0;
		for (int i = 0; i < subjectsSet.getLength(); i++) {
			if (subjectsSet.getSet()[i]) {
				price += subjects.getSubject(i).getPrice();
				weight += subjects.getSubject(i).getSize();
			}
		}

		weight = Math.abs(backpackCapacity - weight);
		if (backpackCapacity - weight < 0) {
			weight *= 10;
		}
		return price / weight;
	}

	private static void printBest(Generation cureentGeneration, int best) {
		//TODO Analyze -1 in best
		if (best==-1){
			System.out.println("Overweight");
			best = glBest;
		}
		int totalPrice = 0;
		int totalWeight = 0;
		System.out.print("Subjects in backpack: ");
		for (int i = 0; i < cureentGeneration.getMember(best).getLength(); i++) {
			if (cureentGeneration.getMember(best).getSet()[i]) {
				System.out.print(String.valueOf(i + 1) + " ");
				totalPrice += subjects.getSubject(i).getPrice();
				totalWeight += subjects.getSubject(i).getSize();
			}
		}
		System.out.println();
		System.out.println("Price: " + String.valueOf(totalPrice));
		System.out.println("Weight: " + String.valueOf(totalWeight));
	}

	private static void answer(Generation cureentGeneration) {
		int index = -1;
		for (int j = 0; j < cureentGeneration.getPopulation(); j++) {
			int max = 0;
			int totalPrice = 0;
			int totalWeight = 0;
			for (int i = 0; i < cureentGeneration.getMember(j).getLength(); i++) {
				if (cureentGeneration.getMember(j).getSet()[i]) {
					totalPrice += subjects.getSubject(i).getPrice();
					totalWeight += subjects.getSubject(i).getSize();
				}

			}
			if (backpackCapacity - totalWeight > 0 && totalPrice > max) {
				index = j;
			}
		}
		printBest(cureentGeneration, index);
	}
}
