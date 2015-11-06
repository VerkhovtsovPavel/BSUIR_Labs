package by.bsuir.verkpavel.adb.atm.states;

import java.awt.Component;

import javax.swing.JPanel;

import by.bsuir.verkpavel.adb.atm.remote.IRemoteBank;

public abstract class BaseATMState {
    private JPanel _atmPanel;
    private IRemoteBank _server;
   
    public BaseATMState(JPanel atmPanel, IRemoteBank server){
        this._atmPanel = atmPanel;
        this._server = server;
    }
    
    public abstract void on();
    public abstract void off();
    public abstract void processHardButton(int buttonNumber);
    
    public void addComponent(Component component){
        _atmPanel.add(component);
    }
    
    public void removeComponent(Component component){
        _atmPanel.remove(component);
    }

    public IRemoteBank getServer() {
        return _server;
    }
}

//TODO Implements concrete states
//TODO Initialize add state specific control in static section on each state
//TODO Implement add(on) and remove(off) methods and call repaint in add(on)

