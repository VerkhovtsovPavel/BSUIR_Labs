using System;
using System.Linq;
using System.Net;
using System.Net.Sockets;

namespace Server
{
    public static class SocketFactory
    {
        public static Socket CreateTimeServerSocket()
        {
            return CreateListener("localhost", 37);
        }

        public static Socket CreateDayTimeServerSocket()
        {
            return CreateListener("localhost", 13);
        }

        private static Socket CreateListener(string ip, int port)
        {
            IPHostEntry ipHost = Dns.GetHostEntry(ip);
            IPAddress ipAddress = ipHost.AddressList[0];
            IPEndPoint ipEndPoint = new IPEndPoint(ipAddress, port);
            
            Socket socketListener = new Socket(ipAddress.AddressFamily, SocketType.Stream, ProtocolType.Tcp);
            
            socketListener.Bind(ipEndPoint);
            socketListener.Listen(10);
            
            return socketListener;
        }
    }
}
