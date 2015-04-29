using System;
using System.Collections.Generic;
using System.Windows;
using Course_project.Controller;

namespace Course_project.Views
{

	public partial class LoginView : MainView
	{
		private readonly LoginAndRegistrationController loginAndRegistrationController;
		
		public LoginView()
		{
			InitializeComponent();
			loginAndRegistrationController = LoginAndRegistrationController.GetInstance();
		}
		
		void Login_submit_buttonClick(object sender, EventArgs e)
		{
			Dictionary<String, object> loginParameters = new Dictionary<string, object>();
			
			loginParameters.Add("Login", login_textBox.Text);
			loginParameters.Add("Password" ,password_textBox.Text);
			if((bool)loginAndRegistrationController.process("login", loginParameters)){
				MessageBox.Show("Login successful");
				goToCalendarePage();
			}else{
				MessageBox.Show("Incorrect login or password");
			}
		}
	}
}
