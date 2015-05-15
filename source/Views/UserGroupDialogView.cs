namespace Course_project.Views
{
	using System;
	using System.Windows.Forms;
	using Course_project.Utils;

	public partial class UserGroupDialogView : Form
	{
		private RequestParameters group;
		
		public UserGroupDialogView(RequestParameters group, ViewMode mode)
		{
			this.InitializeComponent();
			this.submit_button.DialogResult = DialogResult.OK;

			if(mode == ViewMode.EDIT_MODE)
			{
			 	this.Text = "Edit Group";
				this.group_textBox.Text = group.GetParameter<string>("Old Group");
			}
			else if(mode == ViewMode.ADD_MODE)
			{
				this.Text = "Add Group";
			}
			
			this.group = group;
		}
		
		private void Submit_buttonClick(object sender, EventArgs e)
		{
			this.group.AddParameter<string>("Group", this.group_textBox.Text);
			this.Close();
		}
	}
}
