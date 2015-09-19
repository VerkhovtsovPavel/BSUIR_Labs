package queuingSystem.secondStepOfProcessing;

import java.util.Random;

import queuingSystem.BaseQSElement;

public class SecondStepOfProcessing extends BaseQSElement{
    private double probation;
    private boolean isFree = true;
    private boolean isTaskCompleted = false;
    private Random random;
    
    public int processedTask;
    
    public SecondStepOfProcessing(double probation){
        this.probation = probation;
        this.random = new Random();
    }
    
    public boolean pushClient(boolean incomingClient) {
        if(isFree)
        {
            if(incomingClient){
                isFree = false;
            }
            return true;
        }else{
            return false;
        }
    }


    @Override
    public void tact() {
        if(!isFree){
            isTaskCompleted = generateTaskCompleted();
        }else{
            isTaskCompleted = false;
        }
        
        if(isTaskCompleted){
            isFree = true;
            processedTask++;
        }
    }

    private boolean generateTaskCompleted() {
        return random.nextDouble() > probation;
    }

}
