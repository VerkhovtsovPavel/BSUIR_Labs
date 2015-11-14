package by.bsuir.verkpavel.adb.atm_client.states.concrete;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import by.bsuir.verkpavel.adb.atm_client.states.ATMStateManager;
import by.bsuir.verkpavel.adb.atm_client.states.BaseATMState;
import by.bsuir.verkpavel.adb.atm_client.states.Stateble;
import by.bsuir.verkpavel.adb.shared.IRemoteBank;

public class NotConnectionATMState extends BaseATMState {
    JLabel bigNoConnectionLabel;

    public NotConnectionATMState(JPanel atmPanel, IRemoteBank server, Stateble stateble, ATMStateManager stateManager) {
        super(atmPanel, server, stateble, stateManager);
        bigNoConnectionLabel =new JLabel("Нет соединения");
        bigNoConnectionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bigNoConnectionLabel.setFont(new Font("Arial Black", Font.ITALIC, 26));
        bigNoConnectionLabel.setBackground(Color.WHITE);
        bigNoConnectionLabel.setBounds(133, 31, 236, 355);
    }

    @Override
    public void on() {
        addComponent(bigNoConnectionLabel);
    }

    @Override
    public void off() {
        removeComponent(bigNoConnectionLabel);
    }

    @Override
    public void processHardButton(int buttonNumber) {
        // Ignore any user actions
    }

}
