using System;
using System.Windows.Forms;
using Course_project.Views;

//TODO Add group
//TODO Simple algorithm to convert flexible task to hard task
//TODO Mongo server and db localion(?) 
//TODO Dispose server

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
