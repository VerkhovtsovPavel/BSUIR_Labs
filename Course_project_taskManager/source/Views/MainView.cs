namespace Course_project.Views
{
	using System;
	using System.Windows.Forms;
	using Course_project.Controller;
	using Course_project.Exception;

	public partial class MainView : Form
	{
		protected MainView()
		{
			this.InitializeComponent();
		}
		
		protected void GoToRegistrationPage()
		{
			RegistrationView registrationView = new RegistrationView();
			registrationView.Show();
			this.Hide();
		}
		
		protected void GoToAddFlexibleTaskPage()
		{
			FlexibleTaskDialogView addFlexibleTaskView = new FlexibleTaskDialogView(ViewMode.ADD_MODE, null);
			addFlexibleTaskView.ShowDialog();
		}
		
		protected void GoToAddHardTaskPage()
		{
			HardTaskDialogView addHardTaskView = new HardTaskDialogView(ViewMode.ADD_MODE, null);
			addHardTaskView.ShowDialog();
		}
		
		protected void GoToCalendarePage()
		{
			CalendarView calendarView = new CalendarView();
			calendarView.Show();
			this.Hide();
		}
		
		protected void GoToProfilingPage()
		{
			ProfilingView profilingView = new ProfilingView();
			profilingView.Show();
		}
		
		protected void ExitWithApplication()
		{
			this.Close();
		}

		protected void DisableFileMenu()
		{
			this.fileToolStripMenuItem.Enabled = false;
		}
		
		protected void DisableTaskAndProfillingMenu()
		{
			this.tasksToolStripMenuItem.Enabled = false;
			this.profillingToolStripMenuItem.Enabled = false;
		}
		
		private void GoToGroupView()
		{
			UserGroupView groupView = new UserGroupView();
			groupView.Show();
		}
		
		private void GoToLoginPage()
		{
			LoginView loginView = new LoginView();
			loginView.Show();
			this.Hide();
		}

		private void GoToFlexibleTaskListPage()
		{
			FlexibleTaskView dependentTaskView = new FlexibleTaskView(null, null, ViewMode.EDIT_MODE);
			dependentTaskView.Show();
		}
		
		private void ExitToolStripMenuItemClick(object sender, EventArgs e)
		{
			this.ExitWithApplication();
		}
		
		private void RegistrationToolStripMenuItemClick(object sender, EventArgs e)
		{
			this.GoToRegistrationPage();
		}
		
		private void AddFlexibleTaskToolStripMenuItemClick(object sender, EventArgs e)
		{
			this.GoToAddFlexibleTaskPage();
		}
		
		private void AddHardTaskToolStripMenuItemClick(object sender, EventArgs e)
		{
			this.GoToAddHardTaskPage();
		}
		
		private void ShowTasksToolStripMenuItemClick(object sender, EventArgs e)
		{
			this.GoToCalendarePage();
		}
		
		private void ProfilingTasksToolStripMenuItemClick(object sender, EventArgs e)
		{
			this.GoToProfilingPage();
		}
		
		private void ImportTasksInOutlookToolStripMenuItemClick(object sender, EventArgs e)
		{
			try
			{
				TaskController.GetInstance().Process(CommandType.IMPORT_TO_OUTLOOK, null);
				MessageBox.Show("Task imported");
			}
			catch (OutlookNotFoundException)
			{
				MessageBox.Show("Outlook don't install in out system");
			}
		}
		
		private void LoginToolStripMenuItemClick(object sender, EventArgs e)
		{
			this.GoToLoginPage();
		}
		
		private void EditTaskGroupToolStripMenuItemClick(object sender, EventArgs e)
		{
			this.GoToGroupView();
		}
		
		private void EditFlexibleTaskToolStripMenuItemClick(object sender, EventArgs e)
		{
			this.GoToFlexibleTaskListPage();
		}
		
		private void ApportionFlexibleTasksToolStripMenuItemClick(object sender, EventArgs e)
		{
			try
			{
				TaskController.GetInstance().Process(CommandType.APPORTION_FLEXIBLE_TASKS, null);
				MessageBox.Show("Tasks apportion");
			}
			catch (CannotApportionTasks)
			{
				MessageBox.Show("Cannot apportion task with current conditions");
			}
		}
	}
}
