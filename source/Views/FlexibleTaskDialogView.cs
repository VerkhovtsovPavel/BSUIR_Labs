using System;
using Course_project.Entity;
using Course_project.Utils;

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
			
			/*RequestParameters flexibleTaskParameters = new RequestParameters();
			
			flexibleTaskParameters.AddParameter<String>("Title", this.title_tb.Text);
			flexibleTaskParameters.AddParameter<String>("Group", this.group_comboBox.Text);
			
			flexibleTaskParameters.AddParameter<DateTime>("StartTime", this.start_dateTimePicker.Value);
			flexibleTaskParameters.AddParameter<DateTime>("StopTime", this.stop_dateTimePicker.Value);
			
			
			
			bool result = (bool)TaskController.GetInstance().Process(CommandType.ADD_PRIVATE_TASK, flexibleTaskParameters);
			
			if(result){
				MessageBox.Show("Task added");
				Close();
			}*/
		}
	}
}
