using System;
using System.Drawing;
using System.Windows.Forms;
using Course_project.Controller;
using Course_project.Entity;

namespace Course_project.Views
{

	public partial class LoginView : Form
	{
		
		readonly LoginAndRegistrationController loginAndRegistrationController;
		
		public LoginView()
		{
			InitializeComponent();
			loginAndRegistrationController = new LoginAndRegistrationController();
		}
		
		void Login_submit_buttonClick(object sender, EventArgs e)
		{
			loginAndRegistrationController.process("login",new AuthenticationData(login_textBox.Text, password_textBox.Text));
		}
		void ExitToolStripMenuItemClick(object sender, EventArgs e)
		{
	
		}
		void RegistrationToolStripMenuItemClick(object sender, EventArgs e)
		{
			RegistrationView registrationView = new RegistrationView(loginAndRegistrationController);
			registrationView.Show();
		}
		

	}
}
