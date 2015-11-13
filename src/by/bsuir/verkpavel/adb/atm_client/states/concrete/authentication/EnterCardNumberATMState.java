package by.bsuir.verkpavel.adb.atm_client.states.concrete.authentication;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import by.bsuir.verkpavel.adb.atm_client.resources.ProjectProperties;
import by.bsuir.verkpavel.adb.atm_client.states.ATMStateManager;
import by.bsuir.verkpavel.adb.atm_client.states.BaseATMState;
import by.bsuir.verkpavel.adb.atm_client.states.Stateble;
import by.bsuir.verkpavel.adb.atm_client.states.States;
import by.bsuir.verkpavel.adb.shared.IRemoteBank;
import by.bsuir.verkpavel.adb.shared.OperationType;

public class EnterCardNumberATMState extends BaseATMState {

    private JTextField cardNumberTb;
    private JLabel cardNumberLb;
    private JLabel apply;

    public EnterCardNumberATMState(JPanel atmPanel, IRemoteBank server, Stateble stateble, ATMStateManager stateManager) {
        super(atmPanel, server, stateble, stateManager);

        
        cardNumberLb = new JLabel("Номер карты");
        cardNumberLb.setBounds(156, 234, 100, 14);

        cardNumberTb = new JFormattedTextField(ProjectProperties.getCardNumberFormatter());
        cardNumberTb.setBounds(156, 259, 165, 20);
        cardNumberTb.setColumns(10);

        apply = new JLabel("Подтветдить");
        apply.setBounds(286, 472, 118, 14);
    }

    @Override
    public void on() {
        addComponent(cardNumberTb);
        addComponent(cardNumberLb);
        addComponent(apply);
    }

    @Override
    public void off() {
        removeComponent(cardNumberLb);
        removeComponent(cardNumberTb);
        removeComponent(apply);
        cardNumberTb.setText("");
    }

    @Override
    public void processHardButton(int buttonNumber) {
        switch (buttonNumber) {
        case 4:
            if (getCardNumber().replaceAll(" ", "").length() == 16) {
                getOperationList().addOperation(OperationType.CardNumber, getCardNumber());
                setState(States.EnterPinCodeATMState);
            }else{
                JOptionPane.showMessageDialog(null, "Неверный номер карты", "Error", JOptionPane.PLAIN_MESSAGE);
            }
            break;
        case 3:
            this.cardNumberTb.setText("");
            break;
        }

    }

    private String getCardNumber() {
        return this.cardNumberTb.getText();
    }

}
