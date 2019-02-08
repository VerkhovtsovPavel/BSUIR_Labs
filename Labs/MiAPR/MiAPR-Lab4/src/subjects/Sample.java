package subjects;

public class Sample {
	private Subject[] subjects;

	public Sample(int studySampleSize, int featuresCount){
		subjects = new Subject[studySampleSize];
		initialSubjects(featuresCount);
	}
	
	public Subject getSubject(int number){
		return subjects[number];
	}
	
	@Override
	public String toString(){
		String result = "";
		for(int i=0; i< subjects.length; i++){
			result+=subjects[i].toString();
		}
		return result;
	}
	
	private void initialSubjects(int featuresCount){
		for (int i=0; i<subjects.length;i++){
			subjects[i]= new Subject(featuresCount);
		}
	}
}
