package by.bsuir.verkpavel.adb.atm_client.states.concrete.balance;

import javax.swing.JLabel;
import javax.swing.JPanel;

import by.bsuir.verkpavel.adb.atm_client.states.ATMStateManager;
import by.bsuir.verkpavel.adb.atm_client.states.BaseATMState;
import by.bsuir.verkpavel.adb.atm_client.states.Stateble;
import by.bsuir.verkpavel.adb.atm_client.states.States;
import by.bsuir.verkpavel.adb.shared.IRemoteBank;

public class ViewBalancesATMState extends BaseATMState {
    
    private JLabel endWork;
    private JLabel showCreditBalance;
    private JLabel showDebitBalance;

    public ViewBalancesATMState(JPanel atmPanel, IRemoteBank server, Stateble stateble, ATMStateManager stateManager) {
        super(atmPanel, server, stateble, stateManager);
        
        showCreditBalance = new JLabel("Кредитный счет");
        showCreditBalance.setBounds(131, 428, 108, 14);
  
        endWork = new JLabel("Завершение работы");
        endWork.setBounds(131, 472, 128, 14);
              
        showDebitBalance = new JLabel("Депозитный счет");
        showDebitBalance.setBounds(266, 428, 101, 14);
    }

    @Override
    public void on() {
        addComponent(endWork);
        addComponent(showCreditBalance);
        addComponent(showDebitBalance);

    }

    @Override
    public void off() {
       removeComponent(endWork);
       removeComponent(showCreditBalance);
       removeComponent(showDebitBalance);
    }

    @Override
    public void processHardButton(int buttonNumber) {
        switch (buttonNumber) {
        case 1:
            getOperationList().addOperation("accountType", "credit");
            setState(States.BalanceInfoATMState);
            break;
        case 2:
            getOperationList().addOperation("accountType", "debit");
            setState(States.BalanceInfoATMState);
            break;    
        case 3:
            destroySession();
            break;
        }
    }

}
