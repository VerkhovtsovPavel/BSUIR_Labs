using System;
using System.Net.Sockets;
using System.Text;

namespace OSiSP_6.UI
{
	public partial class ChatForm : MainForm
	{
		public ChatForm()
		{
			ChatTextBoxInit();
			InitializeComponent();
		}
		void Send_buttonClick(object sender, EventArgs e)
		{

			string message = "Client:Message:~" + userName+":" + this.message_textBox.Text;
			Byte[] d = Encoding.GetEncoding(1251).GetBytes(message);
			socket.SendTo(d, d.Length, SocketFlags.None, companion);
			chat_textBox.AppendText("[" + DateTime.Now.ToString("HH:mm:ss") + "]"+ userName+":" + this.message_textBox.Text+"\n");
			this.message_textBox.Text = "";

		}
	}
}
