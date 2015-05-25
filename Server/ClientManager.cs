using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Threading;
using System.Net.Sockets;
using System.Net;
using System.IO;
 
namespace Networking
{
   public class ClientManager
    {
       public string IP { get; set; }
       public int Port { get; set; }
       private TcpClient Client;
       private NetworkStream NetworkStream;
       private delegate void ReceiveDataEventHandler(byte[] data);
       private event ReceiveDataEventHandler OnReceiveData;
 
 
       public ClientManager(string ip, int port)
       {
           IP = ip;
           Port = port;
           Client = new TcpClient();
       }
 
       public void Connect()
       {
           try
           {
               Client.Connect(new IPEndPoint(IPAddress.Parse(IP), Port));
               NetworkStream = Client.GetStream();
               Thread thrdReceive = new Thread(new ThreadStart(ReceiveServerData));
               thrdReceive.Start();
           }
           catch (Exception ex)
           {
               Console.WriteLine("Error : " + ex.ToString());
           }
       }
 
       private void ReceiveServerData()
       {
           try
           {
               int buffersize;
               byte[] databuffer;
 
               while (true)
               {
                   databuffer = new byte[1048];
                   buffersize = NetworkStream.Read(databuffer, 0 , databuffer.Length);
 
                   if(OnReceiveData != null) OnReceiveData(databuffer);
               }
           }
           catch (Exception ex)
           {
               Console.WriteLine("Error : " + ex.ToString());
           }
      }
   }
}