using System;
using System.Net;
using System.Net.Sockets;
using System.Threading;
using System.Windows.Forms;

namespace OSiSP_6.UI
{
	public partial class LoginForm : MainForm
	{
		public LoginForm()
		{
			InitializeComponent();
		}
		
		void Submit_buttonClick(object sender, EventArgs e)
		{
			if(this.Username_textBox.Text != String.Empty){
				string message = "Client:Registered:~" + this.Username_textBox.Text + ":" + this.Age_numericUpDown.Value;
				SendMessage(serverStream, message);
				ReceiveMessage(serverStream);
				sessionUpdareTimer.Change(sessionUpdarePeriod, sessionUpdarePeriod);
				new InterlocutorSelectForm().Show();
				this.Hide();
			}
			else
			{
				MessageBox.Show("Please enter username");
			}
		}
	}
}
