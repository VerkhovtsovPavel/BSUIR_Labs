using System;
using System.Windows;
using Course_project.Controller;
using Course_project.Utils;
using Course_project.Views;

namespace Course_project
{
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
			registrationParameters.AddParameter<String>("Login", login_textBox.Text);
			registrationParameters.AddParameter<String>("Password" ,HashUtils.MD5Hash(password_textBox.Text));
			registrationParameters.AddParameter<String>("FirstName", first_name_textBox.Text);
			registrationParameters.AddParameter<String>("LastName", last_name_textBox.Text);
			registrationParameters.AddParameter<String>("TimeZone", timeZone_comboBox.Text);
			
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
