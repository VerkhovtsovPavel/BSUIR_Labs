using System;
using System.Linq;
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
        	enterRegistrationInfo();
        	Connect("127.0.0.1", 1990);
			sessionUpdareTimer.Change(sessionUpdarePeriod, sessionUpdarePeriod);
        	String message = "registered "+userName+":"+age;
        	SendMessage(serverStream, message);
        	userID = ReceiveMessage(serverStream);
			GetOnlineClient();
        	
        	
            Console.WriteLine("\n Press Enter to continue...");
            Console.ReadKey();
            serverStream.Close();
        }
        
        private static void enterRegistrationInfo()
        {
        	Console.Write("Enter user name> ");
        	userName = Console.ReadLine();
        	Console.Write("Enter your age> ");
        	age = Console.Read();
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
			string message = "clientAlive " + userID;
			SendMessage(serverStream, message);
		}

		static void GetOnlineClient()
		{
			const string message = "getOnlineClients";
			SendMessage(serverStream, message);
			onlineClients = ReceiveMessage(serverStream).Split(';');
			Console.WriteLine("Online clients:");
			for (int i = 0; i < onlineClients.Length; i++)
			{
				string[] clientData = onlineClients[i].Split(':');
				Console.WriteLine("#" + i);
				Console.WriteLine("Username: "+clientData[0]);
				Console.WriteLine("Age: "+clientData[1]);
			}
			
			
		}
    }
}