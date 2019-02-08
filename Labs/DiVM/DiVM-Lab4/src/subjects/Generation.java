package subjects;

import java.util.Random;

public class Generation {
	private SubjectsSet[] generation;
	private int population;
	private SubjectsList subjects;
	private double backpackCapacity;

	public Generation(int population, double backpackCapacity, SubjectsList subjects) {
		this.generation = new SubjectsSet[population];
		this.population = population;
		this.subjects = subjects;
		this.backpackCapacity = backpackCapacity;
	}

	public void createFirstGeneration(int itemsCount) {
		Random boolRand = new Random();
		for (int i = 0; i < population; i++) {
			boolean subjset[] = new boolean[itemsCount];
			for (int j = 0; j < itemsCount; j++) {
				subjset[j] = boolRand.nextBoolean();
			}
			generation[i] = new SubjectsSet(subjset, backpackCapacity,subjects);
		}
	}

	public SubjectsSet getMember(int i) {
		return generation[i];
	}

	public void setMember(int i, SubjectsSet subj) {
		this.generation[i] = subj;
	}

	public int getPopulation() {
		return population;
	}
}
