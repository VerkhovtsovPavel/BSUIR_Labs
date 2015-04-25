using System;
using System.Drawing;
using System.Windows.Forms;

namespace Course_project.Views
{

	public abstract partial class MainView : Form
	{
		protected MainView(){
			InitializeComponent();
		}
		
		private void goToRegistrationPage(){
			RegistrationView registrationView = new RegistrationView();
			registrationView.Show();
			Hide();
		}

		private void exitWithApplication(){
			Close();
		}
		
		void ExitToolStripMenuItemClick(object sender, EventArgs e)
		{
			exitWithApplication();
		}
		
		void RegistrationToolStripMenuItemClick(object sender, EventArgs e)
		{
			goToRegistrationPage();
		}
	}
}
