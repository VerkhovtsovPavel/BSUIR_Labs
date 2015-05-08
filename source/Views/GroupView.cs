using System;
using System.Windows.Forms;
using Course_project.Controller;
using Course_project.Entity.DB;
using Course_project.Utils;

namespace Course_project.Views
{
	public partial class GroupView : Form
	{
		private UserGroups userGroups;
		public GroupView()
		{
			InitializeComponent();
			userGroups = (UserGroups)TaskController.GetInstance().Process(CommandType.GET_USER_GROUPS, null); //TODO Get groups from db
			fillGroupComboBox();
		}
		void AddGroup_buttonClick(object sender, EventArgs e)
		{
			RequestParameters group = new RequestParameters();
			
			AddGroupView addGroupView = new AddGroupView(group);
			addGroupView.ShowDialog();
			
			userGroups.Groups.Add(group.GetParameter<String>("Group"));
			this.group_listBox.Items.Add(group.GetParameter<String>("Group"));
			
			RequestParameters requestParameters = new RequestParameters();
			requestParameters.AddParameter<UserGroups>("UserGroups", userGroups);
			TaskController.GetInstance().Process(CommandType.UPDATE_GROUPS, requestParameters);
			
		}
		void EditGroup_buttonClick(object sender, EventArgs e)
		{
			int selectElement = this.group_listBox.SelectedIndex;
			if (selectElement != -1)
			{
				/*RequestParameters requestParameters = new RequestParameters();
				requestParameters.addTask("Task", taskToShow[this.tasks_listBox.SelectedIndex]);
				TaskController.GetInstance().Process(CommandType.ADD_SHARE_TASK, requestParameters);*/
			} else {
				MessageBox.Show("Please select group");
			}
		}
		void DeleteGroup_buttonClick(object sender, EventArgs e)
		{
			int selectElement = this.group_listBox.SelectedIndex;
			if (selectElement != -1)
			{
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
			foreach(String group in userGroups.Groups){
				addToListBox(group);
			}
		}
		
		public void addToListBox(String group){
			this.group_listBox.Items.Add(group);
		}
	}
}
