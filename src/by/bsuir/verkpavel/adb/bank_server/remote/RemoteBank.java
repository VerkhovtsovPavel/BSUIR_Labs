package by.bsuir.verkpavel.adb.bank_server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import by.bsuir.verkpavel.adb.shared.IRemoteBank;
import by.bsuir.verkpavel.adb.shared.OperationList;
//TODO Implement interface methods
public class RemoteBank extends UnicastRemoteObject implements IRemoteBank{
    private static final long serialVersionUID = 4330392012601578065L;

    public RemoteBank() throws RemoteException {}

    @Override
    public boolean isAuthenticate(OperationList operationList) {
        String cardNumber = operationList.getOperation("cardNumber");
        String pinCode = operationList.getOperation("pinCode");
 
        return cardNumber.equals(pinCode);
    }

}
