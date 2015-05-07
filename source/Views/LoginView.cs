using System;
using System.Windows;
using Course_project.Controller;
using Course_project.Utils;

namespace Course_project.Views
{

	public partial class LoginView : MainView
	{
		private readonly LoginAndRegistrationController loginAndRegistrationController;

		public LoginView()
		{
			InitializeComponent();
			this.tasksToolStripMenuItem.Enabled = false;
			this.profillingToolStripMenuItem.Enabled = false;
			loginAndRegistrationController = LoginAndRegistrationController.GetInstance();
		}
		
		void Login_submit_buttonClick(object sender, EventArgs e)
		{
			RequestParameters loginParameters = new RequestParameters();
			
			loginParameters.addString("Login", login_textBox.Text);
			loginParameters.addString("Password" ,HashUtils.MD5Hash(password_textBox.Text));
			if((bool)loginAndRegistrationController.process("login", loginParameters)){
				MessageBox.Show("Login successfully");
				goToCalendarePage();
			}else{
				MessageBox.Show("Incorrect login or password");
			}
		}
		void LoginViewFormClosing(object sender, System.Windows.Forms.FormClosingEventArgs e)
		{
		}
		
	}
}
