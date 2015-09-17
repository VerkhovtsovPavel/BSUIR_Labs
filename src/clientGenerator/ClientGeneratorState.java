package clientGenerator;

import java.util.Random;


public enum ClientGeneratorState {
    BLOKED() {
        @Override
        boolean generateClient() {
            return true; 
        }

    },
    UNBROKEN() {
        @Override
        boolean generateClient() {
            return rand.nextDouble() > 0.75;
        }
    };
    static Random rand = new Random();

    abstract boolean generateClient();
    
}
