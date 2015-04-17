/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 04/17/2015
 * Time: 19:35
 * 
 * To change this template use Tools | Options | Coding | Edit Standard Headers.
 */
namespace Course_project.Views
{
	partial class LoginView
	{
		/// <summary>
		/// Designer variable used to keep track of non-visual components.
		/// </summary>
		private System.ComponentModel.IContainer components = null;
		private System.Windows.Forms.Button login_submit_button;
		private System.Windows.Forms.TextBox login_textBox;
		private System.Windows.Forms.TextBox password_textBox;
		private System.Windows.Forms.Label login_label;
		private System.Windows.Forms.Label password_label;
		private System.Windows.Forms.MenuStrip menuStrip1;
		private System.Windows.Forms.ToolStripMenuItem registrationToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem exitToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem toolStripMenuItem1;
		
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
		/// 
		private void InitializeComponent()
		{
			this.login_submit_button = new System.Windows.Forms.Button();
			this.login_textBox = new System.Windows.Forms.TextBox();
			this.password_textBox = new System.Windows.Forms.TextBox();
			this.login_label = new System.Windows.Forms.Label();
			this.password_label = new System.Windows.Forms.Label();
			this.menuStrip1 = new System.Windows.Forms.MenuStrip();
			this.toolStripMenuItem1 = new System.Windows.Forms.ToolStripMenuItem();
			this.registrationToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.exitToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.menuStrip1.SuspendLayout();
			this.SuspendLayout();
			// 
			// login_submit_button
			// 
			this.login_submit_button.Location = new System.Drawing.Point(182, 126);
			this.login_submit_button.Name = "login_submit_button";
			this.login_submit_button.Size = new System.Drawing.Size(75, 23);
			this.login_submit_button.TabIndex = 0;
			this.login_submit_button.Text = "Submit";
			this.login_submit_button.UseVisualStyleBackColor = true;
			this.login_submit_button.Click += new System.EventHandler(this.Login_submit_buttonClick);
			// 
			// login_textBox
			// 
			this.login_textBox.Location = new System.Drawing.Point(114, 43);
			this.login_textBox.Name = "login_textBox";
			this.login_textBox.Size = new System.Drawing.Size(242, 20);
			this.login_textBox.TabIndex = 1;
			// 
			// password_textBox
			// 
			this.password_textBox.Location = new System.Drawing.Point(114, 80);
			this.password_textBox.Name = "password_textBox";
			this.password_textBox.PasswordChar = '*';
			this.password_textBox.Size = new System.Drawing.Size(242, 20);
			this.password_textBox.TabIndex = 2;
			// 
			// login_label
			// 
			this.login_label.Location = new System.Drawing.Point(27, 40);
			this.login_label.Name = "login_label";
			this.login_label.Size = new System.Drawing.Size(67, 23);
			this.login_label.TabIndex = 3;
			this.login_label.Text = "Login";
			// 
			// password_label
			// 
			this.password_label.Location = new System.Drawing.Point(27, 77);
			this.password_label.Name = "password_label";
			this.password_label.Size = new System.Drawing.Size(67, 23);
			this.password_label.TabIndex = 4;
			this.password_label.Text = "Password";
			// 
			// menuStrip1
			// 
			this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
			this.toolStripMenuItem1});
			this.menuStrip1.Location = new System.Drawing.Point(0, 0);
			this.menuStrip1.Name = "menuStrip1";
			this.menuStrip1.Size = new System.Drawing.Size(427, 24);
			this.menuStrip1.TabIndex = 5;
			this.menuStrip1.Text = "menuStrip1";
			// 
			// toolStripMenuItem1
			// 
			this.toolStripMenuItem1.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
			this.registrationToolStripMenuItem,
			this.exitToolStripMenuItem});
			this.toolStripMenuItem1.Name = "toolStripMenuItem1";
			this.toolStripMenuItem1.Size = new System.Drawing.Size(37, 20);
			this.toolStripMenuItem1.Text = "File";
			// 
			// registrationToolStripMenuItem
			// 
			this.registrationToolStripMenuItem.Name = "registrationToolStripMenuItem";
			this.registrationToolStripMenuItem.Size = new System.Drawing.Size(137, 22);
			this.registrationToolStripMenuItem.Text = "Registration";
			this.registrationToolStripMenuItem.Click += new System.EventHandler(this.RegistrationToolStripMenuItemClick);
			// 
			// exitToolStripMenuItem
			// 
			this.exitToolStripMenuItem.Name = "exitToolStripMenuItem";
			this.exitToolStripMenuItem.Size = new System.Drawing.Size(137, 22);
			this.exitToolStripMenuItem.Text = "Exit";
			this.exitToolStripMenuItem.Click += new System.EventHandler(this.ExitToolStripMenuItemClick);
			// 
			// LoginView
			// 
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.None;
			this.ClientSize = new System.Drawing.Size(427, 188);
			this.Controls.Add(this.password_label);
			this.Controls.Add(this.login_label);
			this.Controls.Add(this.password_textBox);
			this.Controls.Add(this.login_textBox);
			this.Controls.Add(this.login_submit_button);
			this.Controls.Add(this.menuStrip1);
			this.MainMenuStrip = this.menuStrip1;
			this.Name = "LoginView";
			this.Text = "Login";
			this.menuStrip1.ResumeLayout(false);
			this.menuStrip1.PerformLayout();
			this.ResumeLayout(false);
			this.PerformLayout();

		}
	}
}
