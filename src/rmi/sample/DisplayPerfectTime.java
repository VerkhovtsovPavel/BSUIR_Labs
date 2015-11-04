package rmi.sample;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class DisplayPerfectTime {
   public static void main(String[] args) throws Exception {
      //System.setSecurityManager(new RMISecurityManager());
       Registry registry = LocateRegistry.getRegistry(null, 12345);
      PerfectTimeI t = (PerfectTimeI)registry.lookup("PerfectTime");
              //(PerfectTimeI) Naming.lookup("//peppy:2005/PerfectTime");
      for (int i = 0; i < 10; i++)
         System.out.println("Perfect time = " + t.getPerfectTime());
   }
}