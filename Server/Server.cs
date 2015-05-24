using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Remoting.Channels;
using System.Text;
using System.Net;
using System.Net.Sockets;
using System.Threading;
 
namespace Server
{
	class Program
	{
		private const Int32 serverPort = 1990;
		private const string serverIP = "127.0.0.1";
		
		private static Dictionary<int,ServerSideClient> onlineUsers = new Dictionary<int, ServerSideClient>();
		
		private static Timer onlineUserCheckTimer = new Timer(onlineUserCheck);
		
		public static ManualResetEvent tcpClientConnected = new ManualResetEvent(false);
        
		private static void DoBeginAcceptTcpClient(TcpListener listener)
		{
			while (true) {
				tcpClientConnected.Reset();
				listener.BeginAcceptTcpClient(new AsyncCallback(DoAcceptTcpClientCallback), listener);
				tcpClientConnected.WaitOne();
			}
		}
 
		public static void DoAcceptTcpClientCallback(IAsyncResult ar)
		{
			TcpListener listener = (TcpListener)ar.AsyncState;
			TcpClient client = listener.EndAcceptTcpClient(ar);
			
			string ip = ((IPEndPoint)(client.Client.RemoteEndPoint)).Address.ToString();
			int port = ((IPEndPoint)(client.Client.RemoteEndPoint)).Port;

			Console.WriteLine("User connected " + ip + ":" + port);
 
			tcpClientConnected.Set();

			Byte[] bytes = new Byte[256];
			String data = null;

			NetworkStream stream = client.GetStream();
 
			int i;
			while ((i = stream.Read(bytes, 0, bytes.Length)) != 0)
			{
				data = Encoding.GetEncoding(1251).GetString(bytes, 0, i);

				byte[] msg = Encoding.GetEncoding(1251).GetBytes(Controller(data, client));
 
				// Отправляем данные обратно клиенту (ответ).
				stream.Write(msg, 0, msg.Length);
			}
		}
		
		public static void Main(string[] args)
		{
			TcpListener server = null;
			try
			{
				IPAddress localAddr = IPAddress.Parse(serverIP);
				server = new TcpListener(localAddr, serverPort);
				server.Start();
				DoBeginAcceptTcpClient(server);             
				Console.WriteLine("\nНажмите клавишу для продолжения...");
				Console.ReadKey();
			}
			finally
			{
				server.Stop();
			}
		}
		
		private static string Controller(string userRequest, TcpClient client)
		{
			string request = userRequest.Split(' ')[0];
			string parameters = userRequest.Split(' ')[1];
			
			switch(request)
			{
				case "registered":
					string[] registrateParameters = parameters.Split(':');
					
					onlineUsers[/*ip*/1] = new ServerSideClient(registrateParameters[0], Int32.Parse(registrateParameters[1]),
					                                            ((IPEndPoint)(client.Client.RemoteEndPoint)).Address.ToString(), ((IPEndPoint)(client.Client.RemoteEndPoint)).Port);
			
					//TODO Refactor
					return "You successfully registered";
				case "client allive":
					onlineUsers[].isAlive = true;
				case "sqr":
					return (Int32.Parse(parameters)*Int32.Parse(parameters)).ToString();
				default:
					return "Incorrect command";
			}
		}

		private static void onlineUserCheck(object state)
		{
			List<int> keysToDelete;
			foreach(ServerSideClient client in onlineUsers.Values)
			{
				if(client.IsAlive = false)
				{
					
				}
			}
		}
	}
}