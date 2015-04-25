/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 04/17/2015
 * Time: 20:37
 * 
 * To change this template use Tools | Options | Coding | Edit Standard Headers.
 */
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Windows.Forms;
using Course_project.Controller;
using Course_project.Entity;
using Course_project.Views;

namespace Course_project
{
	/// <summary>
	/// Description of RegistrationView.
	/// </summary>
	public partial class RegistrationView : MainView
	{
		LoginAndRegistrationController loginAndRegistrationController;
		
		public RegistrationView(){
			InitializeComponent();
		}
		
		public RegistrationView(LoginAndRegistrationController loginAndRegistrationController)
		{
			this.loginAndRegistrationController = loginAndRegistrationController;
			InitializeComponent();
			

		}
		void Submit_buttonClick(object sender, EventArgs e)
		{
			Dictionary<String, object> registrationParameters = new Dictionary<string, object>();
			
			registrationParameters.Add("Login", login_textBox.Text);
			registrationParameters.Add("Password" ,password_textBox.Text);
			registrationParameters.Add("FirstName", first_name_textBox.Text);
			registrationParameters.Add("LastName", last_name_textBox.Text);
			registrationParameters.Add("TimeZone", timeZone_comboBox.Text);
			loginAndRegistrationController.process("registrate", registrationParameters);
		}
	}
}
