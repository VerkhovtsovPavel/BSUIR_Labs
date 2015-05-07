/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 5/6/2015
 * Time: 21:20
 */
using System;
using Course_project.Model;


namespace Course_project.Controller
{
	/// <summary>
	/// Description of Class1.
	/// </summary>Class1
	public class TaskController
	{
		private static TaskController instance;
		private TaskCommandManager commandManager;
		
		public static TaskController getInstance()
		{
			if (instance == null) {
				instance = new TaskController();
			}
			
			return instance;
		}
		
		private TaskController()
		{
			commandManager = new TaskCommandManager();
		}

		#region IController implementation
		public object process(CommandType request, Course_project.Utils.RequestParameters parameters)
		{
			return commandManager.process(request, parameters);
		}
		#endregion

	}
}

