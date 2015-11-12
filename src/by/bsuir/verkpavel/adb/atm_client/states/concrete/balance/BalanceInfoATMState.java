package by.bsuir.verkpavel.adb.atm_client.states.concrete.balance;

import java.rmi.RemoteException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import by.bsuir.verkpavel.adb.atm_client.states.ATMStateManager;
import by.bsuir.verkpavel.adb.atm_client.states.BaseATMState;
import by.bsuir.verkpavel.adb.atm_client.states.Stateble;
import by.bsuir.verkpavel.adb.atm_client.states.States;
import by.bsuir.verkpavel.adb.shared.IRemoteBank;

//TODO Add button to print check
public class BalanceInfoATMState extends BaseATMState {

    private JLabel sumLb;
    private JTextField sumTf;

    public BalanceInfoATMState(JPanel atmPanel, IRemoteBank server, Stateble stateble, ATMStateManager stateManager) {
        super(atmPanel, server, stateble, stateManager);
        
        sumLb = new JLabel("Остаток на счете");
        sumLb.setBounds(157, 290, 128, 14);

        sumTf = new JTextField();
        sumTf.setColumns(10);
        sumTf.setBounds(157, 313, 171, 20);
    }

    @Override
    public void on() {
       addComponent(sumLb);
       addComponent(sumTf);
        fillFields();

    }

    private void fillFields() {
        try {
            double balance = getServer().getBalance(getOperationList());
            sumTf.setText(""+balance);
        } catch (RemoteException e) {
            setState(States.NotConnectionATMState);
        }

    }

    @Override
    public void off() {
        removeComponent(sumLb);
        removeComponent(sumTf);

    }

    @Override
    public void processHardButton(int buttonNumber) {
        // TODO Auto-generated method stub

    }

}
