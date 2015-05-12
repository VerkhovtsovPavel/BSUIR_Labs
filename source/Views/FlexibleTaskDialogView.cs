using System;
using System.Collections.Generic;
using System.Windows;
using Course_project.Controller;
using Course_project.Entity;
using Course_project.Storage;
using Course_project.Utils;
using Course_project.Views;

//TODO Implement edit mode 
namespace Course_project.Views
{
	public partial class FlexibleTaskDialogView : HardTaskDialogView
	{
		private Dictionary<String, FlexibleTask> permissibleTasks;
		public FlexibleTaskDialogView(ViewMode mode, FlexibleTask task)
			: base(mode, task)
		{
			InitializeComponent();
			this.permissibleTasks = FlexibleTasksStorage.getInstance().getPermissibleTasks(null);
		}
		
		protected override void Submit_buttonClick(object sender, EventArgs e)
		{
			if (formMode == ViewMode.ADD_MODE) {
				RequestParameters flexibleTaskParameters = new RequestParameters();
			
				flexibleTaskParameters.AddParameter<String>("Title", this.title_tb.Text);
				flexibleTaskParameters.AddParameter<String>("Group", this.group_comboBox.Text);
			
				flexibleTaskParameters.AddParameter<DateTime>("StartTime", this.start_dateTimePicker.Value);
				flexibleTaskParameters.AddParameter<DateTime>("StopTime", this.stop_dateTimePicker.Value);
			
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
			}
		}
		
		void AddTask_Click(object sender, EventArgs e)
		{
			List<String> taskTitles = new List<string>();
			foreach (var title in permissibleTasks.Keys) {
				if(!this.dependentTasks.Items.Contains(title)){
					taskTitles.Add(title);
				 }
			}
			
			AddDependentTaskView addDependentTaskView = new AddDependentTaskView(taskTitles, dependentTasks);
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
