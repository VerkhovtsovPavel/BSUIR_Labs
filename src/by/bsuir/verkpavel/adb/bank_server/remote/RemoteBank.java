package by.bsuir.verkpavel.adb.bank_server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import by.bsuir.verkpavel.adb.shared.IRemoteBank;
//TODO Implement interface methods
public class RemoteBank extends UnicastRemoteObject implements IRemoteBank{
    private static final long serialVersionUID = 4330392012601578065L;

    public RemoteBank() throws RemoteException {}

    @Override
    public boolean isAuthenticate(String cardNumber, String pinCode) {
        // TODO Auto-generated method stub
        return false;
    }

}
