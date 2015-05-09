/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 5/5/2015
 * Time: 21:44
 */
using System;
using System.Drawing;
using System.Windows.Forms;
using Course_project.Controller;
using Course_project.Entity.DB;
using Course_project.Utils;

namespace Course_project.Views
{
	/// <summary>
	/// Description of ProfilingView.
	/// </summary>
	public partial class ProfilingView : MainView
	{
		public ProfilingView()
		{
			InitializeComponent();
			this.fileToolStripMenuItem.Enabled = false;
			
			fillGroupComboBox();
		}
		void SpentTime_buttonClick(object sender, EventArgs e)
		{
			string group = this.group_comboBox.Text;
			if (group.Equals("")) {
				MessageBox.Show("Please select group");
			} else {
				RequestParameters requestParameters = new RequestParameters();
				requestParameters.AddParameter<String>("Group", this.group_comboBox.Text);
				int spentTime = (int)TaskController.GetInstance().Process(CommandType.GET_TIME_SPENT_BY_GROUP, requestParameters);
				MessageBox.Show("On tasks with " + this.group_comboBox.Text + " group spent " + spentTime + " minute");
			}
		}

		void fillGroupComboBox()
		{
			UserGroups userGroups = (UserGroups)TaskController.GetInstance().Process(CommandType.GET_USER_GROUPS, null);	
			foreach (String group in userGroups.Groups) {
				this.group_comboBox.Items.Add(group);
			}
		}
	}
}
