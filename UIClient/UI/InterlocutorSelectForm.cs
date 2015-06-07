using System;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Windows.Forms;

namespace OSiSP_6.UI
{
	public partial class InterlocutorSelectForm : MainForm
	{
		public InterlocutorSelectForm()
		{
			userUpdateTimer = new System.Threading.Timer(GetOnlineClient);
			ComboBoxInit();
			InitializeComponent();
			userUpdateTimer.Change(0, 5000);
		}
		
		
		private static void GetOnlineClient(object state)
		{
			string message = "Client:GetOnlineClients:~" + userID;
			SendMessage(serverStream, message);
			ReceiveMessage(serverStream);
			interlocutor_comboBox.Invoke(new Action(interlocutor_comboBox.Items.Clear));
			for (int i = 0; i < onlineClients.Length - 1; i++) {
				string[] clientData = onlineClients[i].Split(':');
				string addString = "#" + (i + 1) + " Username: " + clientData[0] + " Age: " + clientData[1];
				Action action = () => interlocutor_comboBox.Items.Add(addString);
				interlocutor_comboBox.Invoke(action);
			}
			//Form1.richTextBox1.Invoke(new Action(() => Form1.richTextBox1.Text = _in + "\n" + "***********" + "\n" + Form1.richTextBox1.Text))
		}
		
		void Submit_buttonClick(object sender, EventArgs e)
		{
			userUpdateTimer.Change(-1, -1);
			int selectedUser = interlocutor_comboBox.SelectedIndex;
			if (selectedUser != -1) {
				string[] clientData = onlineClients[selectedUser].Split(':');
				string address = clientData[2];
				int port = Int32.Parse(clientData[3]);
				//StartClient(address, port);
				string message = "Client:HandShake:~" + address + ":" + port;
				
				Socket server = new Socket(AddressFamily.InterNetwork, SocketType.Dgram, ProtocolType.Udp);
				string userAddress = ((IPEndPoint)(client.Client.LocalEndPoint)).Address.ToString();
				int userPort = ((IPEndPoint)(client.Client.LocalEndPoint)).Port;		
				IPEndPoint localAddress = new IPEndPoint(IPAddress.Parse(userAddress), userPort);		
				server.Bind(localAddress);
				
				SendMessage(serverStream, message);
				//startServer();
				
				
				EndPoint partner = new IPEndPoint(IPAddress.Parse(address), port);
						
				byte[] data = new byte[1024];
				int bytes = server.ReceiveFrom(data, ref partner);
				string response = Encoding.GetEncoding(1251).GetString(data, 0, bytes);
				Controller(null, response);
		
			} else {
				MessageBox.Show("Please, select user");
			}
		}
	}
}
