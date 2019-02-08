namespace UIClient
{
	using System;
	using System.Windows.Forms;
	using OSiSP_6.UI;

	internal sealed class Program
	{
		[STAThread]
		private static void Main(string[] args)
		{
			Application.EnableVisualStyles();
			Application.SetCompatibleTextRenderingDefault(false);
			MainForm.loginForm = new LoginForm();
			Application.Run(MainForm.loginForm);
		}
	}
}
