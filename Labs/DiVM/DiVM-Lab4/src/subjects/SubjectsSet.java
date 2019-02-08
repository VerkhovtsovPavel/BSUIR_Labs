package subjects;

import java.util.Random;

public class SubjectsSet {
	private boolean[] set;
	private double fitnessValue;
	private double backpackCapacity;
	private SubjectsList subjects;
	
	public SubjectsSet(boolean[] subjSet, double backpackCapacity, SubjectsList subjects) {
		this.setSet(subjSet);
		this.backpackCapacity = backpackCapacity;
		this.subjects = subjects;
		fitnessValue = fitnessFunction();
		
	}

	public boolean[] getSet() {
		return set;
	}

	public void setSet(boolean[] set) {
		this.set = set;
	}
	
	public SubjectsSet makeChild(SubjectsSet secondParent){
		boolean[] childSet = new boolean[this.set.length];
		
		for (int i=0; i<this.getLength()/2; i++){
			childSet[i] = this.set[i];
		}
		
		for (int i=this.getLength()/2; i< set.length; i++){
			childSet[i] = secondParent.getSet()[i];
		}
		return new SubjectsSet(childSet, backpackCapacity, subjects);	
	}
	
	public int getLength(){
		return set.length;
	}
	
	public void mutartion(){
		Random random = new Random();
		set[random.nextInt(set.length)] = random.nextBoolean();
	}
	
	public double getFitnessValue(){
		return fitnessValue;
	}
	
	private double fitnessFunction() {
		double price = 0;
		double weight = 0;
		for (int i = 0; i < set.length; i++) {
			if (set[i]) {
				price += subjects.getSubject(i).getPrice();
				weight += subjects.getSubject(i).getSize();
			}
		}

		weight = Math.abs(backpackCapacity - weight);
		if (backpackCapacity - weight < 0) {
			weight *= 10;
		}

		// TODO Review fitness function. Severe punishment for overweight.
		return price / weight;
	}
}
