package by.bsuir.verkpavel.adb.atm_client.states.concrete;

import java.rmi.RemoteException;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.DefaultFormatterFactory;

import by.bsuir.verkpavel.adb.atm_client.resources.ProjectProperties;
import by.bsuir.verkpavel.adb.atm_client.states.ATMStateManager;
import by.bsuir.verkpavel.adb.atm_client.states.BaseATMState;
import by.bsuir.verkpavel.adb.atm_client.states.Stateble;
import by.bsuir.verkpavel.adb.atm_client.states.States;
import by.bsuir.verkpavel.adb.shared.IRemoteBank;
import by.bsuir.verkpavel.adb.shared.OperationType;

public class CashWithdrawalATMState extends BaseATMState {

    private JLabel endWork;
    private JLabel sumLb;
    private JFormattedTextField sumTf;
    private JLabel apply;

    public CashWithdrawalATMState(JPanel atmPanel, IRemoteBank server, Stateble stateble, ATMStateManager stateManager) {
        super(atmPanel, server, stateble, stateManager);

        sumLb = new JLabel("Сумма");
        sumLb.setBounds(157, 290, 128, 14);

        sumTf = new JFormattedTextField();
        sumTf.setColumns(10);
        sumTf.setBounds(157, 313, 171, 20);
        sumTf.setFormatterFactory(new DefaultFormatterFactory(ProjectProperties.getBYRFormatter()));
        sumTf.setValue(1);
        
        apply = new JLabel("Подтвердить");
        apply.setBounds(276, 472, 118, 14);

        endWork = new JLabel("Выход");
        endWork.setBounds(131, 472, 128, 14);
    }

    @Override
    public void on() {
        addComponent(endWork);
        addComponent(sumLb);
        addComponent(sumTf);
        addComponent(apply);
    }

    @Override
    public void off() {
        removeComponent(endWork);
        removeComponent(sumLb);
        removeComponent(sumTf);
        removeComponent(apply);
    }

    @Override
    public void processHardButton(int buttonNumber) {
        switch (buttonNumber) {
        case 3:
            destroySession();
            break;
        case 4:
            getOperationList().addOperation(OperationType.OperationSum, getSum());
            try {
                if (getServer().executeWithdrawal(getOperationList())) {
                    JOptionPane.showMessageDialog(null, "Заберите деньги", "Info", JOptionPane.PLAIN_MESSAGE);
                    setState(States.PrintCheckQueryATMState);
                } else {
                    JOptionPane.showMessageDialog(null, "Во время операции возникла ошибка", "Error",
                            JOptionPane.PLAIN_MESSAGE);
                    setState(States.ChoiceOperationATMState);
                }
            } catch (RemoteException e) {
                setState(States.NotConnectionATMState);
            }
            break;
        }

    }

    private double getSum() {
        return Double.valueOf(""+sumTf.getValue());
    }
}
