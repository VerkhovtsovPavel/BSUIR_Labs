package queuingSystem.queue;

import java.util.LinkedList;
import java.util.Queue;

import queuingSystem.BaseQSElement;
import queuingSystem.secondStepOfProcessing.SecondStepOfProcessing;

public class CMOQueue extends BaseQSElement {
    public int totalTimeInQuequ;
    public int totalLengthQuequ;
    
    private Queue<Client> queue = new LinkedList<Client>();
    private SecondStepOfProcessing ssp;

    public CMOQueue(SecondStepOfProcessing ssp) {
        this.ssp = ssp;
    }

    @Override
    public void tact() {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean pushClient(boolean client) {
        statictic();
        if (queue.size() > 0) {
            if (ssp.pushClient(true)) {
                totalTimeInQuequ+=queue.poll().getTime();
            }
        }
        if (client) {
            if (!ssp.pushClient(true)) {
                if (queue.size() < 2) {
                    queue.add(new Client());
                    return true;
                } else {
                    return false;
                }
            }else{
                return true;
            }
        }
        return true;
    }
    
    private void statictic(){
        for(Client client : queue){
            client.increaseTime();
        }
        totalLengthQuequ+=queue.size();
    }
}
