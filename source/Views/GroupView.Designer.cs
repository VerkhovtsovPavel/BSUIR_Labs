/*
 * Created by SharpDevelop.
 * User: VerkhovtsovPavel
 * Date: 07.05.2015
 * Time: 12:52
 * 
 * To change this template use Tools | Options | Coding | Edit Standard Headers.
 */
namespace Course_project.Views
{
	partial class GroupView
	{
		/// <summary>
		/// Designer variable used to keep track of non-visual components.
		/// </summary>
		private System.ComponentModel.IContainer components = null;
		private System.Windows.Forms.Button addGroup_button;
		private System.Windows.Forms.Button deleteGroup_button;
		private System.Windows.Forms.ListBox group_listBox;
		private System.Windows.Forms.Button editGroup_button;
		
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
			this.addGroup_button = new System.Windows.Forms.Button();
			this.deleteGroup_button = new System.Windows.Forms.Button();
			this.group_listBox = new System.Windows.Forms.ListBox();
			this.editGroup_button = new System.Windows.Forms.Button();
			this.SuspendLayout();
			// 
			// addGroup_button
			// 
			this.addGroup_button.Location = new System.Drawing.Point(12, 113);
			this.addGroup_button.Name = "addGroup_button";
			this.addGroup_button.Size = new System.Drawing.Size(118, 23);
			this.addGroup_button.TabIndex = 0;
			this.addGroup_button.Text = "Add group";
			this.addGroup_button.UseVisualStyleBackColor = true;
			this.addGroup_button.Click += new System.EventHandler(this.AddGroup_buttonClick);
			// 
			// deleteGroup_button
			// 
			this.deleteGroup_button.Location = new System.Drawing.Point(280, 113);
			this.deleteGroup_button.Name = "deleteGroup_button";
			this.deleteGroup_button.Size = new System.Drawing.Size(122, 23);
			this.deleteGroup_button.TabIndex = 1;
			this.deleteGroup_button.Text = "Delete group";
			this.deleteGroup_button.UseVisualStyleBackColor = true;
			this.deleteGroup_button.Click += new System.EventHandler(this.DeleteGroup_buttonClick);
			// 
			// group_listBox
			// 
			this.group_listBox.FormattingEnabled = true;
			this.group_listBox.Location = new System.Drawing.Point(12, 12);
			this.group_listBox.Name = "group_listBox";
			this.group_listBox.Size = new System.Drawing.Size(390, 95);
			this.group_listBox.TabIndex = 2;
			// 
			// editGroup_button
			// 
			this.editGroup_button.Location = new System.Drawing.Point(136, 113);
			this.editGroup_button.Name = "editGroup_button";
			this.editGroup_button.Size = new System.Drawing.Size(138, 23);
			this.editGroup_button.TabIndex = 3;
			this.editGroup_button.Text = "Edit group";
			this.editGroup_button.UseVisualStyleBackColor = true;
			this.editGroup_button.Click += new System.EventHandler(this.EditGroup_buttonClick);
			// 
			// GroupEditView
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(414, 150);
			this.Controls.Add(this.editGroup_button);
			this.Controls.Add(this.group_listBox);
			this.Controls.Add(this.deleteGroup_button);
			this.Controls.Add(this.addGroup_button);
			this.Name = "GroupEditView";
			this.Text = "GroupEditView";
			this.ResumeLayout(false);

		}
	}
}
