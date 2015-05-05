﻿/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 4/29/2015
 * Time: 21:26
 */
using System;
using Course_project.Controller;
using Course_project.Utils;

namespace Course_project.Views
{
	/// <summary>
	/// Description of Form1.
	/// </summary>
	public partial class AddHardTaskView : MainView{
		private AddTaskController addTaskController;
		
		public AddHardTaskView()
		{
			InitializeComponent();
			this.fileToolStripMenuItem.Enabled = false;
			this.addTaskController = new AddTaskController();
		}
		void Submit_buttonClick(object sender, EventArgs e)
		{
			RequestParameters hardTaskParameters = new RequestParameters();
			
			hardTaskParameters.addString("Title", this.title_tb.Text);
			hardTaskParameters.addString("Group", this.group_comboBox.Text);
			
			hardTaskParameters.addDateTime("StartTime", this.start_dateTimePicker.Value);
			hardTaskParameters.addDateTime("StopTime", this.stop_dateTimePicker.Value);
			
			addTaskController.process("addHardTask" , hardTaskParameters);
			
		}
	}
}
