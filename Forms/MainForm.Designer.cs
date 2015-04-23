/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 4/23/2015
 * Time: 13:45
 * 
 * To change this template use Tools | Options | Coding | Edit Standard Headers.
 */
namespace OSiSP_5.Forms
{
	partial class MainForm
	{
		/// <summary>
		/// Designer variable used to keep track of non-visual components.
		/// </summary>
		private System.ComponentModel.IContainer components = null;
		private System.Windows.Forms.Button download_btn;
		private System.Windows.Forms.Label url_lbl;
		private System.Windows.Forms.TextBox url_txt;
		
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
			this.download_btn = new System.Windows.Forms.Button();
			this.url_lbl = new System.Windows.Forms.Label();
			this.url_txt = new System.Windows.Forms.TextBox();
			this.SuspendLayout();
			// 
			// download_btn
			// 
			this.download_btn.Location = new System.Drawing.Point(176, 61);
			this.download_btn.Name = "download_btn";
			this.download_btn.Size = new System.Drawing.Size(75, 23);
			this.download_btn.TabIndex = 0;
			this.download_btn.Text = "Download ";
			this.download_btn.UseVisualStyleBackColor = true;
			this.download_btn.Click += new System.EventHandler(this.downloadButtonClick);
			// 
			// url_lbl
			// 
			this.url_lbl.Location = new System.Drawing.Point(11, 22);
			this.url_lbl.Name = "url_lbl";
			this.url_lbl.Size = new System.Drawing.Size(52, 23);
			this.url_lbl.TabIndex = 1;
			this.url_lbl.Text = "URL:";
			// 
			// url_txt
			// 
			this.url_txt.Location = new System.Drawing.Point(53, 22);
			this.url_txt.Name = "url_txt";
			this.url_txt.Size = new System.Drawing.Size(386, 20);
			this.url_txt.TabIndex = 2;
			// 
			// MainForm
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(453, 96);
			this.Controls.Add(this.url_txt);
			this.Controls.Add(this.url_lbl);
			this.Controls.Add(this.download_btn);
			this.Name = "MainForm";
			this.Text = "RSS Reader";
			this.ResumeLayout(false);
			this.PerformLayout();

		}
	}
}
