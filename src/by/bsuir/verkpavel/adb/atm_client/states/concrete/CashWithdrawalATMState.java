package by.bsuir.verkpavel.adb.atm_client.states.concrete;

import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

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

    public CashWithdrawalATMState(JPanel atmPanel, IRemoteBank server, Stateble stateble, ATMStateManager stateManager) {
        super(atmPanel, server, stateble, stateManager);

        sumLb = new JLabel("Сумма");
        sumLb.setBounds(157, 290, 128, 14);

        sumTf = new JFormattedTextField();
        sumTf.setColumns(10);
        sumTf.setBounds(157, 313, 171, 20);

        endWork = new JLabel("Завершение работы");
        endWork.setBounds(131, 472, 128, 14);
        
        setMoneyFormat();
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
            getOperationList().addOperation(OperationType.OperationSum, getSum());
            try {
                if (getServer().executeWithdrawal(getOperationList())) {
                    JOptionPane.showMessageDialog(null, "Заберите деньги", "Info", JOptionPane.PLAIN_MESSAGE);
                    setState(States.PrintCheckQueryATMState);
                } else {
                    JOptionPane.showMessageDialog(null, "Недостаточно средств на счете", "Error",
                            JOptionPane.PLAIN_MESSAGE);
                    setState(States.ChoiceOperationATMState);
                }
            } catch (RemoteException e) {
                setState(States.NotConnectionATMState);
            }
            break;
        }

    }

    private long getSum() {
        return Long.valueOf(sumTf.getText());
    }

    private void setMoneyFormat() {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("be-BY"));

        format.setMaximumFractionDigits(0);
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setMinimum(1.0);
        formatter.setMaximum(10000000.0);
        formatter.setAllowsInvalid(false);
        formatter.setOverwriteMode(true);
        sumTf.setFormatterFactory(new DefaultFormatterFactory(formatter));
    }

}
