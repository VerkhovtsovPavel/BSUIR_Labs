package by.bsuir.verkpavel.adb.atm_client.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import by.bsuir.verkpavel.adb.atm_client.states.ATMStateManager;
import by.bsuir.verkpavel.adb.atm_client.states.BaseATMState;
import by.bsuir.verkpavel.adb.atm_client.states.Stateble;
import by.bsuir.verkpavel.adb.shared.IRemoteBank;

//TODO Beatify UI 
public class ATMScreen extends JFrame implements Stateble{
    private static final long serialVersionUID = 2299108317475457681L;

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
        //TODO Think how remove
        currentState = new ATMStateManager(mainPanel, server, this).getState(firstState);
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
        functionnalButton1.addMouseListener(new ATMHardButtonListener());
        functionnalButton1.setMnemonic(1);
        mainPanel.add(functionnalButton1);

        JButton functionnalButton2 = new JButton("");
        functionnalButton2.setBounds(375, 306, 111, 23);
        functionnalButton2.addMouseListener(new ATMHardButtonListener());
        functionnalButton2.setMnemonic(2);
        mainPanel.add(functionnalButton2);

        JButton functionnalButton3 = new JButton("");
        functionnalButton3.setBounds(375, 352, 111, 23);
        functionnalButton3.addMouseListener(new ATMHardButtonListener());
        functionnalButton3.setMnemonic(3);
        mainPanel.add(functionnalButton3);

        JButton functionnalButton4 = new JButton("");
        functionnalButton4.setMnemonic(4);
        functionnalButton4.setBounds(10, 352, 111, 23);
        functionnalButton4.addMouseListener(new ATMHardButtonListener());
        mainPanel.add(functionnalButton4);
    }

    public void switchState(BaseATMState newState) {
        currentState.off();
        currentState = newState;
        currentState.on();
    }
    
    private class ATMHardButtonListener extends MouseAdapter{
            @Override
            public void mouseClicked(MouseEvent e) {
               int buttonNumber = ((JButton)e.getComponent()).getMnemonic();
               System.out.println(buttonNumber);
               currentState.processHardButton(buttonNumber);
            }
    }
}
