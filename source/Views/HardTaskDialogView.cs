using System;
using System.Windows.Forms;
using Course_project.Controller;
using Course_project.Entity.DB;
using Course_project.Utils;

namespace Course_project.Views
{
	public partial class HardTaskDialogView : Form
	{
		public HardTaskDialogView()
		{
			InitializeComponent();
			
			this.start_dateTimePicker.Value = DateTime.Today;
			this.stop_dateTimePicker.Value = DateTime.Today;
			
			fillGroupComboBox();
		}
		
		void Submit_buttonClick(object sender, EventArgs e)
		{
			RequestParameters hardTaskParameters = new RequestParameters();
			
			hardTaskParameters.AddParameter<String>("Title", this.title_tb.Text);
			hardTaskParameters.AddParameter<String>("Group", this.group_comboBox.Text);
			
			hardTaskParameters.AddParameter<DateTime>("StartTime", this.start_dateTimePicker.Value);
			hardTaskParameters.AddParameter<DateTime>("StopTime", this.stop_dateTimePicker.Value);
			
			bool result = (bool)TaskController.GetInstance().Process(CommandType.ADD_PRIVATE_TASK, hardTaskParameters);
			
			if(result){
				MessageBox.Show("Task added");
				Close();
			}
		}
		//TODO Check end time more then start time 
		void fillGroupComboBox()
		{
			this.group_comboBox.Items.Add("");
			UserGroups userGroups = (UserGroups)TaskController.GetInstance().Process(CommandType.GET_USER_GROUPS, null);	
			foreach (String group in userGroups.Groups)
			{
				this.group_comboBox.Items.Add(group);
			}
		}
	}
}
