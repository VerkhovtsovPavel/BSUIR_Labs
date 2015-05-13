using System;
using System.Collections.Generic;
using System.Windows.Forms;
using Course_project.Entity;
using Course_project.Storage;

namespace Course_project.Views
{
	/// <summary>
	/// Description of AddDependentTaskView.
	/// </summary>
	public partial class FlexibleTaskView : Form
	{
		private Dictionary<String, FlexibleTask> tasks;
		private ListBox dependentTaskListBox;
		private ViewMode mode;
		public FlexibleTaskView(Dictionary<String, FlexibleTask> tasks, ListBox listBox, ViewMode mode)
		{
			
			InitializeComponent();
			this.dependentTaskListBox = listBox;
			this.mode = mode;
			
			if (mode == ViewMode.ADD_MODE) {
				this.tasks = tasks;
				FillComboBoxWithExclude();
			} else if (mode == ViewMode.EDIT_MODE) {
				this.tasks = FlexibleTasksStorage.getInstance().getPermissibleTasks(null);
				FullFillComboBox();
				
			}	
		}
		
		
		void SubmitClick(object sender, EventArgs e)
		{
			if (this.taskComboBox.Text != "") {
				if (mode == ViewMode.ADD_MODE) {
					dependentTaskListBox.Items.Add(this.taskComboBox.Text);
					Close();
				} else if (mode == ViewMode.EDIT_MODE) {
					FlexibleTask outTask;
					tasks.TryGetValue(this.taskComboBox.Text, out outTask);
					Hide();
					new FlexibleTaskDialogView(mode, outTask).ShowDialog();
					Show();
					
				}
			} else {
				MessageBox.Show("Please select task");
			}
		}

		void FillComboBoxWithExclude()
		{
			foreach (var title in tasks.Keys) {
				if (!dependentTaskListBox.Items.Contains(title)) {
					this.taskComboBox.Items.Add(title);
				}
			}
		}
		
		void FullFillComboBox()
		{
			foreach (var title in tasks.Keys) {
				this.taskComboBox.Items.Add(title);
			}
		}
	}
}

