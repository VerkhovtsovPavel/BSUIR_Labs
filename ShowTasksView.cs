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

namespace Course_project
{
	/// <summary>
	/// Description of ShowTasksView.
	/// </summary>
	public partial class ShowTasksView : Form
	{
		private List<Task> taskToShow;
		public ShowTasksView(List<Task> tasks)
		{
			InitializeComponent();
			taskToShow = tasks;
		}
		void ShowTasksViewLoad(object sender, EventArgs e)
		{
			foreach(Task task in taskToShow) {
				tasks_listBox.Items.Add(task.ToString());
			}
		}
	}
}
