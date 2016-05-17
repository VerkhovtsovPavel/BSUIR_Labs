namespace Course_project
{
	using System;
	using System.Windows;
	using Course_project.Controller;
	using Course_project.Utils;
	using Course_project.Views;

	public partial class RegistrationView : MainView
	{
		public RegistrationView()
		{
			this.InitializeComponent();
			this.DisableTaskAndProfillingMenu();
			this.FillTimeZoneComboBox();
		}
		
		private void Submit_buttonClick(object sender, EventArgs e)
		{
			if (!this.CheckEmptyFields())
			{
				RequestParameters registrationParameters = new RequestParameters();
				registrationParameters.AddParameter<string>("Login", this.login_textBox.Text);
				registrationParameters.AddParameter<string>("Password", HashUtils.MD5Hash(this.password_textBox.Text));
				registrationParameters.AddParameter<string>("FirstName", this.first_name_textBox.Text);
				registrationParameters.AddParameter<string>("LastName", this.last_name_textBox.Text);
				registrationParameters.AddParameter<string>("TimeZone", this.timeZone_comboBox.Text);
			
				if ((bool)TaskController.GetInstance().Process(CommandType.REGISTRATION, registrationParameters)) 
				{
					MessageBox.Show("Registration successfully");
					this.GoToCalendarePage();
				}
				else
				{
					MessageBox.Show("Duplicate login");
				}
			}
			else
			{
				MessageBox.Show("Please complete all required fields");	
			}
		}

		private void FillTimeZoneComboBox()
		{
			foreach(var info in TimeZoneInfo.GetSystemTimeZones())
			{
				if(!info.Id.Contains("UTC"))
				{
					this.timeZone_comboBox.Items.Add(info.Id);
				}
			}
			
			this.timeZone_comboBox.Sorted = true;
		}
		
		private bool CheckEmptyFields()
		{
			return this.login_textBox.Text.Equals(string.Empty) || this.password_textBox.Text.Equals(string.Empty) || this.first_name_textBox.Text.Equals(string.Empty) || this.last_name_textBox.Text.Equals(string.Empty) || this.timeZone_comboBox.Text.Equals(string.Empty);
		}
	}
}
