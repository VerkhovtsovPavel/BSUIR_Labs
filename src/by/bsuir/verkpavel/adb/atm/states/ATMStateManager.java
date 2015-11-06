package by.bsuir.verkpavel.adb.atm.states;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import by.bsuir.verkpavel.adb.atm.remote.IRemoteBank;

public class ATMStateManager {
    private Map<String,BaseATMState> states;
    
    public ATMStateManager(JPanel mainATMPanel, IRemoteBank server){
        states =  new HashMap<String,BaseATMState>();
        states.put("NoConnection", new NotConnectionATMState(mainATMPanel, server));
        states.put("Authentication", new AuthenticationATMState(mainATMPanel,server));
    }
    
    public BaseATMState getState(String state){
        return states.get(state);
    }
}
