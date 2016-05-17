namespace Course_project.Views
{
	using System;
	using System.Windows;
	using Course_project.Controller;
	using Course_project.Utils;

	public partial class LoginView : MainView
	{
		public LoginView()
		{
			this.InitializeComponent();
			this.DisableTaskAndProfillingMenu();
		}
		
		private void Login_submit_buttonClick(object sender, EventArgs e)
		{
			if(!this.CheckEmptyFields())
			{
				RequestParameters loginParameters = new RequestParameters();
				
				loginParameters.AddParameter<string>("Login", this.login_textBox.Text);
				loginParameters.AddParameter<string>("Password" ,HashUtils.MD5Hash(this.password_textBox.Text));
				if((bool)TaskController.GetInstance().Process(CommandType.LOGIN, loginParameters))
				{
					MessageBox.Show("Login successfully");
					this.GoToCalendarePage();
				}
				else
				{
					MessageBox.Show("Incorrect login or password");
				}
			}
			else
			{
				MessageBox.Show("Please complete all required fields");	
			}
		}
	
		private bool CheckEmptyFields()
		{
			return this.login_textBox.Text.Equals(string.Empty) || this.password_textBox.Text.Equals(string.Empty);
		}
	}
}
