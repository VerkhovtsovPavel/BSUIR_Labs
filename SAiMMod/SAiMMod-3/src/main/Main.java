package main;

import java.util.Scanner;

import queuingSystem.clientGenerator.ClientGenerator;
import queuingSystem.firstStepOfProcessing.FirstStepOfProcessing;
import queuingSystem.queue.QSQueue;
import queuingSystem.secondStepOfProcessing.SecondStepOfProcessing;

public class Main {
	private static Scanner in = new Scanner(System.in);
    private static double prodationOfGenerator = 0.75;
    private static double prodationOfFirstStepOfProcessing = 0.7;
    private static double prodationOfSecondStepOfProcessing = 0.65;
    
    private static int countOfTacts = 1000000000;
    
    public static void main(String[] args) {
    	System.out.print("Please input count of tacts > ");
    	countOfTacts = in.nextInt();

    	System.out.print("Please input elements probations > ");
    	prodationOfGenerator = in.nextDouble();
    	prodationOfFirstStepOfProcessing = in.nextDouble();
    	prodationOfSecondStepOfProcessing = in.nextDouble();
    	
    	
       SecondStepOfProcessing secondStepOfProcessing = new SecondStepOfProcessing(prodationOfSecondStepOfProcessing);
       QSQueue cmoQueue = new QSQueue(secondStepOfProcessing);
       FirstStepOfProcessing firstStepOfProcessing = new FirstStepOfProcessing(prodationOfFirstStepOfProcessing, cmoQueue);
       ClientGenerator clientGenerator = new ClientGenerator(firstStepOfProcessing, prodationOfGenerator);

       for(int i =0; i< countOfTacts; i++){
           clientGenerator.tact();
           firstStepOfProcessing.tact();
           cmoQueue.tact();
           secondStepOfProcessing.tact();
           
           clientGenerator.pushClient(true);
       }
       System.out.println("Count of tacts > "+ countOfTacts);
       System.out.println("Absolutely bandwidth> "+(float)secondStepOfProcessing.processedTask / countOfTacts);
       System.out.println("Average queue length> "+(float)cmoQueue.totalLengthQuequ/countOfTacts);
       System.out.println("Average time in queue> "+(float)cmoQueue.totalTimeInQuequ/secondStepOfProcessing.processedTask);
    }
}
