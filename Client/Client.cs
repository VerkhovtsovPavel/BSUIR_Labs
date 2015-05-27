using System;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Net.Sockets;
using System.Threading;


//TODO Username with space
//TODO Server die
namespace Client
{
    class Program
    {
    	private static string userName;
    	private static int age;
    	
		private static string userID;
    	
        public static bool isClientRunning;
        
		private static Timer sessionUpdareTimer = new Timer(sessionUpdate);
		private const int sessionUpdarePeriod = 50000;
        
        private static NetworkStream serverStream;
        private static NetworkStream companionStream;

		static string[] onlineClients;
		
        private static void Main(string[] args)
        {
			EnterRegistrationInfo();
        	Connect("127.0.0.1", 1990);
			sessionUpdareTimer.Change(sessionUpdarePeriod, sessionUpdarePeriod);
        	String message = "registered~"+userName+":"+age;
        	SendMessage(serverStream, message);
        	userID = ReceiveMessage(serverStream);
			GetOnlineClient();
			SelectCompanion();
        	
            Console.WriteLine("\n Press Enter to continue...");
            Console.ReadKey();
            serverStream.Close();
        }
        
        private static void EnterRegistrationInfo()
        {
        	Console.Write("Enter user name> ");
        	userName = Console.ReadLine();
        	Console.Write("Enter your age> ");
        	age = Int32.Parse(Console.ReadLine());
        }
 
        private static void Connect(String serverIP, int serverPort)
        {
            try
            {
                TcpClient client = new TcpClient(serverIP, serverPort);
                Console.WriteLine("["+DateTime.Now.ToString("HH:mm:ss") +"] Successfully connected to server");
                serverStream = client.GetStream();
            }
            catch (ArgumentNullException e)
            {
                Console.WriteLine("ArgumentNullException: {0}", e);
            }
            catch (SocketException e)
            {
                Console.WriteLine("SocketException: {0}", e);
            }
        }
        
        static void SendMessage(NetworkStream stream, String message)
        {
        	Byte[] data = Encoding.GetEncoding(1251).GetBytes(message);
            stream.Write(data, 0, data.Length);
            Console.WriteLine("Send: {0}", message); //TODO Debug method
        }
        
        static string ReceiveMessage(NetworkStream stream){
        	Byte[] data = new Byte[256];
            String responseData = String.Empty;
            Int32 bytes = stream.Read(data, 0, data.Length);
            responseData = Encoding.GetEncoding(1251).GetString(data, 0, bytes);
			string userPart = responseData.Split(':')[0];
			responseData = responseData.Replace(userPart + ":", String.Empty);
            Console.WriteLine("["+DateTime.Now.ToString("HH:mm:ss") +"] " + userPart);
            
			return responseData;
       }

		static void sessionUpdate(object state)
		{
			string message = "clientAlive~" + userID;
			SendMessage(serverStream, message);
		}

		static void GetOnlineClient()
		{
			string message = "getOnlineClients~"+userID;
			SendMessage(serverStream, message);
			onlineClients = ReceiveMessage(serverStream).Split(';');
			Console.WriteLine("Online clients:");
			for (int i = 0; i < onlineClients.Length-1; i++)
			{
				string[] clientData = onlineClients[i].Split(':');
				Console.WriteLine("#" + (i+1)+" Username: "+clientData[0]+" Age: "+clientData[1]);
			}
		}

		static void SelectCompanion()
		{
			int selectedUser = Int32.Parse(Console.ReadLine());
			
			//CHECK onlineClients.Length+1
			if (selectedUser > 0 && selectedUser < onlineClients.Length + 1)
			{
				string[] clientData = onlineClients[selectedUser].Split(':');
				TcpClient client = new TcpClient(clientData[2], Int32.Parse(clientData[3]));
                Console.WriteLine("["+DateTime.Now.ToString("HH:mm:ss") +"] Successfully connected to companion");
                companionStream = client.GetStream();
                //CHECK
				TcpListener server = new TcpListener(((IPEndPoint)(client.Client.RemoteEndPoint)).Address, ((IPEndPoint)(client.Client.RemoteEndPoint)).Port);
				server.Start();
				DoBeginAcceptTcpClient(server);   
			}
		}
		
		
		
		
		
		//Server part
		
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
 
			tcpClientConnected.Set();

			Byte[] bytes = new Byte[256];
			String data = null;

			NetworkStream stream = client.GetStream();
 
			int i;
			try {
				while ((i = stream.Read(bytes, 0, bytes.Length)) != 0) {
					data = Encoding.GetEncoding(1251).GetString(bytes, 0, i);
					Controller(stream, data);
				}
			} catch (IOException) {
				Console.WriteLine("Error while receve or send message");
			}
		}

		private static void Controller(NetworkStream stream, string data)
		{
			throw new NotImplementedException();
		}
    }
}