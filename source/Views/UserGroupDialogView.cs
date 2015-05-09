using System;
using System.Net.Mime;
using System.Windows.Forms;
using Course_project.Utils;

namespace Course_project.Views
{
	public partial class UserGroupDialogView : Form
	{
		private RequestParameters group;
		public UserGroupDialogView(RequestParameters group, ViewMode mode)
		{
			InitializeComponent();
			submit_button.DialogResult = DialogResult.OK;

			if(mode == ViewMode.EDIT_MODE){
			 	this.Text = "Edit Group";
				this.group_textBox.Text = group.GetParameter<String>("Old Group");
			}
			else if(mode == ViewMode.ADD_MODE){
				this.Text = "Add Group";
			}
			this.group = group;
		}
		void Submit_buttonClick(object sender, EventArgs e)
		{
			this.group.AddParameter<String>("Group", this.group_textBox.Text);
			this.Close();
		}
	}
}
