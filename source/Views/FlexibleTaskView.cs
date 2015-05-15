namespace Course_project.Views
{
	using System;
	using System.Collections.Generic;
	using System.Windows.Forms;
	using Course_project.Entity;
	using Course_project.Storage;

	public partial class FlexibleTaskView : Form
	{
		private Dictionary<string, FlexibleTask> tasks;
		private ListBox dependentTaskListBox;
		private ViewMode mode;
		
		public FlexibleTaskView(Dictionary<string, FlexibleTask> tasks, ListBox listBox, ViewMode mode)
		{
			this.InitializeComponent();
			this.dependentTaskListBox = listBox;
			this.mode = mode;
			
			if (mode == ViewMode.ADD_MODE)
			{
				this.tasks = tasks;
				this.FillComboBoxWithExclude();
			}
			else if (mode == ViewMode.EDIT_MODE)
			{
				this.tasks = FlexibleTasksStorage.GetInstance().GetPermissibleTasks(null);
				this.FullFillComboBox();
				this.Text = "Edit flexible task";
			}	
		}
		
		private void SubmitClick(object sender, EventArgs e)
		{
			if (this.taskComboBox.Text != string.Empty)
			{
				if (this.mode == ViewMode.ADD_MODE)
				{
					this.dependentTaskListBox.Items.Add(this.taskComboBox.Text);
					this.Close();
				}
				else if (this.mode == ViewMode.EDIT_MODE)
				{
					FlexibleTask outTask;
					this.tasks.TryGetValue(this.taskComboBox.Text, out outTask);
					this.Hide();
					new FlexibleTaskDialogView(this.mode, outTask).ShowDialog();
					this.Show();
				}
			}
			else 
			{
				MessageBox.Show("Please select task");
			}
		}

		private void FillComboBoxWithExclude()
		{
			foreach (var title in this.tasks.Keys)
			{
				if (!this.dependentTaskListBox.Items.Contains(title))
				{
					this.taskComboBox.Items.Add(title);
				}
			}
		}
		
		private void FullFillComboBox()
		{
			foreach (var title in this.tasks.Keys)
			{
				this.taskComboBox.Items.Add(title);
			}
		}
	}
}