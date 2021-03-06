package by.bsuir.verkpavel.adb.atm_client.states.concrete.payments;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;

import by.bsuir.verkpavel.adb.atm_client.resources.ProjectProperties;
import by.bsuir.verkpavel.adb.atm_client.states.ATMStateManager;
import by.bsuir.verkpavel.adb.atm_client.states.BaseATMState;
import by.bsuir.verkpavel.adb.atm_client.states.Stateble;
import by.bsuir.verkpavel.adb.atm_client.states.States;
import by.bsuir.verkpavel.adb.shared.IRemoteBank;
import by.bsuir.verkpavel.adb.shared.OperationType;

public class PaymentsDetailsATMState extends BaseATMState {

    private JLabel phoneNumberLb;
    private JLabel sumLb;
    private JTextField phoneNumberTf;
    private JFormattedTextField sumTf;
    private JLabel endWork;
    private JLabel apply;

    public PaymentsDetailsATMState(JPanel atmPanel, IRemoteBank server, Stateble stateble, ATMStateManager stateManager) {
        super(atmPanel, server, stateble, stateManager);

        phoneNumberLb = new JLabel("Номер телефона");
        phoneNumberLb.setBounds(157, 236, 128, 14);

        phoneNumberTf = new JFormattedTextField(ProjectProperties.getPhoneNumberFormatter());

        phoneNumberTf.setBounds(157, 259, 171, 20);
        phoneNumberTf.setColumns(10);

        sumLb = new JLabel("Сумма");
        sumLb.setBounds(157, 290, 128, 14);

        sumTf = new JFormattedTextField();
        sumTf.setColumns(10);
        sumTf.setBounds(157, 313, 171, 20);
        sumTf.setFormatterFactory(new DefaultFormatterFactory(ProjectProperties.getBYRFormatter()));
        sumTf.setValue(1);

        endWork = new JLabel("Выход");
        endWork.setBounds(131, 472, 128, 14);

        apply = new JLabel("Подтвердить");
        apply.setBounds(276, 472, 118, 14);
    }

    @Override
    public void on() {
        addComponent(phoneNumberLb);
        addComponent(phoneNumberTf);
        addComponent(sumLb);
        addComponent(sumTf);
        addComponent(endWork);
        addComponent(apply);
    }

    @Override
    public void off() {
        removeComponent(phoneNumberLb);
        removeComponent(phoneNumberTf);
        removeComponent(sumLb);
        removeComponent(sumTf);
        removeComponent(endWork);
        removeComponent(apply);
    }

    @Override
    public void processHardButton(int buttonNumber) {
        switch (buttonNumber) {
        case 4:
            if (getPhoneNumber().equals("+(   )-  -   -    ")) {
                JOptionPane.showMessageDialog(null, "Неверный номер телефона", "Error", JOptionPane.PLAIN_MESSAGE);
            } else {
                getOperationList().addOperation(OperationType.PhoneNumber, getPhoneNumber());
                getOperationList().addOperation(OperationType.OperationSum, getSum());
                setState(States.PaymentsApplyATMStates);
            }
            break;
        case 3:
            destroySession();
        }
    }

    private double getSum() {
        return Double.valueOf("" + sumTf.getValue());
    }

    private String getPhoneNumber() {
        return phoneNumberTf.getText();
    }

}
