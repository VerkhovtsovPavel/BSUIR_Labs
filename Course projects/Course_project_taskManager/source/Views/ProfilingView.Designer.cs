/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 5/5/2015
 * Time: 21:44
 */
namespace Course_project.Views
{
	public partial class ProfilingView
	{
		/// <summary>
		/// Designer variable used to keep track of non-visual components.
		/// </summary>
		private System.ComponentModel.IContainer components = null;
		private System.Windows.Forms.ComboBox group_comboBox;
		private System.Windows.Forms.Label group_label;
		private System.Windows.Forms.Button spentTime_button;
		
		/// <summary>
		/// Disposes resources used by the form.
		/// </summary>
		/// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
		protected override void Dispose(bool disposing)
		{
			if (disposing)
			{
				if (this.components != null) 
				{
					this.components.Dispose();
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
			this.group_comboBox = new System.Windows.Forms.ComboBox();
			this.group_label = new System.Windows.Forms.Label();
			this.spentTime_button = new System.Windows.Forms.Button();
			this.SuspendLayout();

			// group_comboBox
			this.group_comboBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
			this.group_comboBox.FormattingEnabled = true;
			this.group_comboBox.Location = new System.Drawing.Point(98, 38);
			this.group_comboBox.Name = "group_comboBox";
			this.group_comboBox.Size = new System.Drawing.Size(121, 21);
			this.group_comboBox.TabIndex = 6;

			// group_label
			this.group_label.Location = new System.Drawing.Point(17, 38);
			this.group_label.Name = "group_label";
			this.group_label.Size = new System.Drawing.Size(65, 23);
			this.group_label.TabIndex = 7;
			this.group_label.Text = "Group";

			// SpentTime_button
			this.spentTime_button.Location = new System.Drawing.Point(63, 75);
			this.spentTime_button.Name = "SpentTime_button";
			this.spentTime_button.Size = new System.Drawing.Size(105, 23);
			this.spentTime_button.TabIndex = 8;
			this.spentTime_button.Text = "Get spent time";
			this.spentTime_button.UseVisualStyleBackColor = true;
			this.spentTime_button.Click += new System.EventHandler(this.SpentTime_buttonClick);

			// ProfilingView 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(256, 121);
			this.Controls.Add(this.spentTime_button);
			this.Controls.Add(this.group_label);
			this.Controls.Add(this.group_comboBox);
			this.MaximumSize = new System.Drawing.Size(272, 159);
			this.MinimumSize = new System.Drawing.Size(272, 159);
			this.Name = "ProfilingView";
			this.Text = "ProfilingView";
			this.Controls.SetChildIndex(this.group_comboBox, 0);
			this.Controls.SetChildIndex(this.group_label, 0);
			this.Controls.SetChildIndex(this.spentTime_button, 0);
			this.ResumeLayout(false);
			this.PerformLayout();
		}
	}
}
