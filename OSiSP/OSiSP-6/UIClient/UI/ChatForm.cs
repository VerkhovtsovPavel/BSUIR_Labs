namespace OSiSP_6.UI
{
	using System;
	using System.Net.Sockets;
	using System.Text;

	public partial class ChatForm : MainForm
	{
		public ChatForm()
		{
			ChatTextBoxInit();
			this.InitializeComponent();
		}
		
		private void Send_buttonClick(object sender, EventArgs e)
		{
			string message = "Client:Message:~" + userName+":" + this.message_textBox.Text;
			byte[] d = Encoding.GetEncoding(1251).GetBytes(message);
			socket.SendTo(d, d.Length, SocketFlags.None, companion);
			chat_textBox.AppendText("[" + DateTime.Now.ToString("HH:mm:ss") + "]"+ userName+":" + this.message_textBox.Text+"\n");
			this.message_textBox.Text = string.Empty;
		}
	}
}
