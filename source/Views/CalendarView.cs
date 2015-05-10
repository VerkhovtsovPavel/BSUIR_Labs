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
			
			DateTime begin = ((MonthCalendar) sender).SelectionStart;
			DateTime end = ((MonthCalendar) sender).SelectionEnd;
			
			TasksView showTasksView = new TasksView(begin, end);
			showTasksView.Show();
		}
		void CalendarViewLoad(object sender, EventArgs e)
		{
	
		}
	}
}
