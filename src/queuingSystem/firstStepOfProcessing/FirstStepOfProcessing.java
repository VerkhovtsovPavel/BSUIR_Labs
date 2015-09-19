package queuingSystem.firstStepOfProcessing;

import java.util.Random;

import queuingSystem.BaseQSElement;
import queuingSystem.queue.CMOQueue;

public class FirstStepOfProcessing extends BaseQSElement{
    private double probation;
    private CMOQueue queue;
    private boolean isFree = true;
    private boolean isTaskCompleted = false;
    private Random random;
    
    public FirstStepOfProcessing(double probation, CMOQueue queue){
        this.probation = probation;
        this.queue = queue;
        this.random = new Random();
    }
    
    public boolean pushClient(boolean incomingClient) {
        queue.pushClient(isTaskCompleted);
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
        }
    }


    private boolean generateTaskCompleted() {
        return random.nextDouble() > probation;
    }
}
