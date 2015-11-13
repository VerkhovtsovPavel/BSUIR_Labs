package by.bsuir.verkpavel.adb.atm_client.states.concrete.authentication;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import by.bsuir.verkpavel.adb.atm_client.states.ATMStateManager;
import by.bsuir.verkpavel.adb.atm_client.states.BaseATMState;
import by.bsuir.verkpavel.adb.atm_client.states.Stateble;
import by.bsuir.verkpavel.adb.atm_client.states.States;
import by.bsuir.verkpavel.adb.shared.IRemoteBank;
import by.bsuir.verkpavel.adb.shared.OperationType;

public class EnterPinCodeATMState extends BaseATMState {

    private JTextField pinCodeTb;
    private JLabel pinCodelb;
    private int attemptsCounter;
    private JLabel endWork;
    private JLabel apply;

    public EnterPinCodeATMState(JPanel atmPanel, IRemoteBank server, Stateble stateble, ATMStateManager stateManager) {
        super(atmPanel, server, stateble, stateManager);

        pinCodelb = new JLabel("Пин код");
        pinCodelb.setBounds(226, 234, 100, 14);

        pinCodeTb = new JPasswordField();
        pinCodeTb.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent event) {
                JPasswordField field = (JPasswordField) event.getSource();
                char[] password = field.getPassword();

                if (!"0123456789".contains("" + event.getKeyChar())) {
                    if (password.length == 0) {
                        field.setText("");
                    } else {
                        field.setText((getPinCode().substring(0, getPinCode().length() - 1)));
                    }
                }
                if (password.length > 4) {
                    field.setText((getPinCode().substring(0, 4)));
                }
            }
        });

        pinCodeTb.setColumns(4);
        pinCodeTb.setBounds(226, 259, 50, 20);

        endWork = new JLabel("Завершение работы");
        endWork.setBounds(131, 472, 128, 14);

        apply = new JLabel("Подтветдить");
        apply.setBounds(286, 472, 118, 14);
    }

    @Override
    public void on() {
        addComponent(pinCodeTb);
        addComponent(pinCodelb);
        addComponent(apply);
        addComponent(endWork);
    }

    @Override
    public void off() {
        removeComponent(pinCodeTb);
        removeComponent(pinCodelb);
        removeComponent(apply);
        removeComponent(endWork);
        pinCodeTb.setText("");
    }

    @Override
    public void processHardButton(int buttonNumber) {
        switch (buttonNumber) {
        case 4:
            getOperationList().addOperation(OperationType.PinCode, getPinCode());
            tryAuthenticateCard();
            break;
        case 1:
            pinCodeTb.setText("");
            break;
        case 3:
            destroySession();
            break;
        }
    }

    private void tryAuthenticateCard() {
        try {
            if (getServer().isAuthenticate(getOperationList())) {
                setState(States.ChoiceOperationATMState);
            } else {
                if (attemptsCounter < 2) {
                    JOptionPane.showMessageDialog(null, "Неверный пин код", "Error", JOptionPane.PLAIN_MESSAGE);
                    this.pinCodeTb.setText("");
                    attemptsCounter++;
                } else {
                    JOptionPane.showMessageDialog(null, "Неверный пин код введен три раза. Сессия завершена", "Error",
                            JOptionPane.PLAIN_MESSAGE);
                    attemptsCounter = 0;
                    destroySession();
                }
            }
        } catch (RemoteException e) {
            setState(States.NotConnectionATMState);
        }

    }

    private String getPinCode() {
        return this.pinCodeTb.getText();
    }

}
