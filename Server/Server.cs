using System;
using System.Collections.Concurrent;
using System.Linq;
using System.Net.Cache;
using System.Text;
using System.Net;
using System.Net.Sockets;
using System.Threading;
 
namespace Server
{
	class Program
	{
		
		//private static 
		
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
 
			// Process the connection here. (Add the client to a
			// server table, read data, etc.)
			Console.WriteLine("Соеденение установлено!");
 
			// Signal the calling thread to continue.
			tcpClientConnected.Set();
 
			//  Буфер для чтения данных
			Byte[] bytes = new Byte[256];
			String data = null;
 
			// Получаем информацию от клиента
			NetworkStream stream = client.GetStream();
 
			int i;
			// Принимаем данные от клиента в цикле пока не дойдём до конца.
			while ((i = stream.Read(bytes, 0, bytes.Length)) != 0)
			{
				// Преобразуем данные в Windows-1251 string.
				data = Encoding.GetEncoding(1251).GetString(bytes, 0, i);
 
				// Преобразуем полученную строку в массив Байт.
				byte[] msg = Encoding.GetEncoding(1251).GetBytes(Controller(data));
 
				// Отправляем данные обратно клиенту (ответ).
				stream.Write(msg, 0, msg.Length);
			}
		}
		
		public static void Main(string[] args)
		{
			TcpListener server = null;
			try
			{
				Int32 port = 1990;
				IPAddress localAddr = IPAddress.Parse("127.0.0.1");
				server = new TcpListener(localAddr, port);
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
		
		private static string Controller(string userRequest)
		{
			string request = userRequest.Split(' ')[0];
			string parameters = userRequest.Split(' ')[1];
			
			switch(request)
			{
				case "registrate":
					string[] registrateParameters = parameters.Split(':');
					
					return Math.Abs(Int32.Parse(parameters)).ToString();
				case "sqr":
					return (Int32.Parse(parameters)*Int32.Parse(parameters)).ToString();
				default:
					return "Incorrect command";
			}
		}
	}
}