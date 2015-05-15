namespace Course_project.Views
{
	using System;
	using System.Windows.Forms;
	using Course_project.Controller;
	using Course_project.Entity.DB;
	using Course_project.Utils;
	
	public partial class ProfilingView : MainView
	{
		public ProfilingView()
		{
			this.InitializeComponent();
			this.DisableFileMenu();
			this.FillGroupComboBox();
		}
		
		private void SpentTime_buttonClick(object sender, EventArgs e)
		{
			string group = this.group_comboBox.Text;
			if (group.Equals(string.Empty)) 
			{
				MessageBox.Show("Please select group");
			}
			else
			{
				RequestParameters requestParameters = new RequestParameters();
				requestParameters.AddParameter<string>("Group", this.group_comboBox.Text);
				int spentTime = (int)TaskController.GetInstance().Process(CommandType.GET_TIME_SPENT_BY_GROUP, requestParameters);
				MessageBox.Show("On tasks with " + this.group_comboBox.Text + " group spent " + spentTime + " minute");
			}
		}

		private void FillGroupComboBox()
		{
			UserGroups userGroups = (UserGroups)TaskController.GetInstance().Process(CommandType.GET_USER_GROUPS, null);	
			foreach (string group in userGroups.Groups)
			{
				this.group_comboBox.Items.Add(group);
			}
		}
	}
}
