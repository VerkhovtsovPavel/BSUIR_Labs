namespace Course_project.Views
{
	using System;
	using System.Collections.Generic;
	using System.Windows.Forms;
	using Course_project.Controller;
	using Course_project.Entity;
	using Course_project.Utils;
	
	public partial class TasksView : MainView
	{
		private List<Task> taskToShow;
		
		public TasksView(DateTime beginInterval)
		{
			this.InitializeComponent();
			
			this.tasksGridView.ColumnCount = 6;
			this.tasksGridView.Columns[0].Name = "Title";
			this.tasksGridView.Columns[1].Name = "Owner";
			this.tasksGridView.Columns[2].Name = "Group";
			
			this.tasksGridView.Columns[3].Name = "Start Time";
			this.tasksGridView.Columns[3].AutoSizeMode = DataGridViewAutoSizeColumnMode.AllCells;
			this.tasksGridView.Columns[4].Name = "End Time";
			this.tasksGridView.Columns[4].AutoSizeMode = DataGridViewAutoSizeColumnMode.AllCells;
			this.tasksGridView.Columns[5].Name =	"Duration";
			
			this.DisableFileMenu();
			
			this.start_dateTimePicker.Value = beginInterval.Date;
			this.stop_dateTimePicker.Value = beginInterval.Date.AddDays(1).AddSeconds(-1);
			
			RequestParameters parameters = new RequestParameters();
			parameters.AddParameter<DateTime>("StartTime", beginInterval.Date);
			parameters.AddParameter<DateTime>("EndTime", beginInterval.Date.AddDays(1).AddSeconds(-1));
			
			this.taskToShow = (List<Task>)TaskController.GetInstance().Process(CommandType.GET_TASKS_FROM_RANGE, parameters);
		}
		
		private void ChangeRange(object sender, System.EventArgs e)
		{
			if (this.start_dateTimePicker.Value > this.stop_dateTimePicker.Value)
			{
				this.stop_dateTimePicker.Value = this.start_dateTimePicker.Value.AddMinutes(1);
			}
			
			RequestParameters parameters = new RequestParameters();
			parameters.AddParameter<DateTime>("StartTime", this.start_dateTimePicker.Value);
			parameters.AddParameter<DateTime>("EndTime", this.stop_dateTimePicker.Value);
			
			this.taskToShow = (List<Task>)TaskController.GetInstance().Process(CommandType.GET_TASKS_FROM_RANGE, parameters);
			
			this.PrintTasks();
		}
		
		private void ShowTasksViewLoad(object sender, EventArgs e)
		{	 
			this.PrintTasks();
		}
		
		private void PrintTasks()
		{
			this.tasksGridView.Rows.Clear();

			foreach (Task task in this.taskToShow)
			{
				string[] row = task.ToStringArray();
				this.tasksGridView.Rows.Add(row);
			}
		}
		
		private void ShareTask_buttonClick(object sender, EventArgs e)
		{
			int rowIndex = this.tasksGridView.CurrentCell.RowIndex;
			RequestParameters requestParameters = new RequestParameters();
			requestParameters.AddParameter<Task>("Task", this.taskToShow[rowIndex]);
			if ((bool)TaskController.GetInstance().Process(CommandType.ADD_SHARE_TASK, requestParameters))
			{
				MessageBox.Show("Task shared");
			} 
			else
			{
				MessageBox.Show("Task already shared");
			}
		}

		private void EditTask_buttonClick(object sender, EventArgs e)
		{
			int selectedElement = this.tasksGridView.CurrentCell.RowIndex;
			
			Task taskClone = (Task)this.taskToShow[selectedElement].Clone();
			
			HardTaskDialogView hardTaskDialogView = new HardTaskDialogView(ViewMode.EDIT_MODE, taskClone);
			
			if (hardTaskDialogView.ShowDialog() != DialogResult.OK)
			{
				return;
			}
				
			RequestParameters requestParameters = new RequestParameters();
			requestParameters.AddParameter<Task>("Task", taskClone);
				
			if ((bool)TaskController.GetInstance().Process(CommandType.UPDATE_TASK, requestParameters)) 
			{
				MessageBox.Show("Task changed");
				this.taskToShow[selectedElement] = taskClone;
				this.PrintTasks();
			} 
			else 
			{
				MessageBox.Show("Please select own task");
			}
		}
		
		private void DeleteTask_buttonClick(object sender, EventArgs e)
		{
			int selectedElement = this.tasksGridView.CurrentCell.RowIndex;			
			RequestParameters requestParameters = new RequestParameters();
			requestParameters.AddParameter<Task>("Task", this.taskToShow[selectedElement]);
				
			if ((bool)TaskController.GetInstance().Process(CommandType.REMOVE_TASK, requestParameters))
			{
				this.taskToShow.RemoveAt(selectedElement);
				this.tasksGridView.Rows.RemoveAt(selectedElement);
				MessageBox.Show("Task removed");
			}
			else
			{
				MessageBox.Show("Please select own task");
			}
		}
	}
}
