package by.bsuir.verkpavel.adb.atm_client.states.concrete;

import javax.swing.JLabel;
import javax.swing.JPanel;

import by.bsuir.verkpavel.adb.atm_client.reports.Check;
import by.bsuir.verkpavel.adb.atm_client.reports.CheckTypes;
import by.bsuir.verkpavel.adb.atm_client.states.ATMStateManager;
import by.bsuir.verkpavel.adb.atm_client.states.BaseATMState;
import by.bsuir.verkpavel.adb.atm_client.states.Stateble;
import by.bsuir.verkpavel.adb.atm_client.states.States;
import by.bsuir.verkpavel.adb.shared.IRemoteBank;

public class PrintCheckQueryATMState extends BaseATMState {

    private JLabel endWork;
    private JLabel yes;
    private JLabel no;

    public PrintCheckQueryATMState(JPanel atmPanel, IRemoteBank server, Stateble stateble, ATMStateManager stateManager) {
        super(atmPanel, server, stateble, stateManager);
        
  
        endWork = new JLabel("Завершение работы");
        endWork.setBounds(131, 472, 128, 14);
              
        yes = new JLabel("Да");
        yes.setBounds(266, 428, 101, 14);
             
        no = new JLabel("Нет");
        no.setBounds(286, 472, 118, 14);
    }

    @Override
    public void on() {
        addComponent(endWork);
        addComponent(no);
        addComponent(yes);

    }

    @Override
    public void off() {
        removeComponent(endWork);
        removeComponent(no);
        removeComponent(yes);
    }

    @Override
    public void processHardButton(int buttonNumber) {
        switch (buttonNumber) {
        case 2:
            Check check = new Check(getOperationList(), CheckTypes.CashWithdrawalCheck);
            check.generateCheck();
            check.openCheck();
            setState(States.ChoiceOperationATMState);
        case 3:
            destroySession();
            break;

        case 4:
            setState(States.ChoiceOperationATMState);
            break;
        }

    }

}
