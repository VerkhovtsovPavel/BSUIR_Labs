using System;
using System.Collections.Generic;
using System.Drawing;
using System.Windows.Forms;
using Course_project.Controller;
using Course_project.Entity;

namespace Course_project.Views
{

	public partial class LoginView : MainView
	{
		
		readonly LoginAndRegistrationController loginAndRegistrationController;
		
		public LoginView()
		{
			InitializeComponent();
			loginAndRegistrationController = new LoginAndRegistrationController();
		}
		
		void Login_submit_buttonClick(object sender, EventArgs e)
		{
			Dictionary<String, object> loginParameters = new Dictionary<string, object>();
			
			loginParameters.Add("Login", login_textBox.Text);
			loginParameters.Add("Password" ,password_textBox.Text);
			if((bool)loginAndRegistrationController.process("login",	loginParameters)){
				
			}
		}
		
		void ExitToolStripMenuItemClick(object sender, EventArgs e)
		{
	
		}
		
		void RegistrationToolStripMenuItemClick(object sender, EventArgs e)
		{
			//goToRegistrationPage();
		}
		
	
		

	}
}
