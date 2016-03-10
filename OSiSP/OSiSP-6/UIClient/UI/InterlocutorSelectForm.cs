using System;
using System.Net;
using System.Text;
using System.Windows.Forms;

namespace OSiSP_6.UI
{
	public partial class InterlocutorSelectForm : MainForm
	{
		public InterlocutorSelectForm()
		{
			ComboBoxInit();
			InitializeComponent();
		}
		
		public void Init()
		{
			userUpdateTimer = new System.Threading.Timer(GetOnlineClient);
			userUpdateTimer.Change(0, 5000);
		}
		
		
		private static void GetOnlineClient(object state)
		{
			string message = "Client:GetOnlineClients:~" + userID;
			SendMessage(serverStream, message);
			ReceiveMessage(serverStream);
			interlocutor_comboBox.Invoke(new Action(interlocutor_comboBox.Items.Clear));
			for (int i = 0; i < onlineClients.Length - 1; i++)
			{
				string[] clientData = onlineClients[i].Split(':');
				string addString = "#" + (i + 1) + " Username: " + clientData[0] + " Age: " + clientData[1];
				Action action = () => interlocutor_comboBox.Items.Add(addString);
				interlocutor_comboBox.Invoke(action);
			}
		}
		
		void Submit_buttonClick(object sender, EventArgs e)
		{
			int selectedUser = interlocutor_comboBox.SelectedIndex;
			if (selectedUser != -1)
			{
				this.Submit_button.Enabled = false;
				string[] clientData = onlineClients[selectedUser].Split(':');
				string address = clientData[2];
				int port = Int32.Parse(clientData[3]);
				string message = "Client:HandShake:~" + address + ":" + port;
				
				SendMessage(serverStream, message);
				
				EndPoint partner = new IPEndPoint(IPAddress.Parse(address), port);
				byte[] data = new byte[1024];
				int bytes = socket.ReceiveFrom(data, ref partner);
				companion = partner;
				string response = Encoding.GetEncoding(1251).GetString(data, 0, bytes);
				Controller(response);
				this.Submit_button.Enabled = true;
			}
			else
			{
				MessageBox.Show("Please, select user.");
			}
		}
	}
}
