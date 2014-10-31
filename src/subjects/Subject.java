package subjects;

import java.util.Random;

public class Subject {
	private int[] features;
	private final int DISTERSION =10;
	
	public Subject(int featuresCount) {
		features = new int[featuresCount+1];
		setRandomValues(DISTERSION);
	}
	
	private void setRandomValues(int dispersion){
		Random featureValue = new Random();
		for (int i=0; i<features.length; i++){
			features[i]=featureValue.nextInt(dispersion);
		}
		features[features.length-1]=1;
	}
	
	public int getFeature(int number){
		return features[number];
	}
	
	@Override
	public String toString(){
		String result = "{";
		
		for(int i=0; i<features.length-1; i++){
			result+=String.valueOf(features[i])+";";
		}
		
		result+="}\n";
		
		return result;
	}
}
