using System;
using System.Windows.Forms;
using Course_project.Views;

//TODO Add collection in DB
//TODO Create base views(registration, login, addTask, taskViewer)
//TODO Implement base controlers

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
