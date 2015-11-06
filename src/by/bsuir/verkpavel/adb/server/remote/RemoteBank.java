package by.bsuir.verkpavel.adb.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
//TODO Implement interface methods
public class RemoteBank extends UnicastRemoteObject implements IRemoteBank{
    private static final long serialVersionUID = 4330392012601578065L;

    public RemoteBank() throws RemoteException {}

}
