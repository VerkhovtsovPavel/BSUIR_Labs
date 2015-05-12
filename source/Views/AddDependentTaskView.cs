using System;
using System.Collections.Generic;
using System.Windows.Forms;
using Course_project.Entity;

namespace Course_project.Views
{
	/// <summary>
	/// Description of AddDependentTaskView.
	/// </summary>
	public partial class AddDependentTaskView : Form
	{
		private List<String> tasks;
		private ListBox dependentTaskListBox;
		public AddDependentTaskView(List<String> tasksTitles, ListBox listBox)
		{

			InitializeComponent();
			this.tasks = tasksTitles;
			this.dependentTaskListBox = listBox;
			FillComboBox();

		}
		void SubmitClick(object sender, EventArgs e)
		{
			if (this.taskComboBox.Text != "") {
				dependentTaskListBox.Items.Add(this.taskComboBox.Text);
				Close();
			} else {
				MessageBox.Show("Please select task");
			}
		}

		void FillComboBox()
		{
			foreach (var title in tasks) {
				this.taskComboBox.Items.Add(title);
			}
		}
	}
}
