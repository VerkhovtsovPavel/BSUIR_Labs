/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 4/23/2015
 * Time: 16:10
 */
namespace OSiSP_5.Forms
{
	partial class ArticleView
	{
		/// <summary>
		/// Designer variable used to keep track of non-visual components.
		/// </summary>
		private System.ComponentModel.IContainer components = null;
		private System.Windows.Forms.TextBox note_text;
		
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
			this.note_text = new System.Windows.Forms.TextBox();
			this.SuspendLayout();
			// 
			// note_text
			// 
			this.note_text.Location = new System.Drawing.Point(12, 12);
			this.note_text.Multiline = true;
			this.note_text.Name = "note_text";
			this.note_text.ReadOnly = true;
			this.note_text.Size = new System.Drawing.Size(260, 238);
			this.note_text.TabIndex = 0;
			// 
			// Note
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(284, 262);
			this.Controls.Add(this.note_text);
			this.Name = "Note";
			this.Text = "Note";
			this.ResumeLayout(false);
			this.PerformLayout();

		}
	}
}
