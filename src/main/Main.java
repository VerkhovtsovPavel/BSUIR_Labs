package main;

import queuingSystem.clientGenerator.ClientGenerator;
import queuingSystem.firstStepOfProcessing.FirstStepOfProcessing;
import queuingSystem.queue.CMOQueue;
import queuingSystem.secondStepOfProcessing.SecondStepOfProcessing;

public class Main {
    private static final double prodationOfGenerator = 0.75;
    private static final double prodationOfFirstStepOfProcessing = 0.65;
    private static final double prodationOfSecondStepOfProcessing = 0.60;
    
    private static final int countOfTacts = 1000;
    
    public static void main(String[] args) {
       SecondStepOfProcessing secondStepOfProcessing = new SecondStepOfProcessing(prodationOfSecondStepOfProcessing);
       CMOQueue cmoQueue = new CMOQueue(secondStepOfProcessing);
       FirstStepOfProcessing firstStepOfProcessing = new FirstStepOfProcessing(prodationOfFirstStepOfProcessing, cmoQueue);
       ClientGenerator clientGenerator = new ClientGenerator(firstStepOfProcessing, prodationOfGenerator);

       for(int i =0; i< countOfTacts; i++){
           clientGenerator.tact();
           firstStepOfProcessing.tact();
           cmoQueue.tact();
           secondStepOfProcessing.tact();
           
           clientGenerator.pushClient(true);
       }
       
       System.out.println("Total processed task> "+secondStepOfProcessing.processedTask);
       System.out.println("Average queue length> "+(float)cmoQueue.totalLengthQuequ/countOfTacts);
       System.out.println("Average time in queue> "+(float)cmoQueue.totalTimeInQuequ/secondStepOfProcessing.processedTask);
    }


}
