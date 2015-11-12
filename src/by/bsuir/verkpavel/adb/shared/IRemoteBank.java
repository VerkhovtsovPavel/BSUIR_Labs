package by.bsuir.verkpavel.adb.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
//TODO Add methods
//TODO Copy interface back to server
public interface IRemoteBank extends Remote{

    boolean isAuthenticate(OperationList operationList) throws RemoteException;

    boolean executePayments(OperationList operationList) throws RemoteException;

    boolean executeWithdrawal(OperationList operationList) throws RemoteException;

    double getBalance(OperationList operationList) throws RemoteException;

}
