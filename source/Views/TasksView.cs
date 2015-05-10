using System;
using System.Collections.Generic;
using System.Windows.Forms;
using Course_project.Controller;
using Course_project.Entity;
using Course_project.Utils;

namespace Course_project.Views
{
	//TODO Change listbox (long title)
	//TODO Maybe rename to TasksView
	//TODO First click on current date don't work. (Need select other date)
	public partial class TasksView : MainView
	{
		private List<Task> taskToShow;
		public TasksView(DateTime beginInterval, DateTime endInterval)
		{
			InitializeComponent();
			this.fileToolStripMenuItem.Enabled = false;
			
			this.start_dateTimePicker.Value = beginInterval;
			this.stop_dateTimePicker.Value = endInterval;
			
			RequestParameters parameters = new RequestParameters();
			parameters.AddParameter<DateTime>("StartTime", beginInterval);
			parameters.AddParameter<DateTime>("EndTime", endInterval);
			
			
			taskToShow = (List<Task>)TaskController.GetInstance().Process(CommandType.GET_TASKS_FROM_RANGE, parameters);
		}
		
		
		void changeRange(object sender, System.EventArgs e)
		{
			if (this.start_dateTimePicker.Value > this.stop_dateTimePicker.Value)
			{
				this.stop_dateTimePicker.Value = this.start_dateTimePicker.Value.AddMinutes(1);
			}
			
			RequestParameters parameters = new RequestParameters();
			parameters.AddParameter<DateTime>("StartTime", this.start_dateTimePicker.Value);
			parameters.AddParameter<DateTime>("EndTime", this.stop_dateTimePicker.Value);
			
			taskToShow = (List<Task>)TaskController.GetInstance().Process(CommandType.GET_TASKS_FROM_RANGE, parameters);
			
			PrintTasks();
		}
		
		void ShowTasksViewLoad(object sender, EventArgs e)
		{
			PrintTasks();
		}
		
		//TODO Change listbox to datagridView 
		private void PrintTasks(){
			this.tasks_listBox.Items.Clear();
			
			tasksGridView.DataSource = taskToShow;
			
			foreach (Task task in taskToShow) {
				tasks_listBox.Items.Add(task.ToString());
				
			}
		}
		
		//TODO Check work
		void ShareTask_buttonClick(object sender, EventArgs e)
		{
			if (this.tasks_listBox.SelectedIndex != -1) {
				RequestParameters requestParameters = new RequestParameters();
				requestParameters.AddParameter<Task>("Task", taskToShow[this.tasks_listBox.SelectedIndex]);
				TaskController.GetInstance().Process(CommandType.ADD_SHARE_TASK, requestParameters);
				
				MessageBox.Show("Task shared");
			} else {
				MessageBox.Show("Please select task");
			}
		}
		//TODO Maybe use clone task
		void EditTask_buttonClick(object sender, EventArgs e)
		{
			int selectedElement = this.tasks_listBox.SelectedIndex;
			if (selectedElement != -1) {
				HardTaskDialogView hardTaskDialogView = new HardTaskDialogView(ViewMode.EDIT_MODE, this.taskToShow[selectedElement]);
				hardTaskDialogView.ShowDialog();
				
				RequestParameters requestParameters = new RequestParameters();
				requestParameters.AddParameter<Task>("Task", this.taskToShow[selectedElement]);
				
				if ((bool)TaskController.GetInstance().Process(CommandType.UPDATE_TASK, requestParameters)) {
					tasks_listBox.Items.RemoveAt(selectedElement);
					tasks_listBox.Items.Insert(selectedElement, this.taskToShow[selectedElement]);
				} else {
					MessageBox.Show("Please select own task");
				}
			} else {
				MessageBox.Show("Please select task");
			}
		}
		void DeleteTask_buttonClick(object sender, EventArgs e)
		{
			int selectedElement = this.tasks_listBox.SelectedIndex;
			if (selectedElement != -1) {				
				RequestParameters requestParameters = new RequestParameters();
				requestParameters.AddParameter<Task>("Task", this.taskToShow[selectedElement]);
				
				TaskController.GetInstance().Process(CommandType.REMOVE_TASK, requestParameters);
				
				tasks_listBox.Items.RemoveAt(selectedElement);
				taskToShow.RemoveAt(selectedElement);
			} else {
				MessageBox.Show("Please select task");
			}
		}
	}
}
