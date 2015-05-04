/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 4/29/2015
 * Time: 21:33
 */
namespace Course_project.Views
{
	partial class AddFlexibleTaskView
	{
		/// <summary>
		/// Designer variable used to keep track of non-visual components.
		/// </summary>
		private System.ComponentModel.IContainer components = null;
		private System.Windows.Forms.Label priority_lbl;
		private System.Windows.Forms.NumericUpDown priority_numericUpDown;
		private System.Windows.Forms.Label dependentTask_lbl;
		private System.Windows.Forms.ListView listView1;
		private System.Windows.Forms.Button addTask_button;
		private System.Windows.Forms.Button removeTask_button;
		
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
			this.priority_lbl = new System.Windows.Forms.Label();
			this.priority_numericUpDown = new System.Windows.Forms.NumericUpDown();
			this.dependentTask_lbl = new System.Windows.Forms.Label();
			this.listView1 = new System.Windows.Forms.ListView();
			this.addTask_button = new System.Windows.Forms.Button();
			this.removeTask_button = new System.Windows.Forms.Button();
			((System.ComponentModel.ISupportInitialize)(this.priority_numericUpDown)).BeginInit();
			this.SuspendLayout();
			// 
			// submit_button
			// 
			this.submit_button.Location = new System.Drawing.Point(131, 321);
			// 
			// priority_lbl
			// 
			this.priority_lbl.Location = new System.Drawing.Point(12, 161);
			this.priority_lbl.Name = "priority_lbl";
			this.priority_lbl.Size = new System.Drawing.Size(52, 23);
			this.priority_lbl.TabIndex = 6;
			this.priority_lbl.Text = "Priority";
			// 
			// priority_numericUpDown
			// 
			this.priority_numericUpDown.Location = new System.Drawing.Point(79, 159);
			this.priority_numericUpDown.Maximum = new decimal(new int[] {
			5,
			0,
			0,
			0});
			this.priority_numericUpDown.Name = "priority_numericUpDown";
			this.priority_numericUpDown.Size = new System.Drawing.Size(269, 20);
			this.priority_numericUpDown.TabIndex = 7;
			// 
			// dependentTask_lbl
			// 
			this.dependentTask_lbl.Location = new System.Drawing.Point(12, 208);
			this.dependentTask_lbl.Name = "dependentTask_lbl";
			this.dependentTask_lbl.Size = new System.Drawing.Size(100, 23);
			this.dependentTask_lbl.TabIndex = 8;
			this.dependentTask_lbl.Text = "Dependent tasks";
			// 
			// listView1
			// 
			this.listView1.Location = new System.Drawing.Point(131, 208);
			this.listView1.Name = "listView1";
			this.listView1.Size = new System.Drawing.Size(217, 97);
			this.listView1.TabIndex = 9;
			this.listView1.UseCompatibleStateImageBehavior = false;
			// 
			// addTask_button
			// 
			this.addTask_button.Location = new System.Drawing.Point(12, 234);
			this.addTask_button.Name = "addTask_button";
			this.addTask_button.Size = new System.Drawing.Size(90, 23);
			this.addTask_button.TabIndex = 10;
			this.addTask_button.Text = "Add task";
			this.addTask_button.UseVisualStyleBackColor = true;
			// 
			// removeTask_button
			// 
			this.removeTask_button.Location = new System.Drawing.Point(12, 263);
			this.removeTask_button.Name = "removeTask_button";
			this.removeTask_button.Size = new System.Drawing.Size(90, 23);
			this.removeTask_button.TabIndex = 11;
			this.removeTask_button.Text = "Remove task";
			this.removeTask_button.UseVisualStyleBackColor = true;
			// 
			// AddFlexibleTaskView
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(368, 354);
			this.Controls.Add(this.removeTask_button);
			this.Controls.Add(this.addTask_button);
			this.Controls.Add(this.listView1);
			this.Controls.Add(this.dependentTask_lbl);
			this.Controls.Add(this.priority_numericUpDown);
			this.Controls.Add(this.priority_lbl);
			this.Name = "AddFlexibleTaskView";
			this.Text = "Add flexible task";
			this.Controls.SetChildIndex(this.submit_button, 0);
			this.Controls.SetChildIndex(this.priority_lbl, 0);
			this.Controls.SetChildIndex(this.priority_numericUpDown, 0);
			this.Controls.SetChildIndex(this.dependentTask_lbl, 0);
			this.Controls.SetChildIndex(this.listView1, 0);
			this.Controls.SetChildIndex(this.addTask_button, 0);
			this.Controls.SetChildIndex(this.removeTask_button, 0);
			((System.ComponentModel.ISupportInitialize)(this.priority_numericUpDown)).EndInit();
			this.ResumeLayout(false);
			this.PerformLayout();

		}
	}
}
