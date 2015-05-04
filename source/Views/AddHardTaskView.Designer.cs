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
		protected System.Windows.Forms.Button submit_button;
		private System.Windows.Forms.Label group_lbl;
		private System.Windows.Forms.ComboBox comboBox1;
		
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
			this.submit_button = new System.Windows.Forms.Button();
			this.group_lbl = new System.Windows.Forms.Label();
			this.comboBox1 = new System.Windows.Forms.ComboBox();
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
			this.start_dateTimePicker.CustomFormat = "MMMM dd, yyyy - dddd HH:mm";
			this.start_dateTimePicker.Format = System.Windows.Forms.DateTimePickerFormat.Custom;
			this.start_dateTimePicker.Location = new System.Drawing.Point(79, 56);
			this.start_dateTimePicker.Name = "start_dateTimePicker";
			this.start_dateTimePicker.RightToLeft = System.Windows.Forms.RightToLeft.No;
			this.start_dateTimePicker.Size = new System.Drawing.Size(269, 20);
			this.start_dateTimePicker.TabIndex = 3;
			this.start_dateTimePicker.Value = new System.DateTime(2015, 4, 21, 0, 0, 0, 0);
			this.start_dateTimePicker.ValueChanged += new System.EventHandler(this.Start_dateTimePickerValueChanged);
			// 
			// stop_dateTimePicker
			// 
			this.stop_dateTimePicker.CustomFormat = "MMMM dd, yyyy - dddd HH:mm";
			this.stop_dateTimePicker.Format = System.Windows.Forms.DateTimePickerFormat.Custom;
			this.stop_dateTimePicker.Location = new System.Drawing.Point(79, 90);
			this.stop_dateTimePicker.Name = "stop_dateTimePicker";
			this.stop_dateTimePicker.Size = new System.Drawing.Size(269, 20);
			this.stop_dateTimePicker.TabIndex = 4;
			// 
			// title_tb
			// 
			this.title_tb.Location = new System.Drawing.Point(79, 27);
			this.title_tb.Name = "title_tb";
			this.title_tb.Size = new System.Drawing.Size(269, 20);
			this.title_tb.TabIndex = 5;
			// 
			// submit_button
			// 
			this.submit_button.Location = new System.Drawing.Point(126, 157);
			this.submit_button.Name = "submit_button";
			this.submit_button.Size = new System.Drawing.Size(88, 23);
			this.submit_button.TabIndex = 6;
			this.submit_button.Text = "Submit";
			this.submit_button.UseVisualStyleBackColor = true;
			// 
			// group_lbl
			// 
			this.group_lbl.Location = new System.Drawing.Point(12, 127);
			this.group_lbl.Name = "group_lbl";
			this.group_lbl.Size = new System.Drawing.Size(52, 23);
			this.group_lbl.TabIndex = 7;
			this.group_lbl.Text = "Group";
			// 
			// comboBox1
			// 
			this.comboBox1.FormattingEnabled = true;
			this.comboBox1.Location = new System.Drawing.Point(79, 124);
			this.comboBox1.Name = "comboBox1";
			this.comboBox1.Size = new System.Drawing.Size(269, 21);
			this.comboBox1.TabIndex = 8;
			// 
			// AddHardTaskView
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(360, 204);
			this.Controls.Add(this.comboBox1);
			this.Controls.Add(this.group_lbl);
			this.Controls.Add(this.submit_button);
			this.Controls.Add(this.title_tb);
			this.Controls.Add(this.stop_dateTimePicker);
			this.Controls.Add(this.start_dateTimePicker);
			this.Controls.Add(this.stop_dateTime_lbl);
			this.Controls.Add(this.start_dateTime_lbl);
			this.Controls.Add(this.title_lbl);
			this.Name = "AddHardTaskView";
			this.Text = "Add hard task";
			this.Controls.SetChildIndex(this.title_lbl, 0);
			this.Controls.SetChildIndex(this.start_dateTime_lbl, 0);
			this.Controls.SetChildIndex(this.stop_dateTime_lbl, 0);
			this.Controls.SetChildIndex(this.start_dateTimePicker, 0);
			this.Controls.SetChildIndex(this.stop_dateTimePicker, 0);
			this.Controls.SetChildIndex(this.title_tb, 0);
			this.Controls.SetChildIndex(this.submit_button, 0);
			this.Controls.SetChildIndex(this.group_lbl, 0);
			this.Controls.SetChildIndex(this.comboBox1, 0);
			this.ResumeLayout(false);
			this.PerformLayout();

		}
	}
}
