package subjects;

import java.util.Random;

public class Generation {
	private SubjectsSet[] generation;
	private int population;

	public Generation(int population) {
		this.generation = new SubjectsSet[population];
		this.population = population;
	}

	public void createFirstGeneration(int itemsCount) {
		Random boolRand = new Random();
		for (int i = 0; i < population; i++) {
			boolean subjset[] = new boolean[itemsCount];
			for (int j = 0; j < itemsCount; j++) {
				subjset[j] = boolRand.nextBoolean();
			}
			generation[i] = new SubjectsSet(subjset);
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
