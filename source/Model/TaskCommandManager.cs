using System;
using Course_project.Controller;
using Course_project.Utils;

namespace Course_project.Model
{
	public class TaskCommandManager
	{
		private readonly CommandStore commandStore;
		public TaskCommandManager()
		{
			commandStore = new CommandStore();
		}
		
		public object process(CommandType commandType, RequestParameters parameters){
			return commandStore.getCommand(commandType).Execute(parameters);
		}
	}
}
