namespace Course_project.Views
{
	partial class FlexibleTaskDialogView
	{
		/// <summary>
		/// Designer variable used to keep track of non-visual components.
		/// </summary>
		private System.ComponentModel.IContainer components = null;
		private System.Windows.Forms.Label parts_lbl;
		private System.Windows.Forms.NumericUpDown maxPatrs_numericUpDown;
		private System.Windows.Forms.Label dependentTask_lbl;
		private System.Windows.Forms.ListView dependentTasks;
		private System.Windows.Forms.Button addTask_button;
		private System.Windows.Forms.Button removeTask_button;
		private System.Windows.Forms.Label minTimeFromParts_label;
		private System.Windows.Forms.NumericUpDown numericUpDown1;
		
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
			this.parts_lbl = new System.Windows.Forms.Label();
			this.maxPatrs_numericUpDown = new System.Windows.Forms.NumericUpDown();
			this.dependentTask_lbl = new System.Windows.Forms.Label();
			this.dependentTasks = new System.Windows.Forms.ListView();
			this.addTask_button = new System.Windows.Forms.Button();
			this.removeTask_button = new System.Windows.Forms.Button();
			this.minTimeFromParts_label = new System.Windows.Forms.Label();
			this.numericUpDown1 = new System.Windows.Forms.NumericUpDown();
			((System.ComponentModel.ISupportInitialize)(this.maxPatrs_numericUpDown)).BeginInit();
			((System.ComponentModel.ISupportInitialize)(this.numericUpDown1)).BeginInit();
			this.SuspendLayout();
			// 
			// submit_button
			// 
			this.submit_button.Location = new System.Drawing.Point(131, 360);
			this.submit_button.Click += new System.EventHandler(this.Submit_buttonClick);
			// 
			// parts_lbl
			// 
			this.parts_lbl.Location = new System.Drawing.Point(12, 161);
			this.parts_lbl.Name = "parts_lbl";
			this.parts_lbl.Size = new System.Drawing.Size(61, 23);
			this.parts_lbl.TabIndex = 6;
			this.parts_lbl.Text = "Max patrs";
			// 
			// maxPatrs_priority_numericUpDown
			// 
			this.maxPatrs_numericUpDown.Location = new System.Drawing.Point(79, 159);
			this.maxPatrs_numericUpDown.Maximum = new decimal(new int[] {
			10,
			0,
			0,
			0});
			this.maxPatrs_numericUpDown.Minimum = new decimal(new int[] {
			1,
			0,
			0,
			0});
			this.maxPatrs_numericUpDown.Name = "maxPatrs_priority_numericUpDown";
			this.maxPatrs_numericUpDown.Size = new System.Drawing.Size(269, 20);
			this.maxPatrs_numericUpDown.TabIndex = 7;
			this.maxPatrs_numericUpDown.Value = new decimal(new int[] {
			1,
			0,
			0,
			0});
			// 
			// dependentTask_lbl
			// 
			this.dependentTask_lbl.Location = new System.Drawing.Point(12, 247);
			this.dependentTask_lbl.Name = "dependentTask_lbl";
			this.dependentTask_lbl.Size = new System.Drawing.Size(100, 23);
			this.dependentTask_lbl.TabIndex = 8;
			this.dependentTask_lbl.Text = "Dependent tasks";
			// 
			// listView1
			// 
			this.dependentTasks.Location = new System.Drawing.Point(131, 247);
			this.dependentTasks.Name = "listView1";
			this.dependentTasks.Size = new System.Drawing.Size(217, 97);
			this.dependentTasks.TabIndex = 9;
			this.dependentTasks.UseCompatibleStateImageBehavior = false;
			// 
			// addTask_button
			// 
			this.addTask_button.Location = new System.Drawing.Point(12, 273);
			this.addTask_button.Name = "addTask_button";
			this.addTask_button.Size = new System.Drawing.Size(90, 23);
			this.addTask_button.TabIndex = 10;
			this.addTask_button.Text = "Add task";
			this.addTask_button.UseVisualStyleBackColor = true;
			// 
			// removeTask_button
			// 
			this.removeTask_button.Location = new System.Drawing.Point(12, 302);
			this.removeTask_button.Name = "removeTask_button";
			this.removeTask_button.Size = new System.Drawing.Size(90, 23);
			this.removeTask_button.TabIndex = 11;
			this.removeTask_button.Text = "Remove task";
			this.removeTask_button.UseVisualStyleBackColor = true;
			// 
			// minTimeFromParts_label
			// 
			this.minTimeFromParts_label.Location = new System.Drawing.Point(12, 198);
			this.minTimeFromParts_label.Name = "minTimeFromParts_label";
			this.minTimeFromParts_label.Size = new System.Drawing.Size(122, 23);
			this.minTimeFromParts_label.TabIndex = 12;
			this.minTimeFromParts_label.Text = "Min time form parts (min)";
			// 
			// numericUpDown1
			// 
			this.numericUpDown1.Increment = new decimal(new int[] {
			15,
			0,
			0,
			0});
			this.numericUpDown1.Location = new System.Drawing.Point(140, 196);
			this.numericUpDown1.Maximum = new decimal(new int[] {
			300,
			0,
			0,
			0});
			this.numericUpDown1.Minimum = new decimal(new int[] {
			15,
			0,
			0,
			0});
			this.numericUpDown1.Name = "numericUpDown1";
			this.numericUpDown1.Size = new System.Drawing.Size(208, 20);
			this.numericUpDown1.TabIndex = 13;
			this.numericUpDown1.Value = new decimal(new int[] {
			15,
			0,
			0,
			0});
			// 
			// AddFlexibleTaskView
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(368, 401);
			this.Controls.Add(this.numericUpDown1);
			this.Controls.Add(this.minTimeFromParts_label);
			this.Controls.Add(this.removeTask_button);
			this.Controls.Add(this.addTask_button);
			this.Controls.Add(this.dependentTasks);
			this.Controls.Add(this.dependentTask_lbl);
			this.Controls.Add(this.maxPatrs_numericUpDown);
			this.Controls.Add(this.parts_lbl);
			this.Name = "AddFlexibleTaskView";
			this.Text = "Add flexible task";
			this.Controls.SetChildIndex(this.submit_button, 0);
			this.Controls.SetChildIndex(this.parts_lbl, 0);
			this.Controls.SetChildIndex(this.maxPatrs_numericUpDown, 0);
			this.Controls.SetChildIndex(this.dependentTask_lbl, 0);
			this.Controls.SetChildIndex(this.dependentTasks, 0);
			this.Controls.SetChildIndex(this.addTask_button, 0);
			this.Controls.SetChildIndex(this.removeTask_button, 0);
			this.Controls.SetChildIndex(this.minTimeFromParts_label, 0);
			this.Controls.SetChildIndex(this.numericUpDown1, 0);
			((System.ComponentModel.ISupportInitialize)(this.maxPatrs_numericUpDown)).EndInit();
			((System.ComponentModel.ISupportInitialize)(this.numericUpDown1)).EndInit();
			this.ResumeLayout(false);
			this.PerformLayout();
		}
	}
}
