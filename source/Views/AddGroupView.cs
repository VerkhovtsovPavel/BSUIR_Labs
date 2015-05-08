using System;
using System.Windows.Forms;
using Course_project.Utils;

namespace Course_project.Views
{
	public partial class AddGroupView : Form
	{
		private RequestParameters group;
		public AddGroupView(RequestParameters group)
		{
			InitializeComponent();
			this.group = group;
		}
		void Submit_buttonClick(object sender, EventArgs e)
		{
			this.group.AddParameter<String>("Group", this.group_textBox.Text);
			this.Close();
		}
	}
}
