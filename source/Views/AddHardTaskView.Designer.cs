/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 4/29/2015
 * Time: 21:26
 */
namespace Course_project.Views
{
	partial class AddHardTaskView
	{
		/// <summary>
		/// Designer variable used to keep track of non-visual components.
		/// </summary>
		private System.ComponentModel.IContainer components = null;
		private System.Windows.Forms.Label title_lbl;
		private System.Windows.Forms.Label start_dateTime_lbl;
		private System.Windows.Forms.Label stop_dateTime_lbl;
		private System.Windows.Forms.DateTimePicker start_dateTimePicker;
		private System.Windows.Forms.DateTimePicker stop_dateTimePicker;
		private System.Windows.Forms.TextBox title_tb;
		
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
			this.title_lbl = new System.Windows.Forms.Label();
			this.start_dateTime_lbl = new System.Windows.Forms.Label();
			this.stop_dateTime_lbl = new System.Windows.Forms.Label();
			this.start_dateTimePicker = new System.Windows.Forms.DateTimePicker();
			this.stop_dateTimePicker = new System.Windows.Forms.DateTimePicker();
			this.title_tb = new System.Windows.Forms.TextBox();
			this.SuspendLayout();
			// 
			// title_lbl
			// 
			this.title_lbl.Location = new System.Drawing.Point(12, 30);
			this.title_lbl.Name = "title_lbl";
			this.title_lbl.Size = new System.Drawing.Size(100, 23);
			this.title_lbl.TabIndex = 0;
			this.title_lbl.Text = "Title";
			this.title_lbl.Click += new System.EventHandler(this.Label1Click);
			// 
			// start_dateTime_lbl
			// 
			this.start_dateTime_lbl.Location = new System.Drawing.Point(12, 63);
			this.start_dateTime_lbl.Name = "start_dateTime_lbl";
			this.start_dateTime_lbl.Size = new System.Drawing.Size(100, 23);
			this.start_dateTime_lbl.TabIndex = 1;
			this.start_dateTime_lbl.Text = "Start";
			// 
			// stop_dateTime_lbl
			// 
			this.stop_dateTime_lbl.Location = new System.Drawing.Point(12, 95);
			this.stop_dateTime_lbl.Name = "stop_dateTime_lbl";
			this.stop_dateTime_lbl.Size = new System.Drawing.Size(100, 23);
			this.stop_dateTime_lbl.TabIndex = 2;
			this.stop_dateTime_lbl.Text = "Stop";
			// 
			// start_dateTimePicker
			// 
			this.start_dateTimePicker.Location = new System.Drawing.Point(79, 56);
			this.start_dateTimePicker.Name = "start_dateTimePicker";
			this.start_dateTimePicker.Size = new System.Drawing.Size(200, 20);
			this.start_dateTimePicker.TabIndex = 3;
			// 
			// stop_dateTimePicker
			// 
			this.stop_dateTimePicker.Location = new System.Drawing.Point(79, 89);
			this.stop_dateTimePicker.Name = "stop_dateTimePicker";
			this.stop_dateTimePicker.Size = new System.Drawing.Size(200, 20);
			this.stop_dateTimePicker.TabIndex = 4;
			// 
			// title_tb
			// 
			this.title_tb.Location = new System.Drawing.Point(79, 27);
			this.title_tb.Name = "title_tb";
			this.title_tb.Size = new System.Drawing.Size(200, 20);
			this.title_tb.TabIndex = 5;
			// 
			// Form1
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(312, 150);
			this.Controls.Add(this.title_tb);
			this.Controls.Add(this.stop_dateTimePicker);
			this.Controls.Add(this.start_dateTimePicker);
			this.Controls.Add(this.stop_dateTime_lbl);
			this.Controls.Add(this.start_dateTime_lbl);
			this.Controls.Add(this.title_lbl);
			this.Name = "AddHardTaskView";
			this.Text = "Add hard task";
			this.ResumeLayout(false);
			this.PerformLayout();

		}
	}
}
