package subjects;

public class SubjectsSet {
	private boolean[] set;
	
	public SubjectsSet(boolean[] subjSet) {
		this.setSet(subjSet);
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
		return new SubjectsSet(childSet);	
	}
	
	public int getLength(){
		return set.length;
	}
}
