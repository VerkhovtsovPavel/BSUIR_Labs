/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 04/17/2015
 * Time: 20:37
 */
using System;
using System.Collections.Generic;
using Course_project.Controller;
using Course_project.Utils;
using Course_project.Views;

namespace Course_project
{
	/// <summary>
	/// Description of RegistrationView.
	/// </summary>
	public partial class RegistrationView : MainView
	{
		private readonly LoginAndRegistrationController loginAndRegistrationController;
		
		public RegistrationView()
		{
			this.loginAndRegistrationController = LoginAndRegistrationController.GetInstance();
			InitializeComponent();
		}
		
		private void Submit_buttonClick(object sender, EventArgs e)
		{
			Dictionary<String, String> registrationParameters = new Dictionary<string, string>();
			
			registrationParameters.Add("Login", login_textBox.Text);
			registrationParameters.Add("Password" ,HashUtils.MD5Hash(password_textBox.Text));
			registrationParameters.Add("FirstName", first_name_textBox.Text);
			registrationParameters.Add("LastName", last_name_textBox.Text);
			registrationParameters.Add("TimeZone", timeZone_comboBox.Text);
			loginAndRegistrationController.process("registrate", registrationParameters);
		}
	}
}
