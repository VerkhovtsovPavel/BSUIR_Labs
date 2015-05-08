using System;
using System.Collections.Generic;
using Course_project.Controller;

using System.Windows.Forms;
using Course_project.Entity;
using Course_project.Utils;

namespace Course_project.Views
{
	public partial class CalendarView : MainView
	{

		public CalendarView()
		{
			InitializeComponent();
			this.fileToolStripMenuItem.Enabled = false;

		}
		void MonthCalendarDateSelected(object sender, DateRangeEventArgs e)
		{
			RequestParameters parameters = new RequestParameters();
			parameters.addDateTime("StartTime", ((MonthCalendar) sender).SelectionStart);
			parameters.addDateTime("EndTime", ((MonthCalendar) sender).SelectionEnd);
			
			List<Task> tasks =	(List<Task>)TaskController.GetInstance().Process(CommandType.GET_TASKS_FROM_RANGE, parameters);
			ShowTasksView showTasksView = new ShowTasksView(tasks);
			showTasksView.Show();
		}
	}
}
