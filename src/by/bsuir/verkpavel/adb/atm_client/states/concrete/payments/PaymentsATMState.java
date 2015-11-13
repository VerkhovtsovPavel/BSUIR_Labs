package by.bsuir.verkpavel.adb.atm_client.states.concrete.payments;

import javax.swing.JLabel;
import javax.swing.JPanel;

import by.bsuir.verkpavel.adb.atm_client.states.ATMStateManager;
import by.bsuir.verkpavel.adb.atm_client.states.BaseATMState;
import by.bsuir.verkpavel.adb.atm_client.states.Stateble;
import by.bsuir.verkpavel.adb.atm_client.states.States;
import by.bsuir.verkpavel.adb.shared.IRemoteBank;
import by.bsuir.verkpavel.adb.shared.OperationType;

public class PaymentsATMState extends BaseATMState {
    
    private JLabel velcomPayments;
    private JLabel endWork;
    private JLabel mtsPayments;
    private JLabel lifePayment;

    public PaymentsATMState(JPanel atmPanel, IRemoteBank server, Stateble stateble, ATMStateManager stateManager) {
        super(atmPanel, server, stateble, stateManager);
        
       velcomPayments = new JLabel("Velcom");
       velcomPayments.setBounds(131, 428, 108, 14);
 
       endWork = new JLabel("Завершение работы");
       endWork.setBounds(131, 472, 128, 14);
             
       mtsPayments = new JLabel("MTC");
       mtsPayments.setBounds(286, 428, 81, 14);
            
       lifePayment = new JLabel("Life");
       lifePayment.setBounds(286, 472, 118, 14);
    }

    @Override
    public void on() {
        addComponent(velcomPayments);
        addComponent(endWork);
        addComponent(mtsPayments);
        addComponent(lifePayment);
    }

    @Override
    public void off() {
        removeComponent(velcomPayments);
        removeComponent(endWork);
        removeComponent(mtsPayments);
        removeComponent(lifePayment);
    }

    @Override
    public void processHardButton(int buttonNumber) {
        switch (buttonNumber) {
        case 1:
            getOperationList().addOperation(OperationType.Operator, "Velcome");
            setState(States.PaymentsDetailsATMState);
            break;
        case 2:
            getOperationList().addOperation(OperationType.Operator, "MTC");
            setState(States.PaymentsDetailsATMState);
            break;
        case 3:
            destroySession();
            break;
        case 4:
            getOperationList().addOperation(OperationType.Operator, "Life");
            setState(States.PaymentsDetailsATMState);
            break;    
        }

    }

}
