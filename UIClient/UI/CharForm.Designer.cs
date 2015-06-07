/*
 * Создано в SharpDevelop.
 * Пользователь: Pavel_Verkhovtsov
 * Дата: 5/27/2015
 * Время: 18:27
 */
namespace OSiSP_6.UI
{
	partial class CharForm
	{
		/// <summary>
		/// Designer variable used to keep track of non-visual components.
		/// </summary>
		private System.ComponentModel.IContainer components = null;
		private System.Windows.Forms.TextBox textBox1;
		private System.Windows.Forms.Button Send_button;
		private System.Windows.Forms.TextBox textBox2;
		
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
			this.textBox1 = new System.Windows.Forms.TextBox();
			this.Send_button = new System.Windows.Forms.Button();
			this.textBox2 = new System.Windows.Forms.TextBox();
			this.SuspendLayout();
			// 
			// textBox1
			// 
			this.textBox1.Location = new System.Drawing.Point(12, 219);
			this.textBox1.Name = "textBox1";
			this.textBox1.Size = new System.Drawing.Size(517, 20);
			this.textBox1.TabIndex = 0;
			// 
			// Send_button
			// 
			this.Send_button.Location = new System.Drawing.Point(545, 219);
			this.Send_button.Name = "Send_button";
			this.Send_button.Size = new System.Drawing.Size(112, 23);
			this.Send_button.TabIndex = 1;
			this.Send_button.Text = "Send";
			this.Send_button.UseVisualStyleBackColor = true;
			// 
			// textBox2
			// 
			this.textBox2.Location = new System.Drawing.Point(12, 12);
			this.textBox2.Multiline = true;
			this.textBox2.Name = "textBox2";
			this.textBox2.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
			this.textBox2.Size = new System.Drawing.Size(645, 184);
			this.textBox2.TabIndex = 2;
			// 
			// CharForm
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(669, 262);
			this.Controls.Add(this.textBox2);
			this.Controls.Add(this.Send_button);
			this.Controls.Add(this.textBox1);
			this.MaximumSize = new System.Drawing.Size(685, 300);
			this.MinimumSize = new System.Drawing.Size(685, 300);
			this.Name = "CharForm";
			this.Text = "CharForm";
			this.ResumeLayout(false);
			this.PerformLayout();

		}
	}
}
