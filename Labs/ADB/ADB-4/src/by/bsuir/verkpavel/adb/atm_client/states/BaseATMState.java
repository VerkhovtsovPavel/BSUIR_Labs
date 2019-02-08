package by.bsuir.verkpavel.adb.atm_client.states;

import java.awt.Component;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import by.bsuir.verkpavel.adb.shared.IRemoteBank;
import by.bsuir.verkpavel.adb.shared.OperationList;

public abstract class BaseATMState {
    private JPanel _atmPanel;
    private IRemoteBank _server;
    private ATMStateManager _stateManager;
    private Stateble _statable;
    private static OperationList _operationList = new OperationList();
   
    public BaseATMState(JPanel atmPanel, IRemoteBank server, Stateble stateble, ATMStateManager stateManager){
        this._atmPanel = atmPanel;
        this._server = server;
        this._stateManager = stateManager;
        this._statable = stateble;
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
    
    public void setState(States newState){
        _statable.switchState(_stateManager.getState(newState));
    }
   
    public OperationList getOperationList() {
        return _operationList;
    }
    
    protected void destroySession(){
        JOptionPane.showMessageDialog(null, "Заберите вашу карточку", "Error", JOptionPane.PLAIN_MESSAGE);
        _operationList.clearList();
        _statable.switchState(_stateManager.getState(States.EnterCardNumberATMState));
    }

}