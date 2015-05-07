/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 5/6/2015
 * Time: 21:20
 */
namespace Course_project.Controller
{
using System;
using Course_project.Model;

	public class TaskController
	{
		private static TaskController instance;
		private TaskCommandManager commandManager;
		
		private TaskController()
		{
			this.commandManager = new TaskCommandManager();
		}
		
		public static TaskController GetInstance()
		{
			if (instance == null)
			{
				instance = new TaskController();
			}
			
			return instance;
		}

		#region IController implementation
		public object Process(CommandType request, Course_project.Utils.RequestParameters parameters)
		{
			return this.commandManager.process(request, parameters);
		}
		#endregion
	}
}