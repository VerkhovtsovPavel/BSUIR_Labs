namespace OSiSP_6.UI
{
	partial class InterlocutorSelectForm
	{
		private System.ComponentModel.IContainer components = null;
		private System.Windows.Forms.ComboBox interlocutor_comboBox;
		private System.Windows.Forms.Button Submit_button;
		
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
			this.interlocutor_comboBox = new System.Windows.Forms.ComboBox();
			this.Submit_button = new System.Windows.Forms.Button();
			this.SuspendLayout();
			// 
			// interlocutor_comboBox
			// 
			this.interlocutor_comboBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
			this.interlocutor_comboBox.FormattingEnabled = true;
			this.interlocutor_comboBox.Location = new System.Drawing.Point(12, 12);
			this.interlocutor_comboBox.Name = "interlocutor_comboBox";
			this.interlocutor_comboBox.Size = new System.Drawing.Size(260, 21);
			this.interlocutor_comboBox.TabIndex = 0;
			// 
			// Submit_button
			// 
			this.Submit_button.Location = new System.Drawing.Point(76, 54);
			this.Submit_button.Name = "Submit_button";
			this.Submit_button.Size = new System.Drawing.Size(119, 23);
			this.Submit_button.TabIndex = 1;
			this.Submit_button.Text = "Submit";
			this.Submit_button.UseVisualStyleBackColor = true;
			this.Submit_button.Click += new System.EventHandler(this.Submit_buttonClick);
			// 
			// InterlocutorSelectForm
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(284, 89);
			this.Controls.Add(this.Submit_button);
			this.Controls.Add(this.interlocutor_comboBox);
			this.MaximumSize = new System.Drawing.Size(300, 127);
			this.MinimumSize = new System.Drawing.Size(300, 127);
			this.Name = "InterlocutorSelectForm";
			this.Text = "Interlocutor Select";
			this.ResumeLayout(false);

		}
	}
}
