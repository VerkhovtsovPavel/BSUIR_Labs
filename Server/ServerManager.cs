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
   public class ServerManager
    {
       public int Port { get; set; }
       private TcpListener Listener;
       private List<TcpClient> Clients;
       private List<NetworkStream> NetworkStream;
       private delegate void ReceiveDataEventHandler(int clientid, byte[] data);
       private delegate void ConnectEventHandler(int clientid);
       private event ReceiveDataEventHandler OnReceiveData;
       private event ConnectEventHandler OnClientConnect;
       
 
        public ServerManager(int port)
        {
            Port = port;
            Listener = new TcpListener(new IPEndPoint(IPAddress.Any, Port));
            Clients = new List<TcpClient>();
            NetworkStream = new List<NetworkStream>();
 
        }
 
        public void Start() //Start
        {
            Listener.Start();
            Listener.BeginAcceptTcpClient(new AsyncCallback(ClientConnect), null);
 
        }
 
 
        public void Stop() //Stop
        {
            for (int i = 0; i < Clients.Count; i++)
            {
                Clients[i].Close();
                NetworkStream[i].Close();
            }
            
                Listener.Stop();
            
        }
 
        public void SendDataToClient(int clientid, byte[] data)
        {
 
            int clientidx = clientid - 1;
            NetworkStream[clientidx].Write(data, 0, data.Length);
            NetworkStream[clientidx].Flush();
        
        }
 
       public void SendDataToAllClient(byte[] data)
       {
           for (int i = 1; i <= Clients.Count; i++)
           {
               SendDataToClient(i, data);
           }
       }
 
       public void SendToAllClientExcept(int clientid, byte[] data)
       {
           for (int i = 1; i <= Clients.Count; i++)
           {
               if(clientid != i) SendDataToClient(i, data);
           }
 
       }
 
       private void ClientConnect(IAsyncResult ar)
        {
            TcpClient client = Listener.EndAcceptTcpClient(ar);
            Clients.Add(client);
            NetworkStream.Add(client.GetStream());
 
            if (OnClientConnect != null) OnClientConnect(Clients.Count);
 
            Thread thrdReceive = new Thread(new ParameterizedThreadStart(ClientReceiveData));
            thrdReceive.Start(Clients.Count);
            Listener.BeginAcceptTcpClient(new AsyncCallback(ClientConnect), null);
 
        }
 
        private void ClientReceiveData(object client)
        {
            int clientid = (int)client;
            int clientidx = clientid - 1;
            int buffersize;
            byte[] databuffer;
 
            while (true)
            {
                databuffer = new byte[1048];
                buffersize = NetworkStream[clientidx].Read(databuffer, 0, databuffer.Length);
                Array.Resize(ref databuffer, buffersize);
                if (OnReceiveData != null) OnReceiveData(clientid, databuffer);
              }
          }
   
     }
 
}