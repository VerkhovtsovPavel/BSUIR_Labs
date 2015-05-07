/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 04/17/2015
 * Time: 20:37
 */
using System;
using System.Collections.Generic;
using System.Windows;
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
		public RegistrationView()
		{
			InitializeComponent();
			
			this.tasksToolStripMenuItem.Enabled = false;
			this.profillingToolStripMenuItem.Enabled = false;
			fillTimeZoneComboBox();
		}
		
		private void Submit_buttonClick(object sender, EventArgs e)
		{
			RequestParameters registrationParameters = new RequestParameters();
			registrationParameters.addString("Login", login_textBox.Text);
			registrationParameters.addString("Password" ,HashUtils.MD5Hash(password_textBox.Text));
			registrationParameters.addString("FirstName", first_name_textBox.Text);
			registrationParameters.addString("LastName", last_name_textBox.Text);
			registrationParameters.addString("TimeZone", timeZone_comboBox.Text);
			
			if((bool)TaskController.GetInstance().Process(CommandType.REGISTRATION, registrationParameters)){
				MessageBox.Show("Registration successfully");
				goToCalendarePage();
			}
			else{
				MessageBox.Show("Duplicate login");
			}
		}

		void fillTimeZoneComboBox()
		{
				foreach(var info in TimeZoneInfo.GetSystemTimeZones()){
				if(!info.Id.Contains("UTC")){
					this.timeZone_comboBox.Items.Add(info.Id);
				}
			}
			this.timeZone_comboBox.Sorted = true;
		}
	}
}
