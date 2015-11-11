package by.bsuir.verkpavel.adb.atm_client.states.concrete;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import by.bsuir.verkpavel.adb.atm_client.states.ATMStateManager;
import by.bsuir.verkpavel.adb.atm_client.states.BaseATMState;
import by.bsuir.verkpavel.adb.atm_client.states.Stateble;
import by.bsuir.verkpavel.adb.shared.IRemoteBank;

public class EnterCardNumberATMState extends BaseATMState {
    
    private JTextField cardNumberTb;
    private JLabel cardNumberLb;


    public EnterCardNumberATMState(JPanel atmPanel, IRemoteBank server, Stateble stateble, ATMStateManager stateManager) {
        super(atmPanel, server, stateble, stateManager);

      //TODO Add card number format
        cardNumberLb = new JLabel("Номер карты");
        cardNumberLb.setBounds(156, 234, 100, 14);
        
        cardNumberTb = new JTextField();
        cardNumberTb.setBounds(156, 259, 205, 20);
        cardNumberTb.setColumns(10);
    }

    @Override
    public void on() {
        addComponent(cardNumberTb);
        addComponent(cardNumberLb);
    }

    @Override
    public void off() {
        removeComponent(cardNumberLb);
        removeComponent(cardNumberTb);
        cardNumberTb.setText("");
    }

    @Override
    public void processHardButton(int buttonNumber) {
       switch(buttonNumber){
           case 4:
               getOperationList().addOperation("cardNumber", getCardNumber());
               setState(getStateManager().getState("EnterPinCode"));
               break;
           case 3:
               this.cardNumberTb.setText("");
               break;
       }

    }

   
    private String getCardNumber() {
        return this.cardNumberTb.getText();
    }

}
