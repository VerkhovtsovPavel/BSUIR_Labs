package by.bsiur.verkpavel.saimmod.main;

import by.bsiur.verkpavel.saimmod.distributions.BaseDistribution;
import by.bsiur.verkpavel.saimmod.distributions.UniformDistribution;

public class Main {
    public static void main(String[] args){
        //170, 33, 7
    	BaseDistribution distribution = new UniformDistribution(170, 33, 7);
    	distribution.build();
    	distribution.consoleInfo();
    	distribution.drawHistogram();
    }
}
