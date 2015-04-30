using System;
using System.Windows.Forms;
using Course_project.Views;

//TODO Implement base controlers
//TODO Fix app start (console + form)
//TODO Fix menu points enabled

namespace Course_project
{
	internal sealed class Program
	{

		[STAThread]
		private static void Main(string[] args)
		{
			Application.EnableVisualStyles();
			Application.SetCompatibleTextRenderingDefault(false);
			Application.Run(new LoginView());
		}
	}
}
