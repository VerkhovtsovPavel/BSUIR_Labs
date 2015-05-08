namespace Course_project.Views
{
	partial class MainView
	{
		private System.ComponentModel.IContainer components = null;
		
		private System.Windows.Forms.MenuStrip menuStrip1;
		private System.Windows.Forms.ToolStripMenuItem registrationToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem exitToolStripMenuItem;
		protected System.Windows.Forms.ToolStripMenuItem fileToolStripMenuItem;
		protected System.Windows.Forms.ToolStripMenuItem tasksToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem addFlexibleTaskToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem addHardTaskToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem showTasksToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem profilingTasksToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem importTasksInOutlookToolStripMenuItem;
		protected System.Windows.Forms.ToolStripMenuItem profillingToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem editTaskGroupToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem loginToolStripMenuItem;
		
		protected override void Dispose(bool disposing)
		{
			if (disposing) {
				if (components != null) {
					components.Dispose();
				}
			}
			base.Dispose(disposing);
		}
		
		private void InitializeComponent()
		{
			this.menuStrip1 = new System.Windows.Forms.MenuStrip();
			this.fileToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.loginToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.registrationToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.exitToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.tasksToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.addFlexibleTaskToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.addHardTaskToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.showTasksToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.importTasksInOutlookToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.profillingToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.profilingTasksToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.editTaskGroupToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.menuStrip1.SuspendLayout();
			this.SuspendLayout();
			// 
			// menuStrip1
			// 
			this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
			this.fileToolStripMenuItem,
			this.tasksToolStripMenuItem,
			this.profillingToolStripMenuItem});
			this.menuStrip1.Location = new System.Drawing.Point(0, 0);
			this.menuStrip1.Name = "menuStrip1";
			this.menuStrip1.Size = new System.Drawing.Size(427, 24);
			this.menuStrip1.TabIndex = 5;
			this.menuStrip1.Text = "menuStrip1";
			// 
			// fileToolStripMenuItem
			// 
			this.fileToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
			this.loginToolStripMenuItem,
			this.registrationToolStripMenuItem,
			this.exitToolStripMenuItem});
			this.fileToolStripMenuItem.Name = "fileToolStripMenuItem";
			this.fileToolStripMenuItem.Size = new System.Drawing.Size(37, 20);
			this.fileToolStripMenuItem.Text = "File";
			// 
			// loginToolStripMenuItem
			// 
			this.loginToolStripMenuItem.Name = "loginToolStripMenuItem";
			this.loginToolStripMenuItem.Size = new System.Drawing.Size(137, 22);
			this.loginToolStripMenuItem.Text = "Login";
			this.loginToolStripMenuItem.Click += new System.EventHandler(this.LoginToolStripMenuItemClick);
			// 
			// registrationToolStripMenuItem
			// 
			this.registrationToolStripMenuItem.Name = "registrationToolStripMenuItem";
			this.registrationToolStripMenuItem.Size = new System.Drawing.Size(137, 22);
			this.registrationToolStripMenuItem.Text = "Registration";
			this.registrationToolStripMenuItem.Click += new System.EventHandler(this.RegistrationToolStripMenuItemClick);
			// 
			// exitToolStripMenuItem
			// 
			this.exitToolStripMenuItem.Name = "exitToolStripMenuItem";
			this.exitToolStripMenuItem.Size = new System.Drawing.Size(137, 22);
			this.exitToolStripMenuItem.Text = "Exit";
			this.exitToolStripMenuItem.Click += new System.EventHandler(this.ExitToolStripMenuItemClick);
			// 
			// tasksToolStripMenuItem
			// 
			this.tasksToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
			this.addFlexibleTaskToolStripMenuItem,
			this.addHardTaskToolStripMenuItem,
			this.showTasksToolStripMenuItem,
			this.importTasksInOutlookToolStripMenuItem});
			this.tasksToolStripMenuItem.Name = "tasksToolStripMenuItem";
			this.tasksToolStripMenuItem.Size = new System.Drawing.Size(48, 20);
			this.tasksToolStripMenuItem.Text = "Tasks";
			// 
			// addFlexibleTaskToolStripMenuItem
			// 
			this.addFlexibleTaskToolStripMenuItem.Name = "addFlexibleTaskToolStripMenuItem";
			this.addFlexibleTaskToolStripMenuItem.Size = new System.Drawing.Size(198, 22);
			this.addFlexibleTaskToolStripMenuItem.Text = "Add flexible task";
			this.addFlexibleTaskToolStripMenuItem.Click += new System.EventHandler(this.AddFlexibleTaskToolStripMenuItemClick);
			// 
			// addHardTaskToolStripMenuItem
			// 
			this.addHardTaskToolStripMenuItem.Name = "addHardTaskToolStripMenuItem";
			this.addHardTaskToolStripMenuItem.Size = new System.Drawing.Size(198, 22);
			this.addHardTaskToolStripMenuItem.Text = "Add hard task";
			this.addHardTaskToolStripMenuItem.Click += new System.EventHandler(this.AddHardTaskToolStripMenuItemClick);
			// 
			// showTasksToolStripMenuItem
			// 
			this.showTasksToolStripMenuItem.Name = "showTasksToolStripMenuItem";
			this.showTasksToolStripMenuItem.Size = new System.Drawing.Size(198, 22);
			this.showTasksToolStripMenuItem.Text = "Show tasks";
			this.showTasksToolStripMenuItem.Click += new System.EventHandler(this.ShowTasksToolStripMenuItemClick);
			// 
			// importTasksInOutlookToolStripMenuItem
			// 
			this.importTasksInOutlookToolStripMenuItem.Name = "importTasksInOutlookToolStripMenuItem";
			this.importTasksInOutlookToolStripMenuItem.Size = new System.Drawing.Size(198, 22);
			this.importTasksInOutlookToolStripMenuItem.Text = "Import tasks in Outlook";
			this.importTasksInOutlookToolStripMenuItem.Click += new System.EventHandler(this.ImportTasksInOutlookToolStripMenuItemClick);
			// 
			// profillingToolStripMenuItem
			// 
			this.profillingToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
			this.profilingTasksToolStripMenuItem,
			this.editTaskGroupToolStripMenuItem});
			this.profillingToolStripMenuItem.Name = "profillingToolStripMenuItem";
			this.profillingToolStripMenuItem.Size = new System.Drawing.Size(67, 20);
			this.profillingToolStripMenuItem.Text = "Profilling";
			// 
			// profilingTasksToolStripMenuItem
			// 
			this.profilingTasksToolStripMenuItem.Name = "profilingTasksToolStripMenuItem";
			this.profilingTasksToolStripMenuItem.Size = new System.Drawing.Size(153, 22);
			this.profilingTasksToolStripMenuItem.Text = "Profiling tasks";
			this.profilingTasksToolStripMenuItem.Click += new System.EventHandler(this.ProfilingTasksToolStripMenuItemClick);
			// 
			// editTaskGroupToolStripMenuItem
			// 
			this.editTaskGroupToolStripMenuItem.Name = "editTaskGroupToolStripMenuItem";
			this.editTaskGroupToolStripMenuItem.Size = new System.Drawing.Size(153, 22);
			this.editTaskGroupToolStripMenuItem.Text = "Edit task group";
			this.editTaskGroupToolStripMenuItem.Click += new System.EventHandler(this.EditTaskGroupToolStripMenuItemClick);
			// 
			// MainView
			// 
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.None;
			this.ClientSize = new System.Drawing.Size(427, 188);
			this.Controls.Add(this.menuStrip1);
			this.MainMenuStrip = this.menuStrip1;
			this.Name = "MainView";
			this.Text = "Login";
			this.menuStrip1.ResumeLayout(false);
			this.menuStrip1.PerformLayout();
			this.ResumeLayout(false);
			this.PerformLayout();

		}
		
		
		
	
	}
}
