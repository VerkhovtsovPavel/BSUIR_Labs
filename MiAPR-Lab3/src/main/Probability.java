package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Probability {
	
	private double mathExpectation;
	private double standDeviation;
	public double probalityOfClass;
	public ArrayList<Integer> randomVariable;
	
	Probability(ArrayList<Integer> randomVariables, double probalityOfClass){
		this.randomVariable = new ArrayList<Integer>( new HashSet<Integer>(randomVariables));
		Collections.sort(this.randomVariable);
		this.probalityOfClass = probalityOfClass;
		this.mathExpectation = mathematicalExpectation(randomVariables);
		this.standDeviation = standardDeviation(randomVariables);
	}

	private double mathematicalExpectation(ArrayList<Integer> randomVariables){
		int sum = 0;
		for(int i=0; i<randomVariables.size(); i++){
			sum+=randomVariables.get(i);
		}
		return sum/randomVariables.size();
	}
	
	private double standardDeviation(ArrayList<Integer> randomVariables){
		double sum = 0;
		for(int i=0; i<randomVariables.size(); i++){
			sum+=Math.pow(randomVariables.get(i)-mathExpectation,2);
		}
		return Math.sqrt(sum/randomVariables.size());
	}
	
	public double probabilityDensity(double point){
		double first = (standDeviation*Math.sqrt(Math.PI*2));
		double second = Math.exp(Math.pow((point-mathExpectation)/standDeviation,2)/-2);
		return second/first;	
	}
}
