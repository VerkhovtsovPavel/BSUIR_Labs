package by.bsuir.verkpavel.adb.atm_client.states.concrete;

import javax.swing.JLabel;
import javax.swing.JPanel;

import by.bsuir.verkpavel.adb.atm_client.states.ATMStateManager;
import by.bsuir.verkpavel.adb.atm_client.states.BaseATMState;
import by.bsuir.verkpavel.adb.atm_client.states.Stateble;
import by.bsuir.verkpavel.adb.atm_client.states.States;
import by.bsuir.verkpavel.adb.shared.IRemoteBank;

public class ChoiceOperationATMState extends BaseATMState {
    private JLabel showBalance;
    private JLabel endWork;
    private JLabel withdrawal;
    private JLabel createPayment;

    public ChoiceOperationATMState(JPanel atmPanel, IRemoteBank server, Stateble stateble, ATMStateManager stateManager) {
        super(atmPanel, server, stateble, stateManager);
         
        showBalance = new JLabel("Баланс");
        showBalance.setBounds(131, 428, 108, 14);
  
        endWork = new JLabel("Выход");
        endWork.setBounds(131, 472, 128, 14);
              
        withdrawal = new JLabel("Снятие средств");
        withdrawal.setBounds(256, 428, 111, 14);
             
        createPayment = new JLabel("Платежи");
        createPayment.setBounds(286, 472, 118, 14);
    }

    @Override
    public void on() {
        addComponent(showBalance);
        addComponent(endWork);
        addComponent(withdrawal);
        addComponent(createPayment);
    }

    @Override
    public void off() {
        removeComponent(showBalance);
        removeComponent(endWork);
        removeComponent(withdrawal);
        removeComponent(createPayment);
    }

    @Override
    public void processHardButton(int buttonNumber) {
        switch (buttonNumber) {
        case 1:
            setState(States.ViewBalancesATMState);
            break;
        case 2:
            setState(States.CashWithdrawalATMState);
            break;
        case 3:
            destroySession();
            break;
        case 4:
            setState(States.PaymentsATMState);
            break;    
        }

    }

}
