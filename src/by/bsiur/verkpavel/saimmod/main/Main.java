package by.bsiur.verkpavel.saimmod.main;

import by.bsiur.verkpavel.saimmod.distributions.BaseDistribution;
import by.bsiur.verkpavel.saimmod.distributions.UniformDistribution;

public class Main {
    public static void main(String[] args){
        //3, 69712349, 1
    	BaseDistribution distribution = new UniformDistribution(3, 69712349, 1);
    	distribution.build();
    	distribution.consoleInfo();
    	distribution.drawHistogram();
    }
}
