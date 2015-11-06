package by.bsuir.verkpavel.adb.atm.states;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import by.bsuir.verkpavel.adb.server.remote.IRemoteBank;

public class NotConnectionATMState extends BaseATMState {
    JLabel bigNoConnectionLabel;

    public NotConnectionATMState(JPanel atmPanel, IRemoteBank server, Stateble stateble, ATMStateManager stateManager) {
        super(atmPanel, server, stateble, stateManager);
        bigNoConnectionLabel =new JLabel("No connection");
        bigNoConnectionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bigNoConnectionLabel.setFont(new Font("Arial Black", Font.ITALIC, 26));
        bigNoConnectionLabel.setBackground(Color.WHITE);
        bigNoConnectionLabel.setBounds(133, 11, 236, 355);
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
