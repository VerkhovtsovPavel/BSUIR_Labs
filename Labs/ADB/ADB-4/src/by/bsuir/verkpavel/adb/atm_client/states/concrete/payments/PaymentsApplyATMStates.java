package by.bsuir.verkpavel.adb.atm_client.states.concrete.payments;

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

public class PaymentsApplyATMStates extends BaseATMState{

    private JLabel phoneNumberLb;
    private JTextField phoneNumberTf;
    private JLabel sumLb;
    private JTextField sumTf;
    
    private JLabel operatorLb;
    private JTextField operatorTf;
    private JLabel apply;
    private JLabel endWork;
    private JLabel back;

    public PaymentsApplyATMStates(JPanel atmPanel, IRemoteBank server, Stateble stateble, ATMStateManager stateManager) {
        super(atmPanel, server, stateble, stateManager);
        
        phoneNumberLb = new JLabel("Номер телефона");
        phoneNumberLb.setBounds(157, 236, 128, 14);

        phoneNumberTf = new JTextField();
        phoneNumberTf.setBounds(157, 259, 171, 20);
        phoneNumberTf.setColumns(10);

        sumLb = new JLabel("Сумма");
        sumLb.setBounds(157, 290, 128, 14);

        sumTf = new JTextField();
        sumTf.setColumns(10);
        sumTf.setBounds(157, 313, 171, 20);
        
        operatorLb = new JLabel("Оператор");
        operatorLb.setBounds(157, 180, 128, 14);
        
        operatorTf = new JTextField();
        operatorTf.setColumns(10);
        operatorTf.setBounds(157, 200, 171,20);
        
        apply = new JLabel("Подтвердить");
        apply.setBounds(276, 472, 118, 14);
        
        endWork = new JLabel("Выход");
        endWork.setBounds(131, 472, 128, 14);
        
        back = new JLabel("Назад");
        back.setBounds(286, 428, 101, 14);
    }

    @Override
    public void on() {
        addComponent(operatorLb);
        addComponent(operatorTf);
        addComponent(phoneNumberLb);
        addComponent(phoneNumberTf);
        addComponent(sumLb);
        addComponent(sumTf);
        addComponent(apply);
        addComponent(endWork);
        addComponent(back);
        fillFields();
    }

    private void fillFields() {    
        phoneNumberTf.setText((String) getOperationList().getOperation(OperationType.PhoneNumber));
        phoneNumberTf.setEnabled(false);
        
        sumTf.setText(String.format("%.2f", getOperationList().getOperation(OperationType.OperationSum)));
        sumTf.setEnabled(false);
        
        operatorTf.setText((String) getOperationList().getOperation(OperationType.Operator));
        operatorTf.setEnabled(false);  
    }

    @Override
    public void off() {
        removeComponent(operatorLb);
        removeComponent(operatorTf);
        removeComponent(phoneNumberLb);
        removeComponent(phoneNumberTf);
        removeComponent(sumLb);
        removeComponent(sumTf);
        removeComponent(apply);
        removeComponent(endWork);
        removeComponent(back);
    }

    @Override
    public void processHardButton(int buttonNumber) {
        switch (buttonNumber) {
        case 4:
            try {
                if(getServer().executePayments(getOperationList())){
                    JOptionPane.showMessageDialog(null, "Платеж успешно произведен", "Info", JOptionPane.PLAIN_MESSAGE);
                    Check check = new Check(getOperationList(), CheckTypes.PaymentsCheck);
                    check.generateCheck();
                    check.openCheck();
                }else{
                    JOptionPane.showMessageDialog(null, "Во время платежа произошла ошибка", "Error", JOptionPane.PLAIN_MESSAGE);
                }
                setState(States.ChoiceOperationATMState);
            } catch (RemoteException e) {
                setState(States.NotConnectionATMState);   
            }
            break;
        case 3:
            destroySession();
            break;
        case 2:
            getOperationList().removeOperation(OperationType.PhoneNumber);
            getOperationList().removeOperation(OperationType.OperationSum);  
            setState(States.PaymentsDetailsATMState);
        default:
            break;
        }
        
    }

}
