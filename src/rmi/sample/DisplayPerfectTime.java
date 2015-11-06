package rmi.sample;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class DisplayPerfectTime {
    public static void main(String[] args) throws Exception {

        Registry registry = LocateRegistry.getRegistry(null, 12345);
        PerfectTimeI t = (PerfectTimeI) registry.lookup("PerfectTime");
        for (int i = 0; i < 10; i++)
            System.out.println("Perfect time = " + t.getPerfectTime());
    }
}