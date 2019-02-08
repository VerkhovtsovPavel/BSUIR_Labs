using System;
using System.Windows.Forms;

namespace OSiSP_6.UI
{
	public partial class LoginForm : MainForm
	{
		public LoginForm()
		{
			InitializeComponent();
		}
		
		private void ForceClose(object sender, EventArgs e){
			if(isFailStart)
			{
				Close();
			}
		}
		
		void Submit_buttonClick(object sender, EventArgs e)
		{
			if(this.Username_textBox.Text != String.Empty){
				userName = this.Username_textBox.Text;
				string message = "Client:Registered:~" + this.Username_textBox.Text + ":" + this.Age_numericUpDown.Value;
				SendMessage(serverStream, message);
				ReceiveMessage(serverStream);
				sessionUpdareTimer.Change(sessionUpdarePeriod, sessionUpdarePeriod);
				loginForm.Hide();
				interlocutorSelectForm.Init();
				interlocutorSelectForm.Show();
			}
			else
			{
				MessageBox.Show("Please enter username");
			}
		}
	}
}
