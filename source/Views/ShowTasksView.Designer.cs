/*
 * Created by SharpDevelop.
 * User: Администратор
 * Date: 25.04.2015
 * Time: 13:05
 */
namespace Course_project.Views
{
	partial class ShowTasksView
	{
		/// <summary>
		/// Designer variable used to keep track of non-visual components.
		/// </summary>
		private System.ComponentModel.IContainer components = null;
		private System.Windows.Forms.ListBox tasks_listBox;
		
		/// <summary>
		/// Disposes resources used by the form.
		/// </summary>
		/// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
		protected override void Dispose(bool disposing)
		{
			if (disposing) {
				if (components != null) {
					components.Dispose();
				}
			}
			base.Dispose(disposing);
		}
		
		/// <summary>
		/// This method is required for Windows Forms designer support.
		/// Do not change the method contents inside the source code editor. The Forms designer might
		/// not be able to load this method if it was changed manually.
		/// </summary>
		private void InitializeComponent()
		{
			this.tasks_listBox = new System.Windows.Forms.ListBox();
			this.SuspendLayout();
			// 
			// tasks_listBox
			// 
			this.tasks_listBox.FormattingEnabled = true;
			this.tasks_listBox.Location = new System.Drawing.Point(12, 30);
			this.tasks_listBox.Name = "tasks_listBox";
			this.tasks_listBox.Size = new System.Drawing.Size(260, 212);
			this.tasks_listBox.TabIndex = 0;
			// 
			// ShowTasksView
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(284, 262);
			this.Controls.Add(this.tasks_listBox);
			this.Name = "ShowTasksView";
			this.Text = "ShowTasksView";
			this.Load += new System.EventHandler(this.ShowTasksViewLoad);
			this.ResumeLayout(false);
		}
	}
}
