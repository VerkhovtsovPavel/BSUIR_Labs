using System;
using System.Collections.Generic;
using System.Windows;
using Course_project.Controller;
using Course_project.Entity;
using Course_project.Storage;
using Course_project.Utils;
using Course_project.Views;

namespace Course_project.Views
{
	public partial class FlexibleTaskDialogView : HardTaskDialogView
	{
		private Dictionary<String, FlexibleTask> permissibleTasks;
		public FlexibleTaskDialogView(ViewMode mode, FlexibleTask task)
			: base(mode, task)
		{
			InitializeComponent();
			if (mode == ViewMode.ADD_MODE) {
			
				this.permissibleTasks = FlexibleTasksStorage.getInstance().getPermissibleTasks(null);
			
			} else if (mode == ViewMode.EDIT_MODE) {
				this.Text = "Edit flexible tasks";
				this.permissibleTasks = FlexibleTasksStorage.getInstance().getPermissibleTasks(task);
		
				FlexibleTask flexibleTaskToEdit = (FlexibleTask)taskToEdit;
				
				this.requestedTime_numericUpDown.Value = flexibleTaskToEdit.RequiredTime;
				this.maxPatrs_numericUpDown.Value = flexibleTaskToEdit.MaxParts;
				this.numericUpDown1.Value = flexibleTaskToEdit.MinTimeOfOnePart;
			
				List<FlexibleTask> dependentTasksList = flexibleTaskToEdit.DependedTasks;
			
				foreach (FlexibleTask item in dependentTasksList) {
					dependentTasks.Items.Add(item.Title);
				}
			}
		}
		
		protected override void Submit_buttonClick(object sender, EventArgs e)
		{
			if (formMode == ViewMode.ADD_MODE) {
				RequestParameters flexibleTaskParameters = new RequestParameters();
			
				flexibleTaskParameters.AddParameter<String>("Title", this.title_tb.Text);
				flexibleTaskParameters.AddParameter<String>("Group", this.group_comboBox.Text);
			
				flexibleTaskParameters.AddParameter<DateTime>("StartTime", this.start_dateTimePicker.Value);
				flexibleTaskParameters.AddParameter<DateTime>("StopTime", this.stop_dateTimePicker.Value);
				
				flexibleTaskParameters.AddParameter<decimal>("RequestedTime", this.requestedTime_numericUpDown.Value);
			
				flexibleTaskParameters.AddParameter<decimal>("MaxParts", this.maxPatrs_numericUpDown.Value);
				flexibleTaskParameters.AddParameter<decimal>("MinTimeFromPart", this.numericUpDown1.Value);
				
				List<FlexibleTask> dependentTasks = new List<FlexibleTask>();
			
				FlexibleTask outFlexibleTasks;
				foreach (String item in this.dependentTasks.Items) {
					permissibleTasks.TryGetValue(item, out outFlexibleTasks);
					dependentTasks.Add(outFlexibleTasks);
				}
			
				flexibleTaskParameters.AddParameter<List<FlexibleTask>>("DependentTasks", dependentTasks);
			
				bool result = (bool)TaskController.GetInstance().Process(CommandType.ADD_FLEXIBLE_TASK_IN_STORE, flexibleTaskParameters);
			
				if (result) {
					MessageBox.Show("Task added");
					Close();
				}
			}else if (formMode == ViewMode.EDIT_MODE)
			{
				FlexibleTask flexibleTaskToEdit = (FlexibleTask) taskToEdit;
				
				taskToEdit.Title = this.title_tb.Text;
				taskToEdit.Group = this.group_comboBox.Text;
				taskToEdit.StartTime = TimeUtils.DateTimeToUnixTime(this.start_dateTimePicker.Value);
				taskToEdit.EndTime = TimeUtils.DateTimeToUnixTime(this.stop_dateTimePicker.Value);
				
				flexibleTaskToEdit.RequiredTime = (int)this.requestedTime_numericUpDown.Value;
				flexibleTaskToEdit.MaxParts = (int)this.maxPatrs_numericUpDown.Value;
				flexibleTaskToEdit.MinTimeOfOnePart = (int)this.numericUpDown1.Value;
				
				flexibleTaskToEdit.DependedTasks.Clear();
				
				FlexibleTask outFlexibleTasks;
				foreach (String item in this.dependentTasks.Items) {
					permissibleTasks.TryGetValue(item, out outFlexibleTasks);
					flexibleTaskToEdit.DependedTasks.Add(outFlexibleTasks);
				}
				
				FlexibleTasksStorage.getInstance().updateTasksDependents();
			}
		}
		
		void AddTask_Click(object sender, EventArgs e)
		{
			FlexibleTaskView addDependentTaskView = new FlexibleTaskView(permissibleTasks, dependentTasks, ViewMode.ADD_MODE);
			addDependentTaskView.ShowDialog();
		}
		
		void RemoveTask_Click(object sender, EventArgs e)
		{
			int selectElement = this.dependentTasks.SelectedIndex;
			if (selectElement != -1) {
				this.dependentTasks.Items.RemoveAt(selectElement);
				

			} else {
				MessageBox.Show("Please select task");
			}
		}
	}
}
