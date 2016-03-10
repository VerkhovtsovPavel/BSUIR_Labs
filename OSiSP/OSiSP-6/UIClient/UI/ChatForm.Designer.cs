/*
 * Создано в SharpDevelop.
 * Пользователь: Pavel_Verkhovtsov
 * Дата: 5/27/2015
 * Время: 18:27
 */
namespace OSiSP_6.UI
{
	partial class ChatForm
	{
		/// <summary>
		/// Designer variable used to keep track of non-visual components.
		/// </summary>
		private System.ComponentModel.IContainer components = null;
		private System.Windows.Forms.TextBox message_textBox;
		private System.Windows.Forms.Button Send_button;
		public static System.Windows.Forms.TextBox chat_textBox = new System.Windows.Forms.TextBox();
		
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
			this.message_textBox = new System.Windows.Forms.TextBox();
			this.Send_button = new System.Windows.Forms.Button();
			this.SuspendLayout();
			// 
			// message_textBox
			// 
			this.message_textBox.Location = new System.Drawing.Point(12, 219);
			this.message_textBox.Name = "message_textBox";
			this.message_textBox.Size = new System.Drawing.Size(517, 20);
			this.message_textBox.TabIndex = 0;
			// 
			// Send_button
			// 
			this.Send_button.Location = new System.Drawing.Point(545, 219);
			this.Send_button.Name = "Send_button";
			this.Send_button.Size = new System.Drawing.Size(112, 23);
			this.Send_button.TabIndex = 1;
			this.Send_button.Text = "Send";
			this.Send_button.UseVisualStyleBackColor = true;
			this.Send_button.Click += new System.EventHandler(this.Send_buttonClick);
			// 
			// ChatForm
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(669, 262);
			this.Controls.Add(chat_textBox);
			this.Controls.Add(this.Send_button);
			this.Controls.Add(this.message_textBox);
			this.MaximumSize = new System.Drawing.Size(685, 300);
			this.MinimumSize = new System.Drawing.Size(685, 300);
			this.Name = "ChatForm";
			this.Text = "CharForm";
			this.ResumeLayout(false);
			this.PerformLayout();

		}
		
		private static void ChatTextBoxInit()
		{
			// 
			// chat_textBox
			// 
			chat_textBox.Location = new System.Drawing.Point(12, 12);
			chat_textBox.Multiline = true;
			chat_textBox.Name = "chat_textBox";
			chat_textBox.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
			chat_textBox.Size = new System.Drawing.Size(645, 184);
			chat_textBox.TabIndex = 2;
		}
	}
}
