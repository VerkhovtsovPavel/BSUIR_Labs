package by.bsuir.verkpavel.adb.atm_client.states.concrete.balance;

import java.rmi.RemoteException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import by.bsuir.verkpavel.adb.atm_client.reports.Check;
import by.bsuir.verkpavel.adb.atm_client.reports.CheckTypes;
import by.bsuir.verkpavel.adb.atm_client.states.ATMStateManager;
import by.bsuir.verkpavel.adb.atm_client.states.BaseATMState;
import by.bsuir.verkpavel.adb.atm_client.states.Stateble;
import by.bsuir.verkpavel.adb.atm_client.states.States;
import by.bsuir.verkpavel.adb.shared.IRemoteBank;
import by.bsuir.verkpavel.adb.shared.OperationType;

public class BalanceInfoATMState extends BaseATMState {

    private JLabel sumLb;
    private JTextField sumTf;
    private JLabel endWork;
    private JLabel printCheck;
    private JLabel back;
    private double balance;

    public BalanceInfoATMState(JPanel atmPanel, IRemoteBank server, Stateble stateble, ATMStateManager stateManager) {
        super(atmPanel, server, stateble, stateManager);

        sumLb = new JLabel("Остаток на счете");
        sumLb.setBounds(157, 290, 128, 14);

        sumTf = new JTextField();
        sumTf.setColumns(10);
        sumTf.setBounds(157, 313, 171, 20);

        endWork = new JLabel("Завершение работы");
        endWork.setBounds(131, 472, 128, 14);

        printCheck = new JLabel("Распечатать");
        printCheck.setBounds(286, 428, 101, 14);

        back = new JLabel("Назад");
        back.setBounds(131, 428, 108, 14);
    }

    @Override
    public void on() {
        addComponent(sumLb);
        addComponent(sumTf);
        addComponent(endWork);
        addComponent(printCheck);
        addComponent(back);
        fillFields();

    }

    private void fillFields() {
        try {
            balance = getServer().getBalance(getOperationList());
            if (Double.isNaN(balance)) {
                JOptionPane.showMessageDialog(null, "С данной карточкой не связат депозитный счет", "Error",
                        JOptionPane.PLAIN_MESSAGE);
                setState(States.ViewBalancesATMState);
            } else {
                sumTf.setText(String.format("%.2f", balance));
            }
        } catch (RemoteException e) {
            setState(States.NotConnectionATMState);
        }

    }

    @Override
    public void off() {
        removeComponent(sumLb);
        removeComponent(sumTf);
        removeComponent(endWork);
        removeComponent(printCheck);
        removeComponent(back);
    }

    @Override
    public void processHardButton(int buttonNumber) {
        switch (buttonNumber) {
        case 1:
            setState(States.ChoiceOperationATMState);
            break;
        case 2:
            getOperationList().addOperation(OperationType.Balance, balance);
            Check check = new Check(getOperationList(), CheckTypes.BalanceCheck);
            check.generateCheck();
            check.openCheck();
            break;
        case 3:
            destroySession();
            break;
        }

    }

}
