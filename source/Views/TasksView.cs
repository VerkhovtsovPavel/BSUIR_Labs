using System;
using System.Collections.Generic;
using System.Windows.Forms;
using Course_project.Controller;
using Course_project.Entity;
using Course_project.Utils;

namespace Course_project.Views
{
	//TODO First click on current date don't work. (Need select other date)
	public partial class TasksView : MainView
	{
		private List<Task> taskToShow;
		public TasksView(DateTime beginInterval, DateTime endInterval)
		{
			InitializeComponent();
			
			tasksGridView.ColumnCount = 6;
			tasksGridView.Columns[0].Name = "Title";
			tasksGridView.Columns[1].Name = "Owner";
			tasksGridView.Columns[2].Name = "Group";
			
			tasksGridView.Columns[3].Name = "Start Time";
			tasksGridView.Columns[3].AutoSizeMode = DataGridViewAutoSizeColumnMode.AllCells;
			//TODO Check column size 
			tasksGridView.Columns[4].Name = "End Time";
			tasksGridView.Columns[4].AutoSizeMode = DataGridViewAutoSizeColumnMode.AllCells;
			tasksGridView.Columns[5].Name =	"Duration";
			
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
			if (this.start_dateTimePicker.Value > this.stop_dateTimePicker.Value) {
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
		
		private void PrintTasks()
		{
			tasksGridView.Rows.Clear();

			foreach (Task task in taskToShow) {
				string[] row = task.ToStringArray();
				tasksGridView.Rows.Add(row);
			}

		}
		
		
		void ShareTask_buttonClick(object sender, EventArgs e)
		{
			int rowIndex = tasksGridView.CurrentCell.RowIndex;
			RequestParameters requestParameters = new RequestParameters();
			requestParameters.AddParameter<Task>("Task", taskToShow[rowIndex]);
			if ((bool)TaskController.GetInstance().Process(CommandType.ADD_SHARE_TASK, requestParameters)) {
				MessageBox.Show("Task shared");
			} else {
				MessageBox.Show("Task already shared");
			}
		}

		void EditTask_buttonClick(object sender, EventArgs e)
		{
			int selectedElement = tasksGridView.CurrentCell.RowIndex;
			
			Task taskClone = (Task)this.taskToShow[selectedElement].Clone();
			
			HardTaskDialogView hardTaskDialogView = new HardTaskDialogView(ViewMode.EDIT_MODE, taskClone);
			
			if (hardTaskDialogView.ShowDialog() != DialogResult.OK) {
				return;
			}
				
			RequestParameters requestParameters = new RequestParameters();
			requestParameters.AddParameter<Task>("Task", taskClone);
				
			if ((bool)TaskController.GetInstance().Process(CommandType.UPDATE_TASK, requestParameters)) {
				MessageBox.Show("Task changed");
				this.taskToShow[selectedElement] = taskClone;
				PrintTasks();
			} else {
				MessageBox.Show("Please select own task");
			}
		}
		
		void DeleteTask_buttonClick(object sender, EventArgs e)
		{
			int selectedElement = tasksGridView.CurrentCell.RowIndex;			
			RequestParameters requestParameters = new RequestParameters();
			requestParameters.AddParameter<Task>("Task", this.taskToShow[selectedElement]);
				
			if ((bool)TaskController.GetInstance().Process(CommandType.REMOVE_TASK, requestParameters)) {
				taskToShow.RemoveAt(selectedElement);
				tasksGridView.Rows.RemoveAt(selectedElement);
				MessageBox.Show("Task removed");
			} else {
				MessageBox.Show("Please select own task");
			}
		}
	}
}
