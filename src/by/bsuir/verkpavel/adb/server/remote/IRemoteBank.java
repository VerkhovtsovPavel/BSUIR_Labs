package by.bsuir.verkpavel.adb.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
//TODO Add methods
//TODO Copy interface back to server
public interface IRemoteBank extends Remote{

    boolean isAuthenticate(String cardNumber, String pinCode) throws RemoteException;

}
