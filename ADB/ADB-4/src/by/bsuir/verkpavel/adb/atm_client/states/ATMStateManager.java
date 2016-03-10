package by.bsuir.verkpavel.adb.atm_client.states;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import by.bsuir.verkpavel.adb.atm_client.states.concrete.CashWithdrawalATMState;
import by.bsuir.verkpavel.adb.atm_client.states.concrete.ChoiceOperationATMState;
import by.bsuir.verkpavel.adb.atm_client.states.concrete.NotConnectionATMState;
import by.bsuir.verkpavel.adb.atm_client.states.concrete.PrintCheckQueryATMState;
import by.bsuir.verkpavel.adb.atm_client.states.concrete.authentication.EnterCardNumberATMState;
import by.bsuir.verkpavel.adb.atm_client.states.concrete.authentication.EnterPinCodeATMState;
import by.bsuir.verkpavel.adb.atm_client.states.concrete.balance.BalanceInfoATMState;
import by.bsuir.verkpavel.adb.atm_client.states.concrete.balance.ViewBalancesATMState;
import by.bsuir.verkpavel.adb.atm_client.states.concrete.payments.PaymentsATMState;
import by.bsuir.verkpavel.adb.atm_client.states.concrete.payments.PaymentsApplyATMStates;
import by.bsuir.verkpavel.adb.atm_client.states.concrete.payments.PaymentsDetailsATMState;
import by.bsuir.verkpavel.adb.shared.IRemoteBank;


public class ATMStateManager {
    private Map<States,BaseATMState> states;
    
    public ATMStateManager(JPanel mainATMPanel, IRemoteBank server, Stateble stateble){
        states =  new HashMap<States,BaseATMState>();
        states.put(States.NotConnectionATMState, new NotConnectionATMState(mainATMPanel, server, stateble, this));
        states.put(States.EnterCardNumberATMState, new EnterCardNumberATMState(mainATMPanel,server, stateble, this));
        states.put(States.EnterPinCodeATMState, new EnterPinCodeATMState(mainATMPanel, server, stateble, this));
        states.put(States.ChoiceOperationATMState, new ChoiceOperationATMState(mainATMPanel, server, stateble, this));
        states.put(States.CashWithdrawalATMState, new CashWithdrawalATMState(mainATMPanel, server, stateble, this));
        states.put(States.ViewBalancesATMState, new ViewBalancesATMState(mainATMPanel, server, stateble, this));
        states.put(States.PaymentsApplyATMStates,  new PaymentsApplyATMStates(mainATMPanel, server, stateble, this));
        states.put(States.PaymentsATMState, new PaymentsATMState(mainATMPanel, server, stateble, this));
        states.put(States.PaymentsDetailsATMState, new PaymentsDetailsATMState(mainATMPanel, server, stateble, this));
        states.put(States.BalanceInfoATMState, new BalanceInfoATMState(mainATMPanel, server, stateble, this));
        states.put(States.PrintCheckQueryATMState, new PrintCheckQueryATMState(mainATMPanel, server, stateble, this));
    }
    
    public BaseATMState getState(States state){
        return states.get(state);
    }
}
