package by.bsuir.verkpavel.adb.atm_client.states.concrete.authentication;

import java.rmi.RemoteException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import by.bsuir.verkpavel.adb.atm_client.states.ATMStateManager;
import by.bsuir.verkpavel.adb.atm_client.states.BaseATMState;
import by.bsuir.verkpavel.adb.atm_client.states.Stateble;
import by.bsuir.verkpavel.adb.atm_client.states.States;
import by.bsuir.verkpavel.adb.shared.IRemoteBank;

public class EnterPinCodeATMState extends BaseATMState {

    private JTextField pinCodeTb;
    private JLabel pinCodelb;
    private int attemptsCounter;
    private JLabel endWork;
    private JLabel apply;

    public EnterPinCodeATMState(JPanel atmPanel, IRemoteBank server, Stateble stateble, ATMStateManager stateManager) {
        super(atmPanel, server, stateble, stateManager);
        //TODO Add pin-code format
        pinCodelb = new JLabel("Пин код");
        pinCodelb.setBounds(156, 234, 100, 14);

        pinCodeTb = new JTextField();
        pinCodeTb.setColumns(10);
        pinCodeTb.setBounds(156, 259, 205, 20);
        
        endWork = new JLabel("Завершение работы");
        endWork.setBounds(131, 472, 128, 14);
        
        apply = new JLabel("Подтветдить");
        apply.setBounds(286, 472, 118, 14);
    }

    @Override
    public void on() {
        addComponent(pinCodeTb);
        addComponent(pinCodelb);
        addComponent(apply);
        addComponent(endWork);
    }

    @Override
    public void off() {
        removeComponent(pinCodeTb);
        removeComponent(pinCodelb);
        removeComponent(apply);
        removeComponent(endWork);
        pinCodeTb.setText("");
    }

    @Override
    public void processHardButton(int buttonNumber) {
        switch (buttonNumber) {
        case 4:
            getOperationList().addOperation("pinCode", getPinCode());
            tryAuthenticateCard();
            break;
        case 1:
            pinCodeTb.setText("");
        case 3:
            destroySession();
            break;    
        }
    }

    private void tryAuthenticateCard() {
        try {
            if (getServer().isAuthenticate(getOperationList())) {
                setState(States.ChoiceOperationATMState);
            } else {
                if (attemptsCounter < 2) {
                    // TODO Show dialog Incorrect pin
                    this.pinCodeTb.setText("");
                    getOperationList().removeOperation("pinCode");
                    attemptsCounter++;
                } else {
                    // TODO Show dialog incorrect pin three times
                    attemptsCounter = 0;
                    destroySession();
                }
            }
        } catch (RemoteException e) {
            setState(States.NotConnectionATMState);
        }

    }

    private String getPinCode() {
        return this.pinCodeTb.getText();
    }

}
