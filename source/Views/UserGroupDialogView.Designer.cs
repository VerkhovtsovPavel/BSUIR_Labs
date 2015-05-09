namespace Course_project.Views
{
	partial class UserGroupDialogView
	{
		/// <summary>
		/// Designer variable used to keep track of non-visual components.
		/// </summary>
		private System.ComponentModel.IContainer components = null;
		private System.Windows.Forms.TextBox group_textBox;
		private System.Windows.Forms.Label group_label;
		private System.Windows.Forms.Button submit_button;
		
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
			this.group_textBox = new System.Windows.Forms.TextBox();
			this.group_label = new System.Windows.Forms.Label();
			this.submit_button = new System.Windows.Forms.Button();
			this.SuspendLayout();
			// 
			// group_textBox
			// 
			this.group_textBox.Location = new System.Drawing.Point(64, 9);
			this.group_textBox.Name = "group_textBox";
			this.group_textBox.Size = new System.Drawing.Size(163, 20);
			this.group_textBox.TabIndex = 0;
			// 
			// group_label
			// 
			this.group_label.Location = new System.Drawing.Point(12, 12);
			this.group_label.Name = "group_label";
			this.group_label.Size = new System.Drawing.Size(46, 23);
			this.group_label.TabIndex = 1;
			this.group_label.Text = "Group";
			// 
			// submit_button
			// 
			this.submit_button.Location = new System.Drawing.Point(64, 53);
			this.submit_button.Name = "submit_button";
			this.submit_button.Size = new System.Drawing.Size(127, 23);
			this.submit_button.TabIndex = 2;
			this.submit_button.Text = "Save";
			this.submit_button.UseVisualStyleBackColor = true;
			this.submit_button.Click += new System.EventHandler(this.Submit_buttonClick);
			// 
			// AddGroupView
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(251, 88);
			this.Controls.Add(this.submit_button);
			this.Controls.Add(this.group_label);
			this.Controls.Add(this.group_textBox);
			this.Name = "AddGroupView";
			this.Text = "AddGroupView";
			this.ResumeLayout(false);
			this.PerformLayout();
		}
	}
}
