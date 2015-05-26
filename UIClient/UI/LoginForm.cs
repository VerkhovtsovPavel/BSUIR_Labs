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
				string message = "registered~" + this.Username_textBox.Text + ":" + this.Age_numericUpDown.Value;

				SendMessage(serverStream, message);

				//TcpListener listener = new TcpListener(((IPEndPoint)(client.Client.LocalEndPoint)).Address, ((IPEndPoint)(client.Client.LocalEndPoint)).Port);
				//userID = ReceiveMessage(serverStream);
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
