using System;
using System.IO;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;
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
		protected static System.Threading.Timer userUpdateTimer;
		//	protected static System.Threading.Timer messageRecieveTimer = new System.Threading.Timer(DialogMessageReceive);
		protected const int sessionUpdarePeriod = 5000;
		protected const int messageCheckPeriod = 1000;
		
		protected static volatile bool isInDialog;
		
		protected static NetworkStream serverStream;
		protected static UdpClient companion;

		protected static TcpClient client;
		protected static string[] onlineClients;

		protected static bool isFailStart;
		
		protected static ManualResetEvent tcpClientConnected = new ManualResetEvent(false);
		
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
			} catch (SocketException) {
				MessageBox.Show("Server unavailable");
				isFailStart = true;
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
		
		protected static void SendMessage(NetworkStream stream, String message)
		{
			try {
				Byte[] data = Encoding.GetEncoding(1251).GetBytes(message);
				stream.Write(data, 0, data.Length);
			} catch (IOException) {
				Console.WriteLine("Server down");
				Application.Exit();
			}
		}

		protected static void Controller(NetworkStream stream, string data)
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
						case "HandShake":
							userUpdateTimer.Change(-1, -1);
							string[] partnerAddress = parameters.Split(':','S');
							
							IPEndPoint partner = new IPEndPoint(
								                  IPAddress.Parse(partnerAddress[0]), Int32.Parse(partnerAddress[1]));

							Socket server = new Socket(AddressFamily.InterNetwork, SocketType.Dgram, ProtocolType.Udp);
							string userAddress = ((IPEndPoint)(client.Client.LocalEndPoint)).Address.ToString();
							int userPort = ((IPEndPoint)(client.Client.LocalEndPoint)).Port;
							
							IPEndPoint localAddress = new IPEndPoint(IPAddress.Parse(userAddress), userPort);
							
							server.Bind(localAddress);
      
							string message = String.Empty;
							if (!isInDialog) {
								isInDialog = true;
								message = "Client:HandShake:~Ok";
								//companion = patencialPartner;
								//messageRecieveTimer.Change(0, messageCheckPeriod);
								//ActiveForm.Hide();
								new CharForm().Show();
							} else {
								message = "Client:HandShake:~Lock";
								userUpdateTimer.Change(0, 5000);
							}
							Byte[] d = Encoding.GetEncoding(1251).GetBytes(message);
							server.SendTo(d, d.Length, SocketFlags.None, (EndPoint) partner);
							//startServer();
							break;
					}
					break;
				case "Client":
					switch (command) {
						case "HandShake":
							if ("Ok".Equals(parameters)) {
								MessageBox.Show("Dialog start");
								//ActiveForm.Hide();
								new CharForm().Show();
							} else {
								userUpdateTimer.Change(0, 5000);
								MessageBox.Show("Sorry, user alredy in dialog");
							}
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
		
		/*private static void DialogMessageReceive(object state)
		{
			ReceiveMessage(companionStream);
		}*/
		
		
		
		public static void startServer()
		{
			string userAddress = ((IPEndPoint)(client.Client.LocalEndPoint)).Address.ToString();
				int userPort = ((IPEndPoint)(client.Client.LocalEndPoint)).Port;
			TcpListener server = null;
			try {
				IPAddress localAddr = IPAddress.Parse(userAddress);
				server = new TcpListener(localAddr, userPort);
				server.Start();
				DoBeginAcceptTcpClient(server);
			} finally {
				server.Stop();
			}
		}
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
			try {
				while ((i = stream.Read(bytes, 0, bytes.Length)) != 0) {
					data = Encoding.GetEncoding(1251).GetString(bytes, 0, i);
					Console.WriteLine("Recieve: " + data);

					Controller(client.GetStream(), data);
				}
			} catch (IOException) {
				string userAddress = ((IPEndPoint)(client.Client.RemoteEndPoint)).Address.ToString();
				int userPort = ((IPEndPoint)(client.Client.RemoteEndPoint)).Port;
				Console.WriteLine("Error while work with client {0}:{1}", userAddress, userPort);
			}
		}
		
		
	}
}