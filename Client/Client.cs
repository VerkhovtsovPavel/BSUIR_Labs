using System;
using System.Linq;
using System.Text;
using System.Net.Sockets;
 
namespace Client
{
    class Program
    {
    	private static string userName;
    	private static int age;
    	
        public static bool isClientRunning;
        
        private static NetworkStream serverStream;
        private static NetworkStream companionStream;
        	
        private static void Main(string[] args)
        {
        	enterRegistrationInfo();
        	Connect("127.0.0.1", 1990);
        	
        	String message = "registrate "+userName+":"+age;
        	SendMessage(serverStream, message);
            while (true)
            {
            	
                
            }
            Console.WriteLine("\n Press Enter to continue...");
            Console.Read();
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
                Console.WriteLine("Соедение с сервером установлено!");
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
            Console.WriteLine("Отправлено: {0}", message); //TODO Debug method
        }
        
        static void ReceiveMessage(NetworkStream stream){
        	Byte[] data = new Byte[256];
            String responseData = String.Empty;
            Int32 bytes = stream.Read(data, 0, data.Length);
            responseData = Encoding.GetEncoding(1251).GetString(data, 0, bytes);
            Console.WriteLine("["+DateTime.Now.ToString("HH:mm:ss") +"] " + responseData);
       }
    }
}