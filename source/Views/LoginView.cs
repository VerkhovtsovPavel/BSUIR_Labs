using System;
using System.Collections.Generic;
using System.Windows;
using Course_project.Controller;
using Course_project.Model;
using Course_project.Utils;

namespace Course_project.Views
{

	public partial class LoginView : MainView
	{
		private readonly LoginAndRegistrationController loginAndRegistrationController;
		
		public LoginView()
		{
			InitializeComponent();
			CommandLineCommander.executeCommand("mongod.exe --dbpath "+ProjectProterties.DB_PATH);
			loginAndRegistrationController = LoginAndRegistrationController.GetInstance();
		}
		
		void Login_submit_buttonClick(object sender, EventArgs e)
		{
			Dictionary<String, String> loginParameters = new Dictionary<string, string>();
			
			loginParameters.Add("Login", login_textBox.Text);
			loginParameters.Add("Password" ,HashUtils.MD5Hash(password_textBox.Text));
			if((bool)loginAndRegistrationController.process("login", loginParameters)){
				MessageBox.Show("Login successful");
				goToCalendarePage();
			}else{
				MessageBox.Show("Incorrect login or password");
			}
		}
	}
}
