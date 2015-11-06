package by.bsuir.verkpavel.adb.atm.states;

import java.rmi.RemoteException;

import javax.swing.JPanel;

import by.bsuir.verkpavel.adb.server.remote.IRemoteBank;

public class AuthenticationATMState extends BaseATMState {

    public AuthenticationATMState(JPanel atmPanel, IRemoteBank server, Stateble stateble, ATMStateManager stateManager) {
        super(atmPanel, server, stateble, stateManager);
    }

    @Override
    public void on() {
        // TODO Auto-generated method stub

    }

    @Override
    public void off() {
        // TODO Auto-generated method stub

    }

    @Override
    public void processHardButton(int buttonNumber) {
       switch(buttonNumber){
           case 4:
               tryAuthenticateCard();
       }

    }

    private void tryAuthenticateCard() {
        String cardNumber = getCardNumber();
        String pinCode = getPinCode();
        try {
            if(getServer().isAuthenticate(cardNumber, pinCode)){
               setState(getStateManager().getState("ChoiceOperation")); 
             }
        } catch (RemoteException e) {
            setState(getStateManager().getState("NoConnection"));
        }
        
    }

    private String getPinCode() {
        // TODO Get value from card number field
        return null;
    }

    private String getCardNumber() {
        // TODO Get value from card number field
        return null;
    }

}
