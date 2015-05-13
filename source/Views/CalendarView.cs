using System;
using System.Windows.Forms;

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
		void CalendarViewFormClosed(object sender, FormClosedEventArgs e)
		{
			//CHECK
			Application.Exit();
		}
	}
}
