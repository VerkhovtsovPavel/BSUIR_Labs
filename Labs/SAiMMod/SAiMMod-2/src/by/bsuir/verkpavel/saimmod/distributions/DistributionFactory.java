package by.bsuir.verkpavel.saimmod.distributions;

import java.util.Scanner;

public class DistributionFactory {
    private static Scanner in = new Scanner(System.in);
    private static DistributionFactory instance;

    public static DistributionFactory getInstance() {
        if (instance == null) {
            instance = new DistributionFactory();
        }
        return instance;
    }

    private DistributionFactory() {
    };
    
    public BaseDistribution createDistribution(String type){
        double lambda;
        double a, b;
        int n;
        double mathExp;
        double sigma;
        System.out.print("Enter count of elements > ");
        int length = in.nextInt();

        switch (type) {
        case "Exponensial":
            System.out.print("Enter lambda > ");
            lambda = in.nextDouble();
            return new ExponensialDistribution(lambda, length);
        case "Gamma":
            System.out.print("Enter lambda and N > ");
            lambda = in.nextDouble();
            n = in.nextInt();
            return new GammaDistribution(lambda, n, length);
        case "Gauss":
            System.out.print("Enter math expertion and sigma > ");
            mathExp = in.nextDouble();
            sigma = in.nextDouble();
            return new GaussDistribution(mathExp, sigma, length);
        case "Heiman":
            System.out.print("Enter a and b > ");
            a = in.nextDouble();
            b = in.nextDouble();
            return new HeimanDistribution(a, b, length);
        case "Simpson":
            System.out.print("Enter a and b > ");
            a = in.nextDouble();
            b = in.nextDouble();
            return new SimpsonDistribution(a, b, length);
        case "Uniform":
            System.out.print("Enter a and b > ");
            a = in.nextDouble();
            b = in.nextDouble();
            return new UniformDistribution(a, b, length);
        }
        return null;
    }
}
