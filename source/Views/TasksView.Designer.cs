namespace Course_project.Views
{
	partial class TasksView
	{
		private System.ComponentModel.IContainer components = null;
		private System.Windows.Forms.Button shareTask_button;
		private System.Windows.Forms.Button editTask_button;
		private System.Windows.Forms.Button deleteTask_button;
		private System.Windows.Forms.DateTimePicker start_dateTimePicker;
		private System.Windows.Forms.DateTimePicker stop_dateTimePicker;
		private System.Windows.Forms.DataGridView tasksGridView;
		
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
		private void InitializeComponent()
		{
			this.tasksGridView = new System.Windows.Forms.DataGridView();
			this.shareTask_button = new System.Windows.Forms.Button();
			this.editTask_button = new System.Windows.Forms.Button();
			this.deleteTask_button = new System.Windows.Forms.Button();
			this.start_dateTimePicker = new System.Windows.Forms.DateTimePicker();
			this.stop_dateTimePicker = new System.Windows.Forms.DateTimePicker();
			((System.ComponentModel.ISupportInitialize)(this.tasksGridView)).BeginInit();
			this.SuspendLayout();
			// 
			// tasksGridView
			// 
			this.tasksGridView.BackgroundColor = System.Drawing.SystemColors.ButtonHighlight;
			this.tasksGridView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
			this.tasksGridView.GridColor = System.Drawing.SystemColors.ActiveCaptionText;
			this.tasksGridView.Location = new System.Drawing.Point(12, 68);
			this.tasksGridView.MultiSelect = false;
			this.tasksGridView.Name = "tasksGridView";
			this.tasksGridView.ReadOnly = true;
			this.tasksGridView.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
			this.tasksGridView.Size = new System.Drawing.Size(657, 168);
			this.tasksGridView.TabIndex = 11;
			// 
			// shareTask_button
			// 
			this.shareTask_button.Location = new System.Drawing.Point(283, 286);
			this.shareTask_button.Name = "shareTask_button";
			this.shareTask_button.Size = new System.Drawing.Size(121, 23);
			this.shareTask_button.TabIndex = 6;
			this.shareTask_button.Text = "Share task";
			this.shareTask_button.UseVisualStyleBackColor = true;
			this.shareTask_button.Click += new System.EventHandler(this.ShareTask_buttonClick);
			// 
			// editTask_button
			// 
			this.editTask_button.Location = new System.Drawing.Point(39, 286);
			this.editTask_button.Name = "editTask_button";
			this.editTask_button.Size = new System.Drawing.Size(128, 23);
			this.editTask_button.TabIndex = 7;
			this.editTask_button.Text = "Edit task";
			this.editTask_button.UseVisualStyleBackColor = true;
			this.editTask_button.Click += new System.EventHandler(this.EditTask_buttonClick);
			// 
			// deleteTask_button
			// 
			this.deleteTask_button.Location = new System.Drawing.Point(529, 286);
			this.deleteTask_button.Name = "deleteTask_button";
			this.deleteTask_button.Size = new System.Drawing.Size(114, 23);
			this.deleteTask_button.TabIndex = 8;
			this.deleteTask_button.Text = "Delete task";
			this.deleteTask_button.UseVisualStyleBackColor = true;
			this.deleteTask_button.Click += new System.EventHandler(this.DeleteTask_buttonClick);
			// 
			// start_dateTimePicker
			// 
			this.start_dateTimePicker.CustomFormat = "MMMM dd, yyyy - dddd HH:mm";
			this.start_dateTimePicker.Format = System.Windows.Forms.DateTimePickerFormat.Custom;
			this.start_dateTimePicker.Location = new System.Drawing.Point(12, 42);
			this.start_dateTimePicker.Name = "start_dateTimePicker";
			this.start_dateTimePicker.RightToLeft = System.Windows.Forms.RightToLeft.No;
			this.start_dateTimePicker.Size = new System.Drawing.Size(269, 20);
			this.start_dateTimePicker.TabIndex = 9;
			this.start_dateTimePicker.Value = new System.DateTime(2015, 5, 9, 0, 0, 0, 0);
			this.start_dateTimePicker.ValueChanged += new System.EventHandler(this.changeRange);
			// 
			// stop_dateTimePicker
			// 
			this.stop_dateTimePicker.CustomFormat = "MMMM dd, yyyy - dddd HH:mm";
			this.stop_dateTimePicker.Format = System.Windows.Forms.DateTimePickerFormat.Custom;
			this.stop_dateTimePicker.Location = new System.Drawing.Point(400, 42);
			this.stop_dateTimePicker.Name = "stop_dateTimePicker";
			this.stop_dateTimePicker.RightToLeft = System.Windows.Forms.RightToLeft.No;
			this.stop_dateTimePicker.Size = new System.Drawing.Size(269, 20);
			this.stop_dateTimePicker.TabIndex = 10;
			this.stop_dateTimePicker.Value = new System.DateTime(2015, 5, 9, 0, 0, 0, 0);
			this.stop_dateTimePicker.ValueChanged += new System.EventHandler(this.changeRange);
			// 
			// TasksView
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(681, 328);
			this.Controls.Add(this.tasksGridView);
			this.Controls.Add(this.stop_dateTimePicker);
			this.Controls.Add(this.start_dateTimePicker);
			this.Controls.Add(this.deleteTask_button);
			this.Controls.Add(this.editTask_button);
			this.Controls.Add(this.shareTask_button);
			this.Name = "TasksView";
			this.Text = "ShowTasksView";
			this.Load += new System.EventHandler(this.ShowTasksViewLoad);
			this.Controls.SetChildIndex(this.shareTask_button, 0);
			this.Controls.SetChildIndex(this.editTask_button, 0);
			this.Controls.SetChildIndex(this.deleteTask_button, 0);
			this.Controls.SetChildIndex(this.start_dateTimePicker, 0);
			this.Controls.SetChildIndex(this.stop_dateTimePicker, 0);
			this.Controls.SetChildIndex(this.tasksGridView, 0);
			((System.ComponentModel.ISupportInitialize)(this.tasksGridView)).EndInit();
			this.ResumeLayout(false);
			this.PerformLayout();

		}

		
	}
}
