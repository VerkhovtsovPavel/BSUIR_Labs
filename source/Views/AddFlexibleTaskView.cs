/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 4/29/2015
 * Time: 21:33
 */
using System;
using System.Drawing;

namespace Course_project.Views
{
	/// <summary>
	/// Description of Form1.
	/// </summary>
	public partial class AddFlexibleTaskView : AddHardTaskView
	{
		public AddFlexibleTaskView()
		{
			InitializeComponent();
			this.submit_button.Location = new Point(150, 280);
		}
		void Label1Click(object sender, EventArgs e)
		{
	
		}
		void NumericUpDown1ValueChanged(object sender, EventArgs e)
		{
	
		}
	}
}
