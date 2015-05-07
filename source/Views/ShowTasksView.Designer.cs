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
		private System.Windows.Forms.Button shareTask_button;
		private System.Windows.Forms.Button editTask_button;
		private System.Windows.Forms.Button deleteTask_button;
		private System.Windows.Forms.Button editTask_button;
		private System.Windows.Forms.Button deleteTask_button;
		
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
		{
			this.tasks_listBox = new System.Windows.Forms.ListBox();
			this.shareTask_button = new System.Windows.Forms.Button();
			this.editTask_button = new System.Windows.Forms.Button();
			this.deleteTask_button = new System.Windows.Forms.Button();
			this.SuspendLayout();
			// 
			// tasks_listBox
			// 
			this.tasks_listBox.FormattingEnabled = true;
			this.tasks_listBox.Location = new System.Drawing.Point(12, 30);
			this.tasks_listBox.Name = "tasks_listBox";
			this.tasks_listBox.Size = new System.Drawing.Size(528, 212);
			this.tasks_listBox.TabIndex = 0;
			// 
			// shareTask_button
			// 
			this.shareTask_button.Location = new System.Drawing.Point(209, 248);
			this.shareTask_button.Name = "shareTask_button";
			this.shareTask_button.Size = new System.Drawing.Size(121, 23);
			this.shareTask_button.TabIndex = 6;
			this.shareTask_button.Text = "Share task";
			this.shareTask_button.UseVisualStyleBackColor = true;
			this.shareTask_button.Click += new System.EventHandler(this.ShareTask_buttonClick);
			// 
			// editTask_button
			// 
			this.editTask_button.Location = new System.Drawing.Point(39, 248);
			this.editTask_button.Name = "editTask_button";
			this.editTask_button.Size = new System.Drawing.Size(128, 23);
			this.editTask_button.TabIndex = 7;
			this.editTask_button.Text = "Edit task";
			this.editTask_button.UseVisualStyleBackColor = true;
			// 
			// deleteTask_button
			// 
			this.deleteTask_button.Location = new System.Drawing.Point(382, 248);
			this.deleteTask_button.Name = "deleteTask_button";
			this.deleteTask_button.Size = new System.Drawing.Size(114, 23);
			this.deleteTask_button.TabIndex = 8;
			this.deleteTask_button.Text = "Delete task";
			this.deleteTask_button.UseVisualStyleBackColor = true;
			// 
			// ShowTasksView
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(552, 300);
			this.Controls.Add(this.deleteTask_button);
			this.Controls.Add(this.editTask_button);
			this.Controls.Add(this.shareTask_button);
			this.Controls.Add(this.tasks_listBox);
			this.Name = "ShowTasksView";
			this.Text = "ShowTasksView";
			this.Load += new System.EventHandler(this.ShowTasksViewLoad);
			this.Controls.SetChildIndex(this.tasks_listBox, 0);
			this.Controls.SetChildIndex(this.shareTask_button, 0);
			this.Controls.SetChildIndex(this.editTask_button, 0);
			this.Controls.SetChildIndex(this.deleteTask_button, 0);
			this.ResumeLayout(false);
			this.PerformLayout();

		}this.deleteTask_button.Location = new System.Drawing.Point(382, 248);
			this.deleteTask_button.Name = "deleteTask_button";
			this.deleteTask_button.Size = new System.Drawing.Size(114, 23);
			this.deleteTask_button.TabIndex = 8;
			this.deleteTask_button.Text = "Delete task";
			this.deleteTask_button.UseVisualStyleBackColor = true;
			// 
			// ShowTasksView
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(552, 300);
			this.Controls.Add(this.deleteTask_button);
			this.Controls.Add(this.editTask_button);
			this.Controls.Add(this.shareTask_button);
			this.Controls.Add(this.tasks_listBox);
			this.Name = "ShowTasksView";
			this.Text = "ShowTasksView";
			this.Load += new System.EventHandler(this.ShowTasksViewLoad);
			this.Controls.SetChildIndex(this.tasks_listBox, 0);
			this.Controls.SetChildIndex(this.shareTask_button, 0);
			this.Controls.SetChildIndex(this.editTask_button, 0);
			this.Controls.SetChildIndex(this.deleteTask_button, 0);
			this.ResumeLayout(false);
			this.PerformLayout();

		}
	}
}
