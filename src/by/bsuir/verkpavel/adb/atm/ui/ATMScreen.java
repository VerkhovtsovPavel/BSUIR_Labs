package by.bsuir.verkpavel.adb.atm.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import by.bsuir.verkpavel.adb.atm.remote.IRemoteBank;
import by.bsuir.verkpavel.adb.atm.states.ATMStateManager;
import by.bsuir.verkpavel.adb.atm.states.BaseATMState;

public class ATMScreen extends JFrame {
    private static final long serialVersionUID = 2299108317475457681L;

    private ATMStateManager stateManager;
    private BaseATMState currentState;
    private JPanel mainPanel;

    private static void initialaze() {
        ATMScreen frame = new ATMScreen();
        frame.setSize(500, 500);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    public static void create() {
        initialaze();
    }

    protected ATMScreen() {
        configureDefaultLayot();
        initialazeStates();
        currentState.on();
    }

    private void initialazeStates() {
        String firstState;
        Registry registry;
        IRemoteBank server = null;
        try {
            registry = LocateRegistry.getRegistry(null, 12345);
            server = (IRemoteBank) registry.lookup("RemoteBank");
            firstState = "Authentication";
        } catch (RemoteException | NotBoundException e) {
            firstState = "NoConnection";
        }
        stateManager = new ATMStateManager(mainPanel, server);
        currentState = stateManager.getState(firstState);
        currentState.on();
    }

    private void configureDefaultLayot() {
        setResizable(false);
        setTitle("Банкомат");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        mainPanel = new JPanel();
        setContentPane(mainPanel);
        mainPanel.setLayout(null);

        JButton functionnalButton1 = new JButton("");
        functionnalButton1.setBounds(10, 306, 111, 23);
        mainPanel.add(functionnalButton1);

        JButton functionnalButton2 = new JButton("");
        functionnalButton2.setBounds(375, 306, 111, 23);
        mainPanel.add(functionnalButton2);

        JButton functionnalButton3 = new JButton("");
        functionnalButton3.setBounds(375, 352, 111, 23);
        mainPanel.add(functionnalButton3);

        JButton functionnalButton4 = new JButton("");
        functionnalButton4.setBounds(10, 352, 111, 23);
        mainPanel.add(functionnalButton4);
    }

    private void switchState(BaseATMState newState) {
        currentState.off();
        currentState = newState;
        currentState.on();
    }

}
