using System;
using System.IO;
using System.Net;
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
		protected static System.Threading.Timer userUpdateTimer;
		protected static System.Threading.Timer messageRecieveTimer = new System.Threading.Timer(DialogMessageReceive);
		
		protected const int sessionUpdarePeriod = 5000;
		protected const int messageCheckPeriod = 1000;
		protected const int onlineUserGetPeriod = 5000;
		
		protected static volatile bool isInDialog;
		
		protected static NetworkStream serverStream;
		protected static Socket socket = new Socket(AddressFamily.InterNetwork, SocketType.Dgram, ProtocolType.Udp);
		
		protected static EndPoint companion;

		protected static TcpClient client;
		protected static string[] onlineClients;

		protected static bool isFailStart;
		
		public static LoginForm loginForm;
		protected static InterlocutorSelectForm interlocutorSelectForm = new InterlocutorSelectForm();
		protected static ChatForm chatForm = new ChatForm();

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
				
				string userAddress = ((IPEndPoint)(client.Client.LocalEndPoint)).Address.ToString();
				int userPort = ((IPEndPoint)(client.Client.LocalEndPoint)).Port;		
				IPEndPoint localAddress = new IPEndPoint(IPAddress.Parse(userAddress), userPort);		
				socket.Bind(localAddress);
			} catch (SocketException) {
				MessageBox.Show("Server unavailable");
				isFailStart = true;
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
				Controller(response);
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

		protected static void Controller(string data)
		{
			string sender = data.Split(':')[0];
			string command = data.Split(':')[1];
			string parameters = data.Split('~')[1];
			switch (sender) {
				case "Server":
					ServerAction(command, parameters);
					break;
				case "Client":
					ClientAction(command, parameters);
					break;
			}
		}
		void MainFormFormClosing(object sender, FormClosingEventArgs e)
		{
			Application.Exit();
		}
		
		private static void DialogMessageReceive(object state)
		{
			try {
				byte[] data = new byte[1024];
				int bytes = socket.ReceiveFrom(data, ref companion);
				string response = Encoding.GetEncoding(1251).GetString(data, 0, bytes);
				Controller(response);
			} catch (SocketException) {
				MessageBox.Show("You interlocutor leave char");
				Application.Exit(); 
				/*isInDialog = false;
				ChatForm.chat_textBox.Text = "";
				messageRecieveTimer.Change(-1, -1);
				interlocutorSelectForm.Invoke(new Action(interlocutorSelectForm.Show));
				interlocutorSelectForm.Invoke(new Action(chatForm.Hide));*/
			}
		}
		
		private static void ServerAction(string command, string parameters)
		{
			switch (command) {
				case "You successfully registered":
					userID = parameters;
					break;
				case "Online cliens":
					onlineClients = parameters.Split(';');
					break;
				case "HandShake":
					string[] partnerAddress = parameters.Split(':', 'S');
					IPEndPoint partner = new IPEndPoint(IPAddress.Parse(partnerAddress[0]), Int32.Parse(partnerAddress[1]));
					string message = String.Empty;
					if (!isInDialog) {
						isInDialog = true;
						message = "Client:HandShake:~Ok";
						
						interlocutorSelectForm.Invoke(new Action(interlocutorSelectForm.Hide));
						interlocutorSelectForm.Invoke(new Action(chatForm.Show));
						companion = (EndPoint)partner;
						messageRecieveTimer.Change(5000, messageCheckPeriod);
					} else {
						message = "Client:HandShake:~Lock";
	
					}
					Byte[] d = Encoding.GetEncoding(1251).GetBytes(message);
					socket.SendTo(d, d.Length, SocketFlags.None, (EndPoint)partner);
					break;
			}
		}

		private static void ClientAction(string command, string parameters)
		{
			switch (command) {
				case "HandShake":
					if ("Ok".Equals(parameters)) {
						MessageBox.Show("Dialog start");
						interlocutorSelectForm.Invoke(new Action(interlocutorSelectForm.Hide));
						interlocutorSelectForm.Invoke(new Action(chatForm.Show));
						isInDialog = true;
						messageRecieveTimer.Change(5000, messageCheckPeriod);
					} else {
						MessageBox.Show("Sorry, user alredy in dialog.");
					}
					break;
				case "Message":
					string message = "[" + DateTime.Now.ToString("HH:mm:ss") + "]" + parameters + "\n";
					Action action = () => ChatForm.chat_textBox.AppendText(message);
					ChatForm.chat_textBox.Invoke(action);
					break;
			}
		}
	}
}