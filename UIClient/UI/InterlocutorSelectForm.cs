using System;

namespace OSiSP_6.UI
{
	public partial class InterlocutorSelectForm : MainForm
	{
		
		//private System.Threading.Timer userUpdateTimer = new System.Threading.Timer(GetOnlineClient);
		public InterlocutorSelectForm()
		{
			ComboBoxInit();
			InitializeComponent();
			//userUpdateTimer.Change(0, 10000);
		}
		
		/*	Thread serverReceiver = new Thread(ReceiveMessage);
				serverReceiver.IsBackground = true;
				serverReceiver.Start(serverStream);*/
		
		private static void GetOnlineClient(object state)
		{
			string message = "Client:GetOnlineClients:~" + userID;
			SendMessage(serverStream, message);
			ReceiveMessage(serverStream);
			interlocutor_comboBox.Items.Clear();
			for (int i = 0; i < onlineClients.Length - 1; i++) {
				string[] clientData = onlineClients[i].Split(':');
				interlocutor_comboBox.Items.Add("#" + (i + 1) + " Username: " + clientData[0] + " Age: " + clientData[1]);
			}
			
		}
		
		void Submit_buttonClick(object sender, EventArgs e)
		{
			int selectedUser = interlocutor_comboBox.SelectedIndex;
			if (selectedUser != 0) {
				
			}
		}
	}
}
