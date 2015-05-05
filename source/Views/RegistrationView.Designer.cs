/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 04/17/2015
 * Time: 20:37
 * 
 * To change this template use Tools | Options | Coding | Edit Standard Headers.
 */
namespace Course_project
{
	partial class RegistrationView
	{
		/// <summary>
		/// Designer variable used to keep track of non-visual components.
		/// </summary>
		private System.ComponentModel.IContainer components = null;
		private System.Windows.Forms.Button submit_button;
		private System.Windows.Forms.Label login_label;
		private System.Windows.Forms.Label password_label;
		private System.Windows.Forms.Label label3;
		private System.Windows.Forms.Label first_name_label;
		private System.Windows.Forms.Label last_name_label;
		private System.Windows.Forms.TextBox last_name_textBox;
		private System.Windows.Forms.ComboBox timeZone_comboBox;
		private System.Windows.Forms.TextBox first_name_textBox;
		private System.Windows.Forms.TextBox login_textBox;
		private System.Windows.Forms.TextBox password_textBox;
		
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
			this.submit_button = new System.Windows.Forms.Button();
			this.login_label = new System.Windows.Forms.Label();
			this.password_label = new System.Windows.Forms.Label();
			this.label3 = new System.Windows.Forms.Label();
			this.login_textBox = new System.Windows.Forms.TextBox();
			this.password_textBox = new System.Windows.Forms.TextBox();
			this.timeZone_comboBox = new System.Windows.Forms.ComboBox();
			this.first_name_label = new System.Windows.Forms.Label();
			this.last_name_label = new System.Windows.Forms.Label();
			this.first_name_textBox = new System.Windows.Forms.TextBox();
			this.last_name_textBox = new System.Windows.Forms.TextBox();
			this.SuspendLayout();
			// 
			// submit_button
			// 
			this.submit_button.Location = new System.Drawing.Point(166, 160);
			this.submit_button.Name = "submit_button";
			this.submit_button.Size = new System.Drawing.Size(75, 23);
			this.submit_button.TabIndex = 0;
			this.submit_button.Text = "Submit";
			this.submit_button.UseVisualStyleBackColor = true;
			this.submit_button.Click += new System.EventHandler(this.Submit_buttonClick);
			// 
			// login_label
			// 
			this.login_label.Location = new System.Drawing.Point(20, 38);
			this.login_label.Name = "login_label";
			this.login_label.Size = new System.Drawing.Size(172, 23);
			this.login_label.TabIndex = 1;
			this.login_label.Text = "Login";
			// 
			// password_label
			// 
			this.password_label.Location = new System.Drawing.Point(20, 61);
			this.password_label.Name = "password_label";
			this.password_label.Size = new System.Drawing.Size(100, 23);
			this.password_label.TabIndex = 1;
			this.password_label.Text = "Password";
			// 
			// label3
			// 
			this.label3.Location = new System.Drawing.Point(20, 136);
			this.label3.Name = "label3";
			this.label3.Size = new System.Drawing.Size(100, 23);
			this.label3.TabIndex = 1;
			this.label3.Text = "TimeZone";
			// 
			// login_textBox
			// 
			this.login_textBox.Location = new System.Drawing.Point(98, 35);
			this.login_textBox.Name = "login_textBox";
			this.login_textBox.Size = new System.Drawing.Size(261, 20);
			this.login_textBox.TabIndex = 2;
			// 
			// password_textBox
			// 
			this.password_textBox.Location = new System.Drawing.Point(98, 58);
			this.password_textBox.Name = "password_textBox";
			this.password_textBox.PasswordChar = '*';
			this.password_textBox.Size = new System.Drawing.Size(261, 20);
			this.password_textBox.TabIndex = 2;
			this.password_textBox.UseSystemPasswordChar = true;
			// 
			// timeZone_comboBox
			// 
			this.timeZone_comboBox.FormattingEnabled = true;
			this.timeZone_comboBox.Location = new System.Drawing.Point(98, 133);
			this.timeZone_comboBox.Name = "timeZone_comboBox";
			this.timeZone_comboBox.Size = new System.Drawing.Size(261, 21);
			this.timeZone_comboBox.TabIndex = 3;
			// 
			// first_name_label
			// 
			this.first_name_label.Location = new System.Drawing.Point(20, 84);
			this.first_name_label.Name = "first_name_label";
			this.first_name_label.Size = new System.Drawing.Size(100, 23);
			this.first_name_label.TabIndex = 1;
			this.first_name_label.Text = "First Name";
			// 
			// last_name_label
			// 
			this.last_name_label.Location = new System.Drawing.Point(20, 110);
			this.last_name_label.Name = "last_name_label";
			this.last_name_label.Size = new System.Drawing.Size(100, 23);
			this.last_name_label.TabIndex = 1;
			this.last_name_label.Text = "Last Name";
			// 
			// first_name_textBox
			// 
			this.first_name_textBox.Location = new System.Drawing.Point(98, 81);
			this.first_name_textBox.Name = "first_name_textBox";
			this.first_name_textBox.Size = new System.Drawing.Size(261, 20);
			this.first_name_textBox.TabIndex = 2;
			// 
			// last_name_textBox
			// 
			this.last_name_textBox.Location = new System.Drawing.Point(98, 107);
			this.last_name_textBox.Name = "last_name_textBox";
			this.last_name_textBox.Size = new System.Drawing.Size(261, 20);
			this.last_name_textBox.TabIndex = 2;
			// 
			// RegistrationView
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(405, 196);
			this.Controls.Add(this.timeZone_comboBox);
			this.Controls.Add(this.last_name_textBox);
			this.Controls.Add(this.first_name_textBox);
			this.Controls.Add(this.password_textBox);
			this.Controls.Add(this.login_textBox);
			this.Controls.Add(this.label3);
			this.Controls.Add(this.last_name_label);
			this.Controls.Add(this.first_name_label);
			this.Controls.Add(this.password_label);
			this.Controls.Add(this.login_label);
			this.Controls.Add(this.submit_button);
			this.Name = "RegistrationView";
			this.Text = "Registration";
			this.Controls.SetChildIndex(this.submit_button, 0);
			this.Controls.SetChildIndex(this.login_label, 0);
			this.Controls.SetChildIndex(this.password_label, 0);
			this.Controls.SetChildIndex(this.first_name_label, 0);
			this.Controls.SetChildIndex(this.last_name_label, 0);
			this.Controls.SetChildIndex(this.label3, 0);
			this.Controls.SetChildIndex(this.login_textBox, 0);
			this.Controls.SetChildIndex(this.password_textBox, 0);
			this.Controls.SetChildIndex(this.first_name_textBox, 0);
			this.Controls.SetChildIndex(this.last_name_textBox, 0);
			this.Controls.SetChildIndex(this.timeZone_comboBox, 0);
			this.ResumeLayout(false);
			this.PerformLayout();

		}
	}
}
