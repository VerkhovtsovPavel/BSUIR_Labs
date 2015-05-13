/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 5/12/2015
 * Time: 21:09
 */
namespace Course_project.Views
{
	partial class FlexibleTaskView
	{
		/// <summary>
		/// Designer variable used to keep track of non-visual components.
		/// </summary>
		private System.ComponentModel.IContainer components = null;
		private System.Windows.Forms.ComboBox taskComboBox;
		private System.Windows.Forms.Button Submit;
		
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
			this.taskComboBox = new System.Windows.Forms.ComboBox();
			this.Submit = new System.Windows.Forms.Button();
			this.SuspendLayout();
			// 
			// taskComboBox
			// 
			this.taskComboBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
			this.taskComboBox.FormattingEnabled = true;
			this.taskComboBox.Location = new System.Drawing.Point(12, 12);
			this.taskComboBox.Name = "taskComboBox";
			this.taskComboBox.Size = new System.Drawing.Size(260, 21);
			this.taskComboBox.TabIndex = 0;
			// 
			// Submit
			// 
			this.Submit.Location = new System.Drawing.Point(72, 48);
			this.Submit.Name = "Submit";
			this.Submit.Size = new System.Drawing.Size(118, 31);
			this.Submit.TabIndex = 1;
			this.Submit.Text = "Submit";
			this.Submit.UseVisualStyleBackColor = true;
			this.Submit.Click += new System.EventHandler(this.SubmitClick);
			// 
			// AddDependentTaskView
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(284, 91);
			this.Controls.Add(this.Submit);
			this.Controls.Add(this.taskComboBox);
			this.Name = "AddDependentTaskView";
			this.Text = "Add Dependent Task";
			this.ResumeLayout(false);

		}
	}
}
