package by.bsuir.verkpavel.adb.atm_client.states.concrete;

import java.rmi.RemoteException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import by.bsuir.verkpavel.adb.atm_client.states.ATMStateManager;
import by.bsuir.verkpavel.adb.atm_client.states.BaseATMState;
import by.bsuir.verkpavel.adb.atm_client.states.Stateble;
import by.bsuir.verkpavel.adb.shared.IRemoteBank;

//TODO Enable necessary sum show pop-up message
//TODO Disable necessary sum show pop-up message
//TODO Add money folmat
public class CashWithdrawalATMState extends BaseATMState {

    private JLabel endWork;
    private JLabel sumLb;
    private JTextField sumTf;

    public CashWithdrawalATMState(JPanel atmPanel, IRemoteBank server, Stateble stateble, ATMStateManager stateManager) {
        super(atmPanel, server, stateble, stateManager);
        
        sumLb = new JLabel("Сумма");
        sumLb.setBounds(157, 290, 128, 14);

        sumTf = new JTextField();
        sumTf.setColumns(10);
        sumTf.setBounds(157, 313, 171, 20);
        
        endWork = new JLabel("Завершение работы");
        endWork.setBounds(131, 472, 128, 14);
    }

    @Override
    public void on() {
        addComponent(endWork);
        addComponent(sumLb);
        addComponent(sumTf);
    }

    @Override
    public void off() {
        removeComponent(endWork);
        removeComponent(sumLb);
        removeComponent(sumTf);
    }

    @Override
    public void processHardButton(int buttonNumber) {
        switch (buttonNumber) {
        case 3:
            destroySession();
            break;
        case 4:
            getOperationList().addOperation("withdrawalSum", getSum());
            try {
                getServer().executeWithdrawal(getOperationList());
            } catch (RemoteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            break;    
        }

    }
    
    private long getSum() {
        return Long.valueOf(sumTf.getText());
    }

}
