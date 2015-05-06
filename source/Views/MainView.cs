using System;
using System.Drawing;
using System.Windows.Forms;
using Course_project.Model;

namespace Course_project.Views
{

	public partial class MainView : Form
	{
		protected MainView(){
			InitializeComponent();
		}
		
		protected void goToRegistrationPage(){
			RegistrationView registrationView = new RegistrationView();
			registrationView.Show();
			Hide();
		}
		
		protected void goToAddFlexibleTaskPage(){
			AddFlexibleTaskView addFlexibleTaskView = new AddFlexibleTaskView();
			addFlexibleTaskView.Show();
		}
		
		protected void goToAddHardTaskPage(){
			AddHardTaskView addHardTaskView = new AddHardTaskView();
			addHardTaskView.Show();
		}
		
		protected void goToCalendarePage(){
		 CalendarView calendarView = new CalendarView();
		 calendarView.Show();
		 Hide();
		}
		
		protected void goToProfilingPage(){
		 ProfilingView profilingView = new ProfilingView();
		 profilingView.Show();
		}

		private void exitWithApplication(){
			Close();
		}
		
		void ExitToolStripMenuItemClick(object sender, EventArgs e)
		{
			exitWithApplication();
		}
		
		void RegistrationToolStripMenuItemClick(object sender, EventArgs e)
		{
			goToRegistrationPage();
		}
		
		void AddFlexibleTaskToolStripMenuItemClick(object sender, EventArgs e)
		{
			goToAddFlexibleTaskPage();
		}
		
		void AddHardTaskToolStripMenuItemClick(object sender, EventArgs e)
		{
			goToAddHardTaskPage();
		}
		void ShowTasksToolStripMenuItemClick(object sender, EventArgs e)
		{
			goToCalendarePage();
		}
		void ProfilingTasksToolStripMenuItemClick(object sender, EventArgs e)
		{
			goToProfilingPage();
		}
		void ImportTasksInOutlookToolStripMenuItemClick(object sender, EventArgs e)
		{
			//TODO Send request to controller
			OutlookImporter.importTasks(null);
		}
		
	}
}
