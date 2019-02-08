package subjects;


public class SampleSet {
	private Sample[] samples;
	
	public SampleSet(int classCount, int studySampleSize, int featuresCount){
		samples = new Sample[classCount];
		initialSamples(studySampleSize, featuresCount);
	}
	
	public Sample getSample(int number){
		return samples[number];
	}
	
	@Override
	public String toString(){
		String result = "";
		
		for(int i=0; i< samples.length; i++){
			result+="Class #"+String.valueOf(i)+"\n";
			result+=samples[i].toString();
		}
		return result;
				
	}
	
	public int getFeature(int classNumber, int subjectNumber, int featureNumber){
		return samples[classNumber].getSubject(subjectNumber).getFeature(featureNumber);
	}
	
	private void initialSamples(int studySampleSize, int featuresCount){
		for (int i=0; i<samples.length;i++){
			samples[i]= new Sample(studySampleSize, featuresCount);
		}
	}
}
