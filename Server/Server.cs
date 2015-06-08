namespace Server
{
	using System;
	using System.Collections.Generic;
	using System.IO;
	using System.Linq;
	using System.Security.Cryptography;
	using System.Text;
	using System.Net;
	using System.Net.Sockets;
	using System.Threading;

	public class Program
	{
		private const int ServerPort = 1990;
		private const string ServerIP = "127.0.0.1";
		private const int SessionTimeOut = 10000;
		
		private static readonly Dictionary<string,ServerSideClient> OnlineUsers = new Dictionary<string, ServerSideClient>();
		private static readonly Dictionary<string, NetworkStream> UserStreams = new Dictionary<string, NetworkStream>();
		
		private static Timer onlineUserCheckTimer = new Timer(OnlineUserCheck);
		
		private static ManualResetEvent tcpClientConnected = new ManualResetEvent(false);
		
		public static void Main(string[] args)
		{
			TcpListener server = null;
			try
			{
				IPAddress localAddr = IPAddress.Parse(ServerIP);
				server = new TcpListener(localAddr, ServerPort);
				server.Start();
				onlineUserCheckTimer.Change(SessionTimeOut, SessionTimeOut);
				DoBeginAcceptTcpClient(server);
			} 
			catch (SocketException)
			{
				Console.WriteLine("Server already run or invalid address\nPress enter to exit");
				Console.ReadKey();
			}
			finally
			{
				server.Stop();
			}
		}
		
		public static void DoAcceptTcpClientCallback(IAsyncResult ar)
		{
			TcpListener listener = (TcpListener)ar.AsyncState;
			TcpClient client = listener.EndAcceptTcpClient(ar);
			
			string ip = ((IPEndPoint)client.Client.RemoteEndPoint).Address.ToString();
			int port = ((IPEndPoint)client.Client.RemoteEndPoint).Port;

			Console.WriteLine("User connected " + ip + ":" + port);
			
			tcpClientConnected.Set();

			byte[] bytes = new byte[256];
			string data = null;

			NetworkStream stream = client.GetStream();
			
			string streamID = ip + ":" + port;
			if (!UserStreams.ContainsKey(streamID))
			{
				UserStreams[streamID] = stream;
			}
			
			int i;
			try
			{
				while ((i = stream.Read(bytes, 0, bytes.Length)) != 0) 
				{
					data = Encoding.GetEncoding(1251).GetString(bytes, 0, i);
					Console.WriteLine("[" + DateTime.Now.ToString("HH:mm:ss") + "] Recieve: " + data);

					string message = Controller(data, client);
					if (message != string.Empty)
					{
						Console.WriteLine("[" + DateTime.Now.ToString("HH:mm:ss") + "] Send:" + message);
						byte[] msg = Encoding.GetEncoding(1251).GetBytes(message);
						
						stream.Write(msg, 0, msg.Length);
					}
				}
			}
			catch (IOException)
			{
				string userAddress = ((IPEndPoint)client.Client.RemoteEndPoint).Address.ToString();
				int userPort = ((IPEndPoint)client.Client.RemoteEndPoint).Port;
				Console.WriteLine("Error while work with client {0}:{1}", userAddress, userPort);
			}
		}
		
		private static void DoBeginAcceptTcpClient(TcpListener listener)
		{
			while (true) 
			{
				tcpClientConnected.Reset();
				listener.BeginAcceptTcpClient(new AsyncCallback(DoAcceptTcpClientCallback), listener);
				tcpClientConnected.WaitOne();
			}
		}
		
		private static string Controller(string userRequest, TcpClient client)
		{
			string request = userRequest.Split(':')[1];
			string parameters = userRequest.Split('~')[1];
			switch (request)
			{
				case "Registered":
					string[] registrateParameters = parameters.Split(':');
					string userName = registrateParameters[0];
					int age = int.Parse(registrateParameters[1]);
					string userAddress = ((IPEndPoint)client.Client.RemoteEndPoint).Address.ToString();
					int userPort = ((IPEndPoint)client.Client.RemoteEndPoint).Port;
					ServerSideClient serverClient =	new ServerSideClient(userName, age, userAddress, userPort);
					string userID = CreateUserID(serverClient);
					OnlineUsers[userID] = serverClient;
					return "Server:You successfully registered:~" + userID;
				case "ClientAlive":
					string userIDString = parameters;
					OnlineUsers[userIDString].IsAlive = true;
					return string.Empty;
				case "GetOnlineClients":
					return CreateOnlineClientsString(parameters);
				case "HandShake":
					string[] partnerAddress = parameters.Split(':');
					string senderAddress = ((IPEndPoint)client.Client.RemoteEndPoint).Address.ToString();
					int senderPort = ((IPEndPoint)client.Client.RemoteEndPoint).Port;
					string message = "Server:HandShake:~" + senderAddress + ":" + senderPort;
					Console.WriteLine("[" + DateTime.Now.ToString("HH:mm:ss") + "] Send:" + message);
					byte[] msg = Encoding.GetEncoding(1251).GetBytes(message);	
					UserStreams[parameters].Write(msg, 0, msg.Length);
					return string.Empty;
				default:
					return "Incorrect command";
			}
		}

		private static void OnlineUserCheck(object state)
		{
			lock (OnlineUsers)
			{
				List<string> keysToDelete = new List<string>();
				foreach (string clientID in OnlineUsers.Keys)
				{
					if (!OnlineUsers[clientID].IsAlive)
					{
						keysToDelete.Add(clientID);
						UserStreams.Remove(OnlineUsers[clientID].Address + ":" + OnlineUsers[clientID].Port);
					}
					
					OnlineUsers[clientID].IsAlive = false;
				}
				
				foreach (string key in keysToDelete)
				{
					OnlineUsers.Remove(key);
				}
			}
		}
		
		private static string CreateUserID(ServerSideClient client)
		{
			string input = client.UserName + client.Age.ToString() + DateTime.Now.ToString();
			byte[] data = Encoding.ASCII.GetBytes(input);
			data = MD5.Create().ComputeHash(data);
			return Convert.ToBase64String(data);
		}

		private static string CreateOnlineClientsString(string userID)
		{
			lock (OnlineUsers)
			{
				string result = "Server:Online cliens:~";
				foreach (string clientID in OnlineUsers.Keys)
				{
					if (userID != clientID)
					{
						result += OnlineUsers[clientID].UserName + ":" + OnlineUsers[clientID].Age + ":" + OnlineUsers[clientID].Address + ":" + OnlineUsers[clientID].Port + ";";
					}
				}
				
				return result;
			}
		}
	}
}