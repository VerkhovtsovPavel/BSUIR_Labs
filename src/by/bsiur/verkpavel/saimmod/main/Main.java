package by.bsiur.verkpavel.saimmod.main;

import java.util.Scanner;

import by.bsiur.verkpavel.saimmod.distributions.BaseDistribution;
import by.bsiur.verkpavel.saimmod.distributions.UniformDistribution;

public class Main {
    private static Scanner consoleReader = new Scanner(System.in);
    public static void main(String[] args){
        //3, 837123, 1
        System.out.println("Enter distribution parameters (a, m, R0, length)");
        int a = consoleReader.nextInt();
        int m = consoleReader.nextInt();
        int r0 = consoleReader.nextInt();
        int length = consoleReader.nextInt();
        
    	BaseDistribution distribution = new UniformDistribution(a, m, r0, length);
    	distribution.build();
    	distribution.consoleInfo();
    	distribution.drawHistogram();
    }
}
