package clientGenerator;

import java.util.Random;

public class ClientGenerator {
    private Random ramdom;
    private ClientGeneratorState state;
    public ClientGenerator(){
        ramdom = new Random();
    };
    
    public void tact(){
        state.generateClient();
    }
    
    

}
