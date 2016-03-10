package queuingSystem.queue;

public class Client {

    private int waitTime;

    public int getTime() {
        return this.waitTime;
    }

    public void increaseTime() {
        this.waitTime++;
    }

}
