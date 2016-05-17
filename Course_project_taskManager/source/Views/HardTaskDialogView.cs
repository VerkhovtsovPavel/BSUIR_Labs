namespace Course_project.Views
{
	using System;
	using System.Windows.Forms;
	using Course_project.Controller;
	using Course_project.Entity;
	using Course_project.Entity.DB;
	using Course_project.Utils;

	public partial class HardTaskDialogView : Form
	{
		private ViewMode formMode;
		
		private Task taskToEdit;
		
		public HardTaskDialogView(ViewMode mode, Task task)
		{
			this.formMode = mode;
			
			this.InitializeComponent();
			this.FillGroupComboBox();
			
			if (mode == ViewMode.ADD_MODE)
			{
				this.start_dateTimePicker.Value = DateTime.Today;
				this.stop_dateTimePicker.Value = DateTime.Today.AddMinutes(1);
				this.Text = "Add task";
			}
			else if (mode == ViewMode.EDIT_MODE)
			{
				this.Text = "Edit task";
				this.taskToEdit = task;
				this.title_tb.Text = task.Title;
				this.stop_dateTimePicker.Value = TimeUtils.ConvertUnixTimeToDateTime(task.EndTime);
				this.start_dateTimePicker.Value = TimeUtils.ConvertUnixTimeToDateTime(task.StartTime);
				this.group_comboBox.SelectedIndex = this.group_comboBox.FindString(task.Group);
			}
		}

		public Task GetTaskToEdit()
		{
			return this.taskToEdit;
		}

		public ViewMode GetFormMode()
		{
			return this.formMode;
		}
		
		protected virtual void Submit_buttonClick(object sender, EventArgs e)
		{
			if (!this.CheckEmptyFields())
			{
				this.submit_button.DialogResult = DialogResult.OK;
				if (this.formMode == ViewMode.ADD_MODE)
				{
					RequestParameters hardTaskParameters = new RequestParameters();
			
					hardTaskParameters.AddParameter<string>("Title", this.title_tb.Text);
					hardTaskParameters.AddParameter<string>("Group", this.group_comboBox.Text);
					hardTaskParameters.AddParameter<DateTime>("StartTime", this.start_dateTimePicker.Value);
					hardTaskParameters.AddParameter<DateTime>("StopTime", this.stop_dateTimePicker.Value);
			
					bool result = (bool)TaskController.GetInstance().Process(CommandType.ADD_PRIVATE_TASK, hardTaskParameters);
			
					if (result)
					{
						MessageBox.Show("Task added");
					}
				}
				else if (this.formMode == ViewMode.EDIT_MODE)
				{
					this.taskToEdit.Title = this.title_tb.Text;
					this.taskToEdit.Group = this.group_comboBox.Text;
					this.taskToEdit.StartTime = TimeUtils.DateTimeToUnixTime(this.start_dateTimePicker.Value);
					this.taskToEdit.EndTime = TimeUtils.DateTimeToUnixTime(this.stop_dateTimePicker.Value);
					this.Close();
				}
			}
			else
			{
				MessageBox.Show("Please complete all required fields");	
			}
		}
	
		protected virtual bool CheckEmptyFields()
		{
			return this.title_tb.Text.Equals(string.Empty);
		}
		
		private void FillGroupComboBox()
		{
			this.group_comboBox.Items.Add(string.Empty);
			UserGroups userGroups = (UserGroups)TaskController.GetInstance().Process(CommandType.GET_USER_GROUPS, null);	
			foreach (string group in userGroups.Groups)
			{
				this.group_comboBox.Items.Add(group);
			}
		}
		
		private void CorrectDateTimePickerValueChanged(object sender, EventArgs e)
		{
			if (this.start_dateTimePicker.Value > this.stop_dateTimePicker.Value)
			{
				this.stop_dateTimePicker.Value = this.start_dateTimePicker.Value.AddMinutes(1);
			}
		}
	}
}
