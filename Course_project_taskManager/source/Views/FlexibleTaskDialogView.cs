namespace Course_project.Views
{
	using System;
	using System.Collections.Generic;
	using System.Windows;
	using Course_project.Controller;
	using Course_project.Entity;
	using Course_project.Storage;
	using Course_project.Utils;
	using Course_project.Views;

	public partial class FlexibleTaskDialogView : HardTaskDialogView
	{
		private Dictionary<string, FlexibleTask> permissibleTasks;
		
		public FlexibleTaskDialogView(ViewMode mode, FlexibleTask task)
			: base(mode, task)
		{
			this.InitializeComponent();
			if (mode == ViewMode.ADD_MODE)
			{
				this.permissibleTasks = FlexibleTasksStorage.GetInstance().GetPermissibleTasks(null);
			}
			else if (mode == ViewMode.EDIT_MODE)
			{
				this.Text = "Edit flexible tasks";
				this.permissibleTasks = FlexibleTasksStorage.GetInstance().GetPermissibleTasks(task);
		
				FlexibleTask flexibleTaskToEdit = (FlexibleTask)this.GetTaskToEdit();
				
				this.requestedTime_numericUpDown.Value = flexibleTaskToEdit.RequiredTime / 60;
				this.maxPatrs_numericUpDown.Value = flexibleTaskToEdit.MaxParts;
				this.numericUpDown1.Value = flexibleTaskToEdit.MinTimeOfOnePart;
			
				List<FlexibleTask> dependentTasksList = flexibleTaskToEdit.DependedTasks;
			
				foreach (FlexibleTask item in dependentTasksList)
				{
					this.dependentTasks.Items.Add(item.Title);
				}
			}
		}
		
		protected override void Submit_buttonClick(object sender, EventArgs e)
		{
			if (!this.CheckEmptyFields())
			{
				this.submit_button.DialogResult = System.Windows.Forms.DialogResult.OK;
				if (this.GetFormMode() == ViewMode.ADD_MODE)
				{
					RequestParameters flexibleTaskParameters = new RequestParameters();
			
					flexibleTaskParameters.AddParameter<string>("Title", this.title_tb.Text);
					flexibleTaskParameters.AddParameter<string>("Group", this.group_comboBox.Text);
			
					flexibleTaskParameters.AddParameter<DateTime>("StartTime", this.start_dateTimePicker.Value);
					flexibleTaskParameters.AddParameter<DateTime>("StopTime", this.stop_dateTimePicker.Value);
				
					flexibleTaskParameters.AddParameter<decimal>("MaxParts", this.maxPatrs_numericUpDown.Value);
					flexibleTaskParameters.AddParameter<decimal>("MinTimeFromPart", this.numericUpDown1.Value);
					flexibleTaskParameters.AddParameter<decimal>("RequestedTime", this.requestedTime_numericUpDown.Value);
					
					this.maxPatrs_numericUpDown.Value =  this.maxPatrs_numericUpDown.Value;
					this.numericUpDown1.Value = this.numericUpDown1.Value;
					this.requestedTime_numericUpDown.Value = this.requestedTime_numericUpDown.Value;
				
					List<FlexibleTask> dependentTasksList = new List<FlexibleTask>();
			
					FlexibleTask outFlexibleTasks;
					foreach (string item in this.dependentTasks.Items)
					{
						this.permissibleTasks.TryGetValue(item, out outFlexibleTasks);
						dependentTasksList.Add(outFlexibleTasks);
					}
			
					flexibleTaskParameters.AddParameter<List<FlexibleTask>>("DependentTasks", dependentTasksList);
			
					bool result = (bool)TaskController.GetInstance().Process(CommandType.ADD_FLEXIBLE_TASK_IN_STORE, flexibleTaskParameters);
			
					if (result)
					{
						MessageBox.Show("Task added");
					}
				}
				else if (this.GetFormMode() == ViewMode.EDIT_MODE)
				{
					FlexibleTask flexibleTaskToEdit = (FlexibleTask)this.GetTaskToEdit();
				
					flexibleTaskToEdit.Title = this.title_tb.Text;
					flexibleTaskToEdit.Group = this.group_comboBox.Text;
					flexibleTaskToEdit.StartTime = TimeUtils.DateTimeToUnixTime(this.start_dateTimePicker.Value);
					flexibleTaskToEdit.EndTime = TimeUtils.DateTimeToUnixTime(this.stop_dateTimePicker.Value);
				
					flexibleTaskToEdit.RequiredTime = (int)this.requestedTime_numericUpDown.Value * 60;
					flexibleTaskToEdit.MaxParts = (int)this.maxPatrs_numericUpDown.Value;
					flexibleTaskToEdit.MinTimeOfOnePart = (int)this.numericUpDown1.Value;
				
					flexibleTaskToEdit.DependedTasks.Clear();
				
					FlexibleTask outFlexibleTasks;
					foreach (string item in this.dependentTasks.Items)
					{
						this.permissibleTasks.TryGetValue(item, out outFlexibleTasks);
						flexibleTaskToEdit.DependedTasks.Add(outFlexibleTasks);
					}
				
					FlexibleTasksStorage.GetInstance().UpdateTasksDependents();
				}
			}
			else 
			{
				MessageBox.Show("Please complete all required fields");
			}
		}
		
		private void AddTask_Click(object sender, EventArgs e)
		{
			FlexibleTaskView addDependentTaskView = new FlexibleTaskView(this.permissibleTasks, this.dependentTasks, ViewMode.ADD_MODE);
			addDependentTaskView.ShowDialog();
		}
		
		private void RemoveTask_Click(object sender, EventArgs e)
		{
			int selectElement = this.dependentTasks.SelectedIndex;
			if (selectElement != -1)
			{
				this.dependentTasks.Items.RemoveAt(selectElement);
			}
			else
			{
				MessageBox.Show("Please select task");
			}
		}
	}
}
