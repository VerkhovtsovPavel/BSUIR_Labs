package subjects;

import java.util.ArrayList;
import java.util.Random;

public class SubjectsList {
	private ArrayList<Subject> subjects = new ArrayList<Subject>();

	public SubjectsList(int listSize, int distersion) {
		createSubjectArray(listSize, distersion);
	}

	private void createSubjectArray(int count, int distersion) {
		Random random = new Random();
		for (int i = 0; i < count; i++) {
			this.subjects.add(new Subject(random.nextInt(distersion), random.nextInt(distersion)));
		}
	}
	
	public Subject getSubject(int i){
		return subjects.get(i);
	}


	public void printSubjectList(){
		System.out.println("-------------------------");
		System.out.println("|Number\t|Price\t|Weight\t|");
		for (int i=0; i<subjects.size(); i++) {
			System.out.println("|"+String.valueOf(i+1)+"\t|"+String.valueOf(subjects.get(i).getPrice())+"\t|"+String.valueOf(subjects.get(i).getSize())+"\t|");
		}
		System.out.println("-------------------------");
	}
}


