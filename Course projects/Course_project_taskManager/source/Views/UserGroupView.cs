namespace Course_project.Views
{
	using System;
	using System.Windows.Forms;
	using Course_project.Controller;
	using Course_project.Entity.DB;
	using Course_project.Utils;
	
	public partial class UserGroupView : Form
	{
		private UserGroups userGroups;
		
		public UserGroupView()
		{
			this.InitializeComponent();
			this.userGroups = (UserGroups)TaskController.GetInstance().Process(CommandType.GET_USER_GROUPS, null); 
			this.FillGroupComboBox();
		}
		
		private void AddGroup_buttonClick(object sender, EventArgs e)
		{
			RequestParameters group = new RequestParameters();
			
			UserGroupDialogView addGroupView = new UserGroupDialogView(group, ViewMode.ADD_MODE);
			
			DialogResult result = addGroupView.ShowDialog();
			if (result == DialogResult.OK) 
			{
				this.userGroups.Groups.Add(group.GetParameter<string>("Group"));
				this.group_listBox.Items.Add(group.GetParameter<string>("Group"));
			
				RequestParameters requestParameters = new RequestParameters();
				requestParameters.AddParameter<UserGroups>("UserGroups", this.userGroups);
				TaskController.GetInstance().Process(CommandType.UPDATE_GROUPS, requestParameters);
			}
		}
		
		private void EditGroup_buttonClick(object sender, EventArgs e)
		{
			int selectElement = this.group_listBox.SelectedIndex;
			if (selectElement != -1)
			{
				RequestParameters group = new RequestParameters();
				group.AddParameter<string>("Old Group", this.userGroups.Groups[selectElement]);
			
				UserGroupDialogView addGroupView = new UserGroupDialogView(group, ViewMode.EDIT_MODE);
		
				if (addGroupView.ShowDialog() == DialogResult.OK)
				{
					this.userGroups.Groups[selectElement]=group.GetParameter<string>("Group");
					
					this.group_listBox.Items.RemoveAt(selectElement);
					this.group_listBox.Items.Insert(selectElement, group.GetParameter<string>("Group"));
			
					RequestParameters requestParameters = new RequestParameters();
					requestParameters.AddParameter<UserGroups>("UserGroups", this.userGroups);
					TaskController.GetInstance().Process(CommandType.UPDATE_GROUPS, requestParameters);
				}
			}
			else
			{
				MessageBox.Show("Please select group");
			}
		}
		
		private void DeleteGroup_buttonClick(object sender, EventArgs e)
		{
			int selectElement = this.group_listBox.SelectedIndex;
			if (selectElement != -1)
			{
				this.group_listBox.Items.RemoveAt(selectElement);
				this.userGroups.Groups.RemoveAt(selectElement);
				RequestParameters requestParameters = new RequestParameters();
				requestParameters.AddParameter<UserGroups>("UserGroups", this.userGroups);
				TaskController.GetInstance().Process(CommandType.UPDATE_GROUPS, requestParameters);
			}
			else
			{
				MessageBox.Show("Please select group");
			}
		}

		private void FillGroupComboBox()
		{
			foreach (string group in this.userGroups.Groups)
			{
				this.group_listBox.Items.Add(group);
			}
		}
	}
}
