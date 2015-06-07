using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Net;
using System.Net.Sockets;
using System.Threading;

//TODO Client die

namespace Server
{
	class Program
	{
		private const int serverPort = 1990;
		private const string serverIP = "127.0.0.1";
		private const int sessionTimeOut = 10000;
		
		private static readonly Dictionary<string,ServerSideClient> onlineUsers = new Dictionary<string, ServerSideClient>();
		private static readonly Dictionary<string, NetworkStream> userStreams = new Dictionary<string, NetworkStream>();
		
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
			
			string streamID = ip+":"+port;
			if(!userStreams.ContainsKey(streamID)){
				userStreams[streamID]=stream;
			}
			
			int i;
			try {
				while ((i = stream.Read(bytes, 0, bytes.Length)) != 0) {
					data = Encoding.GetEncoding(1251).GetString(bytes, 0, i);
					Console.WriteLine("Recieve: " + data);

					string message = Controller(data, client);
					if (message != String.Empty) {
						Console.WriteLine("Send:" + message);
						byte[] msg = Encoding.GetEncoding(1251).GetBytes(message);
						
						stream.Write(msg, 0, msg.Length);
					}
				}
			} catch (IOException) {
				string userAddress = ((IPEndPoint)(client.Client.RemoteEndPoint)).Address.ToString();
				int userPort = ((IPEndPoint)(client.Client.RemoteEndPoint)).Port;
				Console.WriteLine("Error while work with client {0}:{1}", userAddress, userPort);
			}
		}
		
		public static void Main(string[] args)
		{
			TcpListener server = null;
			try {
				IPAddress localAddr = IPAddress.Parse(serverIP);
				server = new TcpListener(localAddr, serverPort);
				server.Start();
				onlineUserCheckTimer.Change(sessionTimeOut, sessionTimeOut);
				DoBeginAcceptTcpClient(server);
				Console.WriteLine("\nНажмите клавишу для продолжения...");
				Console.ReadKey();
			} finally {
				server.Stop();
			}
		}
		
		private static string Controller(string userRequest, TcpClient client)
		{
			string request = userRequest.Split(':')[1];
			string parameters = userRequest.Split('~')[1];
			//TODO Move all case to separated methods
			switch (request) {
				case "Registered":
					string[] registrateParameters = parameters.Split(':');
					string userName = registrateParameters[0];
					int age = Int32.Parse(registrateParameters[1]);
					string userAddress = ((IPEndPoint)(client.Client.RemoteEndPoint)).Address.ToString();
					int userPort = ((IPEndPoint)(client.Client.RemoteEndPoint)).Port;
					ServerSideClient serverClient =	new ServerSideClient(userName, age, userAddress, userPort);
					string userID = CreateUserID(serverClient);
					onlineUsers[userID] = serverClient;
					return "Server:You successfully registered:~" + userID;
				case "ClientAlive":
					string userIDString = parameters;
					onlineUsers[userIDString].IsAlive = true;
					return String.Empty;
				case "GetOnlineClients":
					return CreateOnlineClientsString(parameters);
				case "HandShake":
					string[] partnerAddress = parameters.Split(':');
					//Maybe move top
					string senderAddress = ((IPEndPoint)(client.Client.RemoteEndPoint)).Address.ToString();
					int senderPort = ((IPEndPoint)(client.Client.RemoteEndPoint)).Port;
					string message = "Server:HandShake:~" + senderAddress + ":" + senderPort;
					Console.WriteLine("Send:" + message);
					byte[] msg = Encoding.GetEncoding(1251).GetBytes(message);	
					userStreams[parameters].Write(msg, 0, msg.Length);
					return String.Empty;
				default:
					return "Incorrect command";
			}
		}

		private static void onlineUserCheck(object state)
		{
			lock (onlineUsers) {
				List<String> keysToDelete = new List<string>();
				foreach (String clientID in onlineUsers.Keys) {
					if (!onlineUsers[clientID].IsAlive) {
						keysToDelete.Add(clientID);
						userStreams.Remove(onlineUsers[clientID].Address+":"+onlineUsers[clientID].Port);
					}
					onlineUsers[clientID].IsAlive = false;
				}
				foreach (String key in keysToDelete) {
					onlineUsers.Remove(key);
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
			lock (onlineUsers) {
				string result = "Server:Online cliens:~";
				foreach (String clientID in onlineUsers.Keys) {
					if (userID != clientID) {
						result += onlineUsers[clientID].UserName + ":" + onlineUsers[clientID].Age + ":" + onlineUsers[clientID].Address + ":" + onlineUsers[clientID].Port + ";";
					}
				}
				return result;
			}
		}
	}
}