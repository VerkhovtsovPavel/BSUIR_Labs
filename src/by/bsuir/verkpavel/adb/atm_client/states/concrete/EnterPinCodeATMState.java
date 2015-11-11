package by.bsuir.verkpavel.adb.atm_client.states.concrete;

import java.rmi.RemoteException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import by.bsuir.verkpavel.adb.atm_client.states.ATMStateManager;
import by.bsuir.verkpavel.adb.atm_client.states.BaseATMState;
import by.bsuir.verkpavel.adb.atm_client.states.Stateble;
import by.bsuir.verkpavel.adb.shared.IRemoteBank;

public class EnterPinCodeATMState extends BaseATMState {

    private JTextField pinCodeTb;
    private JLabel pinCodelb;
    private int attemptsCounter;

    public EnterPinCodeATMState(JPanel atmPanel, IRemoteBank server, Stateble stateble, ATMStateManager stateManager) {
        super(atmPanel, server, stateble, stateManager);
        //TODO Add pin-code format
        pinCodelb = new JLabel("Пин код");
        pinCodelb.setBounds(156, 290, 76, 20);

        pinCodeTb = new JTextField();
        pinCodeTb.setColumns(10);
        pinCodeTb.setBounds(156, 307, 205, 20);
    }

    @Override
    public void on() {
        addComponent(pinCodeTb);
        addComponent(pinCodelb);
    }

    @Override
    public void off() {
        removeComponent(pinCodeTb);
        removeComponent(pinCodelb);
        pinCodeTb.setText("");
    }

    @Override
    public void processHardButton(int buttonNumber) {
        switch (buttonNumber) {
        case 4:
            getOperationList().addOperation("pinCode", getPinCode());
            tryAuthenticateCard();
            break;
        case 3:
            pinCodeTb.setText("");
        }
    }

    private void tryAuthenticateCard() {
        try {
            if (getServer().isAuthenticate(getOperationList())) {
                setState(getStateManager().getState("ChoiceOperation"));
            } else {
                if (attemptsCounter < 2) {
                    // TODO Show dialog Incorrect pin
                    this.pinCodeTb.setText("");
                    getOperationList().removeOperation("pinCode");
                    attemptsCounter++;
                } else {
                    // TODO Show dialog incorrect pin three times
                    attemptsCounter = 0;
                    getOperationList().clearList();
                    setState(getStateManager().getState("EnterCardNumber"));
                }
            }
        } catch (RemoteException e) {
            setState(getStateManager().getState("NoConnection"));
        }

    }

    private String getPinCode() {
        return this.pinCodeTb.getText();
    }

}
