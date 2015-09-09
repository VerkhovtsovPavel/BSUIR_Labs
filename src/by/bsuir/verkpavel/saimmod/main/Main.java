package by.bsuir.verkpavel.saimmod.main;

import by.bsuir.verkpavel.saimmod.distributions.BaseDistribution;
import by.bsuir.verkpavel.saimmod.distributions.ExponensialDistribution;
import by.bsuir.verkpavel.saimmod.distributions.GammaDistribution;
import by.bsuir.verkpavel.saimmod.distributions.GaussDistribution;
import by.bsuir.verkpavel.saimmod.distributions.LemerDistribution;
import by.bsuir.verkpavel.saimmod.distributions.UniformDistribution;

public class Main {
    public static void main(String[] args){
        //3, 69712349, 1
 //  	BaseDistribution distribution = new LemerDistribution(3, 69712349, 1, 10000000);
//   	BaseDistribution distribution = new UniformDistribution(10,100,100);
        BaseDistribution distribution = new GaussDistribution(10, 20, 15, (double) 2.85, 10000);
//    	BaseDistribution distribution = new ExponensialDistribution(10, 20, 23, 100000);
//    	BaseDistribution distribution = new GammaDistribution(10, 2, 10000);
    	distribution.build();
    	distribution.consoleInfo();
    	distribution.drawHistogram();
    }
}
