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

namespace Course_project
{
	/// <summary>
	/// Description of ShowTasksView.
	/// </summary>
	public partial class CalendarView : Form
	{
		private CalendarController calendarController;
		public CalendarView()
		{
			InitializeComponent();
			calendarController = new CalendarController();
		}
		void MonthCalendar1DateChanged(object sender, DateRangeEventArgs e)
		{
			Dictionary<String, object> parameters = new Dictionary<String, object>();
			//TODO Add user info in dictionary
			//TODO Get read date
			parameters.Add("date", sender);
			
			List<Task> tasks =	(List<Task>)calendarController.process("showTask", parameters);
			ShowTasksView showTasksView = new ShowTasksView(tasks);
			showTasksView.Show();
			
		}
	}
}
