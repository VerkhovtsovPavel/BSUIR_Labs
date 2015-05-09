using System;
using System.Drawing;
using System.Windows.Forms;
using Course_project.Controller;
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
			FlexibleTaskDialogView addFlexibleTaskView = new FlexibleTaskDialogView(ViewMode.ADD_MODE, null);
			addFlexibleTaskView.ShowDialog();
		}
		
		protected void goToAddHardTaskPage(){
			HardTaskDialogView addHardTaskView = new HardTaskDialogView(ViewMode.ADD_MODE, null);
			addHardTaskView.ShowDialog();
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

		void goToGroupView()
		{
			UserGroupView groupView = new UserGroupView();
			groupView.Show();
		}
		void goToLoginPage()
		{
			LoginView loginView = new LoginView();
			loginView.Show();
			Hide();
		}
		protected void exitWithApplication(){
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
			TaskController.GetInstance().Process(CommandType.IMPORT_TO_OUTLOOK, null);
		}
		void LoginToolStripMenuItemClick(object sender, EventArgs e)
		{
			goToLoginPage();
		}
		void EditTaskGroupToolStripMenuItemClick(object sender, EventArgs e)
		{
			goToGroupView();
		}
		
	}
}
