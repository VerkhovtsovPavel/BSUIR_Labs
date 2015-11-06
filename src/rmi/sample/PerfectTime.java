package rmi.sample;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class PerfectTime extends UnicastRemoteObject implements PerfectTimeI {
    private static final long serialVersionUID = 7962148736203237152L;

// Реализация интерфейса:
   public long getPerfectTime() throws RemoteException {
      return System.currentTimeMillis();
   }
  
   // Необходимо реализовывать конструктор,
   // выбрасывающий RemoteException:
   public PerfectTime() throws RemoteException {
      // super(); // Вызывается автоматически
   }
  
   // Регистрация для обслуживания RMI. Выбрасывает
   // исключения на консоль.
   public static void main(String[] args) throws Exception {
      PerfectTime pt = new PerfectTime();
      Registry registry = LocateRegistry.createRegistry(12345);
      registry.bind("PerfectTime", pt);
      System.out.println("Ready to do time");
   }
}