package rmi.sample;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PerfectTimeI extends Remote {
   long getPerfectTime() throws RemoteException;
}