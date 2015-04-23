/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 4/23/2015
 * Time: 15:45
 */
namespace OSiSP_5.Forms
{
	partial class Titles
	{
		/// <summary>
		/// Designer variable used to keep track of non-visual components.
		/// </summary>
		private System.ComponentModel.IContainer components = null;
		private System.Windows.Forms.ListBox titles_lb;
		
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
			this.titles_lb = new System.Windows.Forms.ListBox();
			this.SuspendLayout();
			// 
			// titles_lb
			// 
			this.titles_lb.FormattingEnabled = true;
			this.titles_lb.Location = new System.Drawing.Point(12, 12);
			this.titles_lb.Name = "titles_lb";
			this.titles_lb.Size = new System.Drawing.Size(260, 173);
			this.titles_lb.TabIndex = 0;
			this.titles_lb.SelectedIndexChanged += new System.EventHandler(this.Titles_lbSelectedIndexChanged);
			// 
			// Titles
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(284, 193);
			this.Controls.Add(this.titles_lb);
			this.Name = "Titles";
			this.Text = "Titles";
			this.ResumeLayout(false);

		}
		
		private void setTitle(string title){
			this.Text = title;
		}
	}
}
