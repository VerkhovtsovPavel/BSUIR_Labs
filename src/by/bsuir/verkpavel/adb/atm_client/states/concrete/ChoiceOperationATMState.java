package by.bsuir.verkpavel.adb.atm_client.states.concrete;

import javax.swing.JPanel;

import by.bsuir.verkpavel.adb.atm_client.states.ATMStateManager;
import by.bsuir.verkpavel.adb.atm_client.states.BaseATMState;
import by.bsuir.verkpavel.adb.atm_client.states.Stateble;
import by.bsuir.verkpavel.adb.shared.IRemoteBank;

public class ChoiceOperationATMState extends BaseATMState {

    public ChoiceOperationATMState(JPanel atmPanel, IRemoteBank server, Stateble stateble, ATMStateManager stateManager) {
        super(atmPanel, server, stateble, stateManager);
        // TODO Add UI elements
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
        // TODO Auto-generated method stub

    }

}
