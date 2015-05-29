using System;
using System.IO;
using System.Net.Sockets;
using System.Text;
using System.Windows.Forms;
using UIClient;

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

		static MainForm()
		{
			Connect(ClientProperties.SERVER_ADDRESS, ClientProperties.SERVER_PORT);
		}
		
		public MainForm()
		{
			InitializeComponent();
		}
		
		static void sessionUpdate(object state)
		{
			string message = "Client:ClientAlive:~" + userID;
			SendMessage(serverStream, message);
		}
		
		protected static void Connect(String serverIP, int serverPort)
		{
			try {
				client = new TcpClient(serverIP, serverPort);
				serverStream = client.GetStream();
			} catch (SocketException e) {
				MessageBox.Show("Server unavailable");
				ActiveForm.Close();
				//TODO Don't work
			}
		}
		
		protected static void ReceiveMessages(object networkStream)
		{
			NetworkStream stream = (NetworkStream)networkStream;
			while (true) {
				try {
					Byte[] data = new Byte[256];
					String response = String.Empty;
					Int32 bytes = stream.Read(data, 0, data.Length);
					response = Encoding.GetEncoding(1251).GetString(data, 0, bytes);
					Controller(stream, response);
				} catch (IOException) {
					MessageBox.Show("Server down");
					Application.Exit();
				}
			}
		}
		
		protected static void ReceiveMessage(object networkStream)
		{
			NetworkStream stream = (NetworkStream)networkStream;
			try {
				Byte[] data = new Byte[256];
				String response = String.Empty;
				Int32 bytes = stream.Read(data, 0, data.Length);
				response = Encoding.GetEncoding(1251).GetString(data, 0, bytes);
				Controller(stream, response);
			} catch (IOException) {
				MessageBox.Show("Server down");
				Application.Exit();
			}
		}
		
		
		//Make private and create to methods send to server and partner
		protected static void SendMessage(NetworkStream stream, String message)
		{
			try {
				Byte[] data = Encoding.GetEncoding(1251).GetBytes(message);
				stream.Write(data, 0, data.Length);
			} catch (IOException) {
				Console.WriteLine("Server down");
			}
		}

		private static void Controller(NetworkStream stream, string data)
		{
			string sender = data.Split(':')[0];
			string command = data.Split(':')[1];
			string parameters = data.Split('~')[1];
			switch (sender) {
				case "Server":
					switch (command) {
						case "You successfully registered":
							userID = parameters;
							break;
						case "Online cliens":
							onlineClients = parameters.Split(';');
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
		void MainFormFormClosing(object sender, FormClosingEventArgs e)
		{
			Application.Exit();
		}
	}
}

