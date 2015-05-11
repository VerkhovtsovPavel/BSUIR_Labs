using System;
using System.Collections.Generic;
using System.Windows;
using Course_project.Controller;
using Course_project.Entity;
using Course_project.Utils;
using Course_project.Views;

namespace Course_project.Views
{
	public partial class FlexibleTaskDialogView : HardTaskDialogView
	{
		public FlexibleTaskDialogView(ViewMode mode, FlexibleTask task) : base(mode, task)
		{
			InitializeComponent();
		}
		void Submit_buttonClick(object sender, EventArgs e)
		{
			if(formMode == ViewMode.ADD_MODE){
			RequestParameters flexibleTaskParameters = new RequestParameters();
			
			flexibleTaskParameters.AddParameter<String>("Title", this.title_tb.Text);
			flexibleTaskParameters.AddParameter<String>("Group", this.group_comboBox.Text);
			
			flexibleTaskParameters.AddParameter<DateTime>("StartTime", this.start_dateTimePicker.Value);
			flexibleTaskParameters.AddParameter<DateTime>("StopTime", this.stop_dateTimePicker.Value);
			
			flexibleTaskParameters.AddParameter<decimal>("MaxParts", this.maxPatrs_numericUpDown.Value);
			flexibleTaskParameters.AddParameter<decimal>("MinTimeFromPart", this.numericUpDown1.Value);
			
			List<String> dependentTasks =  new List<String>();
			
			foreach(String item in this.dependentTasks.Items)
				dependentTasks.Add(item);
			
			flexibleTaskParameters.AddParameter<List<String>>("DependentTasks", dependentTasks);
			
			bool result = (bool)TaskController.GetInstance().Process(CommandType.ADD_FLEXIBLE_TASK_IN_STORE, flexibleTaskParameters);
			
			if(result){
				MessageBox.Show("Task added");
				Close();
			}
			}
		}
	}
}
