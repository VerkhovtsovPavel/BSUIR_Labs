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
		private System.Windows.Forms.TextBox Username_textBox;
		private System.Windows.Forms.Label Age_label;
		private System.Windows.Forms.Button Submit_button;
		private System.Windows.Forms.NumericUpDown Age_numericUpDown;
		
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
			this.Username_textBox = new System.Windows.Forms.TextBox();
			this.Age_label = new System.Windows.Forms.Label();
			this.Submit_button = new System.Windows.Forms.Button();
			this.Age_numericUpDown = new System.Windows.Forms.NumericUpDown();
			((System.ComponentModel.ISupportInitialize)(this.Age_numericUpDown)).BeginInit();
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
			// Username_textBox
			// 
			this.Username_textBox.Location = new System.Drawing.Point(83, 9);
			this.Username_textBox.MaxLength = 32;
			this.Username_textBox.Name = "Username_textBox";
			this.Username_textBox.Size = new System.Drawing.Size(209, 20);
			this.Username_textBox.TabIndex = 1;
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
			this.Submit_button.Click += new System.EventHandler(this.Submit_buttonClick);
			// 
			// Age_numericUpDown
			// 
			this.Age_numericUpDown.Location = new System.Drawing.Point(83, 38);
			this.Age_numericUpDown.Minimum = new decimal(new int[] {
			10,
			0,
			0,
			0});
			this.Age_numericUpDown.Name = "Age_numericUpDown";
			this.Age_numericUpDown.Size = new System.Drawing.Size(58, 20);
			this.Age_numericUpDown.TabIndex = 5;
			this.Age_numericUpDown.Value = new decimal(new int[] {
			10,
			0,
			0,
			0});
			// 
			// LoginForm
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(304, 110);
			this.Controls.Add(this.Age_numericUpDown);
			this.Controls.Add(this.Submit_button);
			this.Controls.Add(this.Age_label);
			this.Controls.Add(this.Username_textBox);
			this.Controls.Add(this.UserName_label);
			this.MaximumSize = new System.Drawing.Size(320, 148);
			this.MinimumSize = new System.Drawing.Size(320, 148);
			this.Name = "LoginForm";
			this.Text = "Login";
			((System.ComponentModel.ISupportInitialize)(this.Age_numericUpDown)).EndInit();
			this.ResumeLayout(false);
			this.PerformLayout();

		}
	}
}
