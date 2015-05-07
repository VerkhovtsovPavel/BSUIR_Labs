using System;
using System.Collections.Generic;
using Course_project.Controller;
using Course_project.Model.Command;

namespace Course_project.Model
{
	public class CommandStore
	{
		private Dictionary<CommandType, ICommand> commands;
		
		public CommandStore()
		{
			commands = new Dictionary<CommandType, ICommand>();
			
			addCommand(CommandType.ADD_PRIVATE_TASK, new AddPrivateTaskCommand());
			addCommand(CommandType.ADD_SHARE_TASK,	new ShareTaskCommand());
			addCommand(CommandType.LOGIN, new LoginCommand());
			addCommand(CommandType.REGISTRATION, new RegistrateCommand());
			addCommand(CommandType.SHOW_TASKS, new GetTasksFromRangeCommand());
			addCommand(CommandType.IMPORT_TO_OUTLOOK, new ImportTaskInOutlookCommand());
			addCommand(CommandType.ADD_FLEXIBLE_TASK_IN_STORE, new AddFlexibleTaskCommand());
		}
		
		public void addCommand(CommandType commandType, ICommand command)
		{
			commands.Add(commandType, command);
		}
		
		public ICommand getCommand(CommandType commandType)
		{
			ICommand command;
			commands.TryGetValue(commandType, out command);
			return command;
		}
	}
}
