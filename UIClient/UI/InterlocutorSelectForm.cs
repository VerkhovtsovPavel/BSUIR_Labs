using System;
using System.Windows.Forms;

namespace OSiSP_6.UI
{
	public partial class InterlocutorSelectForm : MainForm
	{
		
		//private System.Threading.Timer userUpdateTimer = new System.Threading.Timer(GetOnlineClient);
		public InterlocutorSelectForm()
		{
			InitializeComponent();
		//	userUpdateTimer.Change(0,10000);
		}
		
		
		private void GetOnlineClient(object state)
		{
			string message = "getOnlineClients~" + userID;

			SendMessage(serverStream, message);
			//onlineClients = ReceiveMessage(serverStream).Split(';');
			if (onlineClients.Length == 1) {
				MessageBox.Show("You one online user :(");
			} else {
				this.interlocutor_comboBox.Items.Clear();
				for (int i = 0; i < onlineClients.Length - 1; i++) {
					string[] clientData = onlineClients[i].Split(':');
					this.interlocutor_comboBox.Items.Add("#" + (i + 1) + " Username: " + clientData[0] + " Age: " + clientData[1]);
				}
			}
		}
		void Submit_buttonClick(object sender, EventArgs e)
		{
			int selectedUser = interlocutor_comboBox.SelectedIndex;
			if(selectedUser !=0)
			{
				
			}
		}
	}
}
