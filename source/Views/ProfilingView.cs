/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 5/5/2015
 * Time: 21:44
 */
using System;
using System.Drawing;
using System.Windows.Forms;

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
		}
		void SpentTime_buttonClick(object sender, EventArgs e)
		{
			//TODO Add real spent time
			MessageBox.Show("On tasks with "+this.group_comboBox.Text+" spent "+23+" minute");
		}
	}
}
