/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 5/7/2015
 * Time: 21:21
 */
using System;
using Course_project.Entity;
using Course_project.TaskDao;
using Course_project.Utils;

namespace Course_project.Model.Command
{
	/// <summary>
	/// Description of ShareTaskCommand.
	/// </summary>
	public class ShareTaskCommand : ICommand
	{
		public object Execute(RequestParameters parameters)
		{
			Task task = parameters.getTask("Task");
			Dao.getInstance().addSharedTask(task);
			return true;
		}
	}
}
