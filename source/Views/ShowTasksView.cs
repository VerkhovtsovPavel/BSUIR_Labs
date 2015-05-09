using System;
using System.Collections.Generic;
using System.Windows.Forms;
using Course_project.Controller;
using Course_project.Entity;
using Course_project.Utils;

namespace Course_project.Views
{
	//TODO Add field to change interval
	//TODO Change listbox (long title)
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
			//TODO First click on current date don't work. (Need select other date)
			foreach(Task task in taskToShow) {
				tasks_listBox.Items.Add(task.ToString());
			}
		}
		//TODO Check work
		void ShareTask_buttonClick(object sender, EventArgs e)
		{
			if(this.tasks_listBox.SelectedIndex != -1)
			{
				RequestParameters requestParameters = new RequestParameters();
				requestParameters.AddParameter<Task>("Task", taskToShow[this.tasks_listBox.SelectedIndex]);
				TaskController.GetInstance().Process(CommandType.ADD_SHARE_TASK, requestParameters);
				
				MessageBox.Show("Task shared");
			}
			else{
				MessageBox.Show("Please select task");
			}
		}
		void EditTask_buttonClick(object sender, EventArgs e)
		{
			int selectedElement = this.tasks_listBox.SelectedIndex;
			if(selectedElement !=-1)
			{
				HardTaskDialogView hardTaskDialogView = new HardTaskDialogView(ViewMode.EDIT_MODE, this.taskToShow[selectedElement]);
				hardTaskDialogView.ShowDialog();
				
				RequestParameters requestParameters = new RequestParameters();
				requestParameters.AddParameter<Task>("Task", this.taskToShow[selectedElement]);
				
				TaskController.GetInstance().Process(CommandType.UPDATE_TASK, requestParameters);
				
				tasks_listBox.Items.RemoveAt(selectedElement);
				tasks_listBox.Items.Insert(selectedElement, this.taskToShow[selectedElement]);
			}
			else
			{
				MessageBox.Show("Please select task");
			}
		}
		void DeleteTask_buttonClick(object sender, EventArgs e)
		{
			int selectedElement = this.tasks_listBox.SelectedIndex;
			if(selectedElement !=-1)
			{				
				RequestParameters requestParameters = new RequestParameters();
				requestParameters.AddParameter<Task>("Task", this.taskToShow[selectedElement]);
				
				TaskController.GetInstance().Process(CommandType.REMOVE_TASK, requestParameters);
				
				tasks_listBox.Items.RemoveAt(selectedElement);
				taskToShow.RemoveAt(selectedElement);
			}
			else
			{
				MessageBox.Show("Please select task");
			}
		}
	}
}
