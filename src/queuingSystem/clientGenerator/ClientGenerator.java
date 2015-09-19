package queuingSystem.clientGenerator;

import java.util.Random;

import queuingSystem.BaseQSElement;
import queuingSystem.firstStepOfProcessing.FirstStepOfProcessing;

public class ClientGenerator extends BaseQSElement{
    private Random random;
    private FirstStepOfProcessing fsp;
    private double probation;
    private boolean isBlocked = false;
    public boolean isUserCreated = false;
    public ClientGenerator(FirstStepOfProcessing fsp, double probation){
        random = new Random();
        this.fsp = fsp;
        this.probation = probation;
    };
    
    public void tact(){
       if(!isBlocked){
           isUserCreated = generateClient();
       }
       else{
           isUserCreated = true;
       } 
    }
    
    public boolean pushClient(boolean client){
        if(isUserCreated)
           if(fsp.pushClient(isUserCreated)){
               isBlocked = false;
           }
           else{
               isBlocked = true;
           }
        return true;
    }
    
    private boolean generateClient(){
        return random.nextDouble() > probation;
    }
    

}
