namespace Course_project.Model
{
	using System;
	using Course_project.Controller;
	using Course_project.Utils;

	public class TaskCommandManager
	{
		private readonly CommandStore commandStore;
		
		public TaskCommandManager()
		{
			this.commandStore = new CommandStore();
		}
		
		public object Process(CommandType commandType, RequestParameters parameters)
		{
			return this.commandStore.GetCommand(commandType).Execute(parameters);
		}
	}
}
