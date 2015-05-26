using System;
using System.IO;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using System.Windows.Forms;

namespace OSiSP_6.UI
{
	public partial class MainForm : Form
	{
		protected static string userName;
		protected static int age;
		
		protected static string userID;
		
		protected static System.Threading.Timer sessionUpdareTimer = new System.Threading.Timer(sessionUpdate);
		protected const int sessionUpdarePeriod = 5000;
		
		protected static NetworkStream serverStream;
		protected static NetworkStream companionStream;

		protected static TcpClient client;
		protected static string[] onlineClients;
		
		public MainForm()
		{
			InitializeComponent();
			//TODO Move to constant string
			Connect("127.0.0.1", 1990);
		}
		
		static void sessionUpdate(object state)
		{
			string message = "clientAlive~" + userID;
			SendMessage(serverStream, message);
		}
		
		
		protected void Connect(String serverIP, int serverPort)
		{
			try {
				client = new TcpClient(serverIP, serverPort);
				serverStream = client.GetStream();
				Thread serverReceiver = new Thread(ReceiveMessage);
				serverReceiver.IsBackground = true;
				serverReceiver.Start(serverStream);
			} catch (SocketException e) {
				MessageBox.Show("Server unavailable");
				Application.Exit(); //TODO Don't work
			}
		}
		
		protected static void ReceiveMessage(object networkStream)
		{
			NetworkStream stream = (NetworkStream)networkStream;
			while(true){
				try {
					Byte[] data = new Byte[256];
					String responseData = String.Empty;
					Int32 bytes = stream.Read(data, 0, data.Length);
					responseData = Encoding.GetEncoding(1251).GetString(data, 0, bytes);
					//TODO Rename variable
					Controller(stream, responseData);
				} catch (IOException) {
					MessageBox.Show("Server down");
					Application.Exit();
				}
			}
		}
		
		
		//Make private and create to methods send to server and partner
		protected static void SendMessage(NetworkStream stream, String message)
		{
			try {
				Byte[] data = Encoding.GetEncoding(1251).GetBytes(message);
				stream.Write(data, 0, data.Length);
				Console.WriteLine("Send: {0}", message); //TODO Debug method
			} catch (IOException) {
				Console.WriteLine("Server down");
			}
		}

		private static void Controller(NetworkStream stream, string data)
		{
			MessageBox.Show("We in!!!");
			string sender = data.Split(':')[0];
			string command = data.Split(':')[1];
			switch (sender) {
				case "Server":
					switch (command) {
						case "You successfully registered":
							userID = data.Split(':')[1];
							break;
						case "Online cliens":
							onlineClients = data.Split(':')[2].Split(';');
							/*if (onlineClients.Length == 1) {
								Console.WriteLine("You one online user :(");
							} else {
								for (int i = 0; i < onlineClients.Length - 1; i++) {
									string[] clientData = onlineClients[i].Split(':');
									Console.WriteLine("#" + (i + 1) + " Username: " + clientData[0] + " Age: " + clientData[1]);
								}
							}*/
							break;
							
					}
					break;
				case "Client":
					switch (command) {
						case "Handshake":
							companionStream = stream;
							break;
						case "Message":
							//Write to textBox
							break;
					}
					break;
			}
		}
	}
}

