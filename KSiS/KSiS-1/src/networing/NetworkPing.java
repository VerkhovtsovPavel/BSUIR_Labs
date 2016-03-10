package networing;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;

import sun.security.util.BitArray;



public class NetworkPing {

    public static void main(String[] args) throws IOException {
        InetAddress localhost = InetAddress.getLocalHost();
        NetworkInterface network = NetworkInterface.getByInetAddress(localhost);
        short mask = network.getInterfaceAddresses().get(0).getNetworkPrefixLength();
        byte[] ip = localhost.getAddress();

        int countOfNodes = 1 << (32 - mask);
        int[] networkAddress = getNetworkAddress(ip, mask);
        for (int i = 0; i < ip.length; i++) {
            ip[i] = (byte) networkAddress[i];
        }
        for (int i = 1; i <= countOfNodes - 2; i++) {
            incrementIp(ip);
            InetAddress address = InetAddress.getByAddress(ip);
            if (address.isReachable(1000)) {
                System.out.println(address + " (online)");
            } else if (!address.getHostAddress().equals(address.getHostName())) {
                System.out.println(address + " (offline)");
            }
        }

    }

    private static int[] getNetworkAddress(byte[] ip, short mask) {
        int[] networkMask = new int[4];
        if(mask<=24)
        {
            networkMask[3] =0;
            
        }else{
            networkMask[3] = ip[3] && (2^(mask-24) << (32 -mask));
        }

    }

    private static void incrementIp(byte[] oldIp) {
        oldIp[3]++;
        if (oldIp[3] == 0) {
            oldIp[2]++;
            if (oldIp[3] == 0 && oldIp[2] == 0) {
                oldIp[1]++;
                if (oldIp[3] == 0 && oldIp[2] == 0 && oldIp[1] == 0) {
                    oldIp[0]++;
                }
            }
        }
    }
}
