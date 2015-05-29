namespace OSiSP_6.UI
{
	partial class InterlocutorSelectForm
	{
		private System.ComponentModel.IContainer components = null;
		private System.Windows.Forms.Button Submit_button;
		private static System.Windows.Forms.ComboBox interlocutor_comboBox = new System.Windows.Forms.ComboBox();
		
		protected override void Dispose(bool disposing)
		{
			if (disposing) {
				if (components != null) {
					components.Dispose();
				}
			}
			base.Dispose(disposing);
		}
		
		private void InitializeComponent()
		{
			this.Submit_button = new System.Windows.Forms.Button();
			this.SuspendLayout();
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

			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(284, 89);
			this.Controls.Add(interlocutor_comboBox);
			this.Controls.Add(this.Submit_button);
			this.MaximumSize = new System.Drawing.Size(300, 127);
			this.MinimumSize = new System.Drawing.Size(300, 127);
			this.Name = "InterlocutorSelectForm";
			this.Text = "Interlocutor Select";
			this.ResumeLayout(false);

		}
		
		private  void ComboBoxInit()
		{
			interlocutor_comboBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
			interlocutor_comboBox.FormattingEnabled = true;
			interlocutor_comboBox.Location = new System.Drawing.Point(12, 12);
			interlocutor_comboBox.Name = "users_comboBox";
			interlocutor_comboBox.Size = new System.Drawing.Size(260, 21);
			interlocutor_comboBox.TabIndex = 2;
		}
	}
}
