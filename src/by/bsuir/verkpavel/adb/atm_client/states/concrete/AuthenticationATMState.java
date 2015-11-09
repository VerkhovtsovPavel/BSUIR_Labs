package by.bsuir.verkpavel.adb.atm_client.states.concrete;

import java.rmi.RemoteException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import by.bsuir.verkpavel.adb.atm_client.states.ATMStateManager;
import by.bsuir.verkpavel.adb.atm_client.states.BaseATMState;
import by.bsuir.verkpavel.adb.atm_client.states.Stateble;
import by.bsuir.verkpavel.adb.shared.IRemoteBank;

public class AuthenticationATMState extends BaseATMState {
    
    private JTextField cardNumberTb;
    private JTextField pinCodeTb;
    private JLabel cardNumberLb;
    private JLabel pinCodelb;

    public AuthenticationATMState(JPanel atmPanel, IRemoteBank server, Stateble stateble, ATMStateManager stateManager) {
        super(atmPanel, server, stateble, stateManager);
        
        cardNumberTb = new JTextField();
        cardNumberTb.setBounds(156, 259, 205, 20);
        cardNumberTb.setColumns(10);
        
        pinCodeTb = new JTextField();
        pinCodeTb.setColumns(10);
        pinCodeTb.setBounds(156, 307, 205, 20);
        
        cardNumberLb = new JLabel("Номер карты");
        cardNumberLb.setBounds(156, 234, 100, 14);
        
        pinCodelb = new JLabel("Пин код");
        pinCodelb.setBounds(156, 290, 46, 14);
    }

    @Override
    public void on() {
        addComponent(cardNumberTb);
        addComponent(pinCodeTb);
        addComponent(cardNumberLb);
        addComponent(pinCodelb);
    }

    @Override
    public void off() {
        removeComponent(cardNumberTb);
        removeComponent(pinCodeTb);
        removeComponent(cardNumberLb);
        removeComponent(pinCodelb);

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
        return this.pinCodeTb.getText();
    }

    private String getCardNumber() {
        return this.cardNumberTb.getText();
    }

}
