using System;
using System.Windows.Forms;
using Course_project.Views;

//TODO Add group view
//TODO Add group commands
//TODO Add group logic in dao

//TODO Simple algorithm to convert flexible task to hard task
//TODO Mongo server and db localion(?) 

//TODO Use strategy(bool?) to combane Add and Edit views  
  
//TODO Command pattern instead *Logic classes
//TODO Use session only on logic level

//TODO Application not always closes

//TODO Fix forms size 

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
