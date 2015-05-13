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

		public object Process(CommandType request, Course_project.Utils.RequestParameters parameters)
		{
			return this.commandManager.process(request, parameters);
		}
	}
}