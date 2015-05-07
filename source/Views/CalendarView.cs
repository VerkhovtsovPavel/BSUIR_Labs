/*
 * Created by SharpDevelop.
 * User: Администратор
 * Date: 25.04.2015
 * Time: 12:14
 */
using System;
using System.Collections.Generic;
using Course_project.Controller;

using System.Windows.Forms;
using Course_project.Entity;
using Course_project.Utils;

namespace Course_project.Views
{
	/// <summary>
	/// Description of ShowTasksView.
	/// </summary>
	public partial class CalendarView : MainView
	{
		private CalendarController calendarController;
		public CalendarView()
		{
			InitializeComponent();
			this.fileToolStripMenuItem.Enabled = false;
			calendarController = new CalendarController();
		}
		void MonthCalendarDateSelected(object sender, DateRangeEventArgs e)
		{
			RequestParameters parameters = new RequestParameters();
			parameters.addDateTime("StartTime", ((MonthCalendar) sender).SelectionStart);
			parameters.addDateTime("EndTime", ((MonthCalendar) sender).SelectionEnd);
			
			List<Task> tasks =	(List<Task>)calendarController.process("showTasks", parameters);
			ShowTasksView showTasksView = new ShowTasksView(tasks);
			showTasksView.Show();
		}
	}
}
