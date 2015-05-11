
//TODO Simple algorithm to convert flexible task to hard task
//TODO Mongo server and db localion(?) 

//TODO Use strategy(enum) to combane Add and Edit views  
  
//TODO If user delete group need remove group from all tasks

//TODO Application not always closes

//TODO Fix forms size 

//TODO Fix code style warnings (StyleCop)

//TODO Catch excection Session(where??)

//TODO Move all string literal to const fienlds

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
