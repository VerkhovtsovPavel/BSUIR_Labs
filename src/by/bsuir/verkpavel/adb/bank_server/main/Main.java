package by.bsuir.verkpavel.adb.bank_server.main;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import by.bsuir.verkpavel.adb.bank_server.remote.RemoteBank;
import by.bsuir.verkpavel.adb.bank_server.ui.MainView;

public class Main {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        RemoteBank rb = new RemoteBank();
        Registry registry = LocateRegistry.createRegistry(12345);
        registry.bind("RemoteBank", rb);
        System.out.println("Ready to do time");
        MainView.create();
    }
}
