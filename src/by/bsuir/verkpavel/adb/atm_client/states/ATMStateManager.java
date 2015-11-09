package by.bsuir.verkpavel.adb.atm_client.states;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import by.bsuir.verkpavel.adb.atm_client.states.concrete.AuthenticationATMState;
import by.bsuir.verkpavel.adb.atm_client.states.concrete.NotConnectionATMState;
import by.bsuir.verkpavel.adb.shared.IRemoteBank;

public class ATMStateManager {
    private Map<String,BaseATMState> states;
    
    public ATMStateManager(JPanel mainATMPanel, IRemoteBank server, Stateble stateble){
        states =  new HashMap<String,BaseATMState>();
        states.put("NoConnection", new NotConnectionATMState(mainATMPanel, server, stateble, this));
        states.put("Authentication", new AuthenticationATMState(mainATMPanel,server, stateble, this));
    }
    
    public BaseATMState getState(String state){
        return states.get(state);
    }
}
