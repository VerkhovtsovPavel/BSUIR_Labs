using System;
using System.Windows.Forms;
using Course_project.Controller;
using Course_project.Entity.DB;
using Course_project.Utils;

namespace Course_project.Views
{
	public partial class UserGroupView : Form
	{
		private UserGroups userGroups;
		public UserGroupView()
		{
			InitializeComponent();
			userGroups = (UserGroups)TaskController.GetInstance().Process(CommandType.GET_USER_GROUPS, null); 
			fillGroupComboBox();
		}
		void AddGroup_buttonClick(object sender, EventArgs e)
		{
			RequestParameters group = new RequestParameters();
			
			UserGroupDialogView addGroupView = new UserGroupDialogView(group, ViewMode.ADD_MODE);
			
			DialogResult result = addGroupView.ShowDialog();
			if (result == DialogResult.OK) {
				userGroups.Groups.Add(group.GetParameter<String>("Group"));
				this.group_listBox.Items.Add(group.GetParameter<String>("Group"));
			
				RequestParameters requestParameters = new RequestParameters();
				requestParameters.AddParameter<UserGroups>("UserGroups", userGroups);
				TaskController.GetInstance().Process(CommandType.UPDATE_GROUPS, requestParameters);
			}
			
		}
		void EditGroup_buttonClick(object sender, EventArgs e)
		{
			int selectElement = this.group_listBox.SelectedIndex;
			if (selectElement != -1) {
				RequestParameters group = new RequestParameters();
				group.AddParameter<String>("Old Group", userGroups.Groups[selectElement]);
			
				UserGroupDialogView addGroupView = new UserGroupDialogView(group, ViewMode.EDIT_MODE);
		
				if (addGroupView.ShowDialog() == DialogResult.OK) {
					userGroups.Groups[selectElement]=group.GetParameter<String>("Group");
					
					this.group_listBox.Items.RemoveAt(selectElement);
					this.group_listBox.Items.Insert(selectElement, group.GetParameter<String>("Group"));
			
					RequestParameters requestParameters = new RequestParameters();
					requestParameters.AddParameter<UserGroups>("UserGroups", userGroups);
					TaskController.GetInstance().Process(CommandType.UPDATE_GROUPS, requestParameters);
				}
			} else {
				MessageBox.Show("Please select group");
			}
		}
		void DeleteGroup_buttonClick(object sender, EventArgs e)
		{
			int selectElement = this.group_listBox.SelectedIndex;
			if (selectElement != -1) {
				this.group_listBox.Items.RemoveAt(selectElement);
				this.userGroups.Groups.RemoveAt(selectElement);
				RequestParameters requestParameters = new RequestParameters();
				requestParameters.AddParameter<UserGroups>("UserGroups", userGroups);
				TaskController.GetInstance().Process(CommandType.UPDATE_GROUPS, requestParameters);
			} else {
				MessageBox.Show("Please select group");
			}
		}

		void fillGroupComboBox()
		{
			foreach (String group in userGroups.Groups) {
				this.group_listBox.Items.Add(group);
			}
		}
	}
}
