package by.bsuir.verkpavel.saimmod.main;

import java.util.Scanner;

import by.bsuir.verkpavel.saimmod.distributions.BaseDistribution;
import by.bsuir.verkpavel.saimmod.distributions.DistributionFactory;

public class Main {
    private static Scanner in = new Scanner(System.in);
    public static void main(String[] args){
        System.out.print("Enter type of distribution > ");
        String type = in.next();
        BaseDistribution distribution = DistributionFactory.getInstance().createDistribution(type);
        
    	distribution.build();
    	distribution.consoleInfo();
    	distribution.drawHistogram();
    }
}
