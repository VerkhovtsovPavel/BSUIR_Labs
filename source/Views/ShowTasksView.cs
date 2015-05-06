/*
 * Created by SharpDevelop.
 * User: Администратор
 * Date: 25.04.2015
 * Time: 13:05
 */
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Windows.Forms;
using Course_project.Entity;

namespace Course_project.Views
{
	/// <summary>
	/// Description of ShowTasksView.
	/// </summary>
	public partial class ShowTasksView : MainView
	{
		private List<Task> taskToShow;
		public ShowTasksView(List<Task> tasks)
		{
			InitializeComponent();
			this.fileToolStripMenuItem.Enabled = false;
			taskToShow = tasks;
		}
		void ShowTasksViewLoad(object sender, EventArgs e)
		{
			foreach(Task task in taskToShow) {
				tasks_listBox.Items.Add(task.ToString());
			}
		}
		void ShareTask_buttonClick(object sender, EventArgs e)
		{
			//TODO Check value in SelectedIndex field if task don't selected
			if(this.tasks_listBox.SelectedIndex != -1)
			{
			}
		}
	}
}
