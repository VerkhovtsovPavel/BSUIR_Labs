/*
 * Создано в SharpDevelop.
 * Пользователь: Pavel_Verkhovtsov
 * Дата: 5/27/2015
 * Время: 18:26
 */
namespace OSiSP_6.UI
{
	partial class LoginForm
	{
		/// <summary>
		/// Designer variable used to keep track of non-visual components.
		/// </summary>
		private System.ComponentModel.IContainer components = null;
		private System.Windows.Forms.Label UserName_label;
		private System.Windows.Forms.TextBox textBox1;
		private System.Windows.Forms.TextBox textBox2;
		private System.Windows.Forms.Label Age_label;
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
			this.UserName_label = new System.Windows.Forms.Label();
			this.textBox1 = new System.Windows.Forms.TextBox();
			this.textBox2 = new System.Windows.Forms.TextBox();
			this.Age_label = new System.Windows.Forms.Label();
			this.Submit_button = new System.Windows.Forms.Button();
			this.SuspendLayout();
			// 
			// UserName_label
			// 
			this.UserName_label.Location = new System.Drawing.Point(12, 9);
			this.UserName_label.Name = "UserName_label";
			this.UserName_label.Size = new System.Drawing.Size(65, 23);
			this.UserName_label.TabIndex = 0;
			this.UserName_label.Text = "User name";
			// 
			// textBox1
			// 
			this.textBox1.Location = new System.Drawing.Point(83, 9);
			this.textBox1.Name = "textBox1";
			this.textBox1.Size = new System.Drawing.Size(209, 20);
			this.textBox1.TabIndex = 1;
			// 
			// textBox2
			// 
			this.textBox2.Location = new System.Drawing.Point(83, 41);
			this.textBox2.Name = "textBox2";
			this.textBox2.Size = new System.Drawing.Size(100, 20);
			this.textBox2.TabIndex = 3;
			// 
			// Age_label
			// 
			this.Age_label.Location = new System.Drawing.Point(12, 38);
			this.Age_label.Name = "Age_label";
			this.Age_label.Size = new System.Drawing.Size(47, 23);
			this.Age_label.TabIndex = 2;
			this.Age_label.Text = "Age";
			// 
			// Submit_button
			// 
			this.Submit_button.Location = new System.Drawing.Point(115, 79);
			this.Submit_button.Name = "Submit_button";
			this.Submit_button.Size = new System.Drawing.Size(84, 22);
			this.Submit_button.TabIndex = 4;
			this.Submit_button.Text = "Submit";
			this.Submit_button.UseVisualStyleBackColor = true;
			// 
			// LoginForm
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(304, 110);
			this.Controls.Add(this.Submit_button);
			this.Controls.Add(this.textBox2);
			this.Controls.Add(this.Age_label);
			this.Controls.Add(this.textBox1);
			this.Controls.Add(this.UserName_label);
			this.MaximumSize = new System.Drawing.Size(320, 148);
			this.MinimumSize = new System.Drawing.Size(320, 148);
			this.Name = "LoginForm";
			this.Text = "Login";
			this.ResumeLayout(false);
			this.PerformLayout();

		}
	}
}
