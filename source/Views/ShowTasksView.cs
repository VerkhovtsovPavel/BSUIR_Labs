using System;
using System.Collections.Generic;
using System.Windows.Forms;
using Course_project.Controller;
using Course_project.Entity;
using Course_project.Utils;

namespace Course_project.Views
{
	//TODO Add field to change interval
	public partial class ShowTasksView : MainView
	{
		private List<Task> taskToShow;
		public ShowTasksView(List<Task> tasks)
		{
			InitializeComponent();
			this.fileToolStripMenuItem.Enabled = false;
			taskToShow = tasks;
		}
		void ShowTasksViewLoad(object sender, EventArgs e)
		{
			foreach(Task task in taskToShow) {
				tasks_listBox.Items.Add(task.ToString());
			}
		}
		void ShareTask_buttonClick(object sender, EventArgs e)
		{
			if(this.tasks_listBox.SelectedIndex != -1)
			{
				RequestParameters requestParameters = new RequestParameters();
				requestParameters.addTask("Task", taskToShow[this.tasks_listBox.SelectedIndex]);
				TaskController.GetInstance().Process(CommandType.ADD_SHARE_TASK, requestParameters);
			}
			else{
				MessageBox.Show("Please select task");
			}
		}
	}
}
