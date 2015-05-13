//TODO Simple algorithm to convert flexible task to hard task
  
//TODO Fix forms size 

//TODO Fix code style

//TODO Catch excection Session(where??)

//TODO Move all string literal to const fienlds

//TODO Check empty fields!!

namespace Course_project
{
	using System;
	using System.Windows.Forms;
	using Course_project.Views;
	
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
