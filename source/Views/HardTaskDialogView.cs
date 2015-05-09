using System;
using System.Windows.Forms;
using Course_project.Controller;
using Course_project.Entity;
using Course_project.Entity.DB;
using Course_project.Utils;

namespace Course_project.Views
{
	public partial class HardTaskDialogView : Form
	{
		private ViewMode formMode;
		private Task taskToEdit;
		public HardTaskDialogView(ViewMode mode, Task task)
		{
			this.formMode = mode;
			
			InitializeComponent();
			fillGroupComboBox();
			if (mode == ViewMode.ADD_MODE) {
				this.start_dateTimePicker.Value = DateTime.Today;
				this.stop_dateTimePicker.Value = DateTime.Today;
				this.Text = "Add task";
			} else if (mode == ViewMode.EDIT_MODE) {
				this.taskToEdit = task;
				this.Text = "Edit task";
				
				this.title_tb.Text = task.Title;
				this.stop_dateTimePicker.Value = TimeUtils.convertUnixTimeToDateTime(task.EndTime);
				this.start_dateTimePicker.Value = TimeUtils.convertUnixTimeToDateTime(task.StartTime);
				this.group_comboBox.SelectedIndex = this.group_comboBox.FindString(task.Group);
			}
		}
		
		void Submit_buttonClick(object sender, EventArgs e)
		{
			if (formMode == ViewMode.ADD_MODE) {
				RequestParameters hardTaskParameters = new RequestParameters();
			
				hardTaskParameters.AddParameter<String>("Title", this.title_tb.Text);
				hardTaskParameters.AddParameter<String>("Group", this.group_comboBox.Text);
				hardTaskParameters.AddParameter<DateTime>("StartTime", this.start_dateTimePicker.Value);
				hardTaskParameters.AddParameter<DateTime>("StopTime", this.stop_dateTimePicker.Value);
			
				bool result = (bool)TaskController.GetInstance().Process(CommandType.ADD_PRIVATE_TASK, hardTaskParameters);
			
				if (result) {
					MessageBox.Show("Task added");
					Close();
				}
			} else if (formMode == ViewMode.EDIT_MODE) {
				taskToEdit.Title = this.title_tb.Text;
				taskToEdit.Group = this.group_comboBox.Text;
				taskToEdit.StartTime = TimeUtils.DateTimeToUnixTime(this.start_dateTimePicker.Value);
				taskToEdit.EndTime = TimeUtils.DateTimeToUnixTime(this.stop_dateTimePicker.Value);
				
				MessageBox.Show("Task edit");
				Close();
			}
		}
		
		void fillGroupComboBox()
		{
			this.group_comboBox.Items.Add("");
			UserGroups userGroups = (UserGroups)TaskController.GetInstance().Process(CommandType.GET_USER_GROUPS, null);	
			foreach (String group in userGroups.Groups)
			{
				this.group_comboBox.Items.Add(group);
			}
		}
		
		void Start_dateTimePickerValueChanged(object sender, EventArgs e)
		{
			if (((DateTimePicker)sender).Value > this.stop_dateTimePicker.Value) {
				this.stop_dateTimePicker.Value = ((DateTimePicker)sender).Value.AddMinutes(1);
			}
		}
		
		void Stop_dateTimePickerValueChanged(object sender, EventArgs e)
		{
			if (((DateTimePicker)sender).Value < this.start_dateTimePicker.Value) {
				this.start_dateTimePicker.Value = ((DateTimePicker)sender).Value.AddMinutes(-1);
			}
		}
		
	}
}
