namespace Course_project.Views
{
	using System;
	using System.Windows.Forms;

	public partial class CalendarView : MainView
	{
		public CalendarView()
		{
			this.InitializeComponent();
			this.DisableFileMenu();
		}
		
		private void MonthCalendarDateSelected(object sender, DateRangeEventArgs e)
		{
			DateTime begin = ((MonthCalendar) sender).SelectionStart;
			
			TasksView showTasksView = new TasksView(begin);
			showTasksView.Show();
		}
		
		private void CalendarViewFormClosed(object sender, FormClosedEventArgs e)
		{
			Application.Exit();
		}
	}
}
