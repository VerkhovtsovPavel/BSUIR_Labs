namespace Course_project.Model
{
	using System;
	using System.Collections.Generic;
	using Course_project.Controller;
	using Course_project.Model.Command;

	public class CommandStore
	{
		private readonly Dictionary<CommandType, ICommand> commands;
		
		public CommandStore()
		{
			this.commands = new Dictionary<CommandType, ICommand>();
			
			this.AddCommand(CommandType.ADD_PRIVATE_TASK, new AddPrivateTaskCommand());
			this.AddCommand(CommandType.ADD_SHARE_TASK,	new ShareTaskCommand());
			this.AddCommand(CommandType.LOGIN, new LoginCommand());
			this.AddCommand(CommandType.REGISTRATION, new RegistrateCommand());
			this.AddCommand(CommandType.GET_TASKS_FROM_RANGE, new GetTasksFromRangeCommand());
			this.AddCommand(CommandType.IMPORT_TO_OUTLOOK, new ImportTaskInOutlookCommand());
			this.AddCommand(CommandType.ADD_FLEXIBLE_TASK_IN_STORE, new AddFlexibleTaskCommand());
			this.AddCommand(CommandType.UPDATE_GROUPS, new UpdateGroupsCommand());
			this.AddCommand(CommandType.GET_USER_GROUPS, new GetUserGroupsCommand());
			this.AddCommand(CommandType.GET_TIME_SPENT_BY_GROUP, new GetTimeSpentByGroup());
			this.AddCommand(CommandType.UPDATE_TASK, new UpdateTaskCommand());
			this.AddCommand(CommandType.REMOVE_TASK, new RemoveTaskCommand());
			this.AddCommand(CommandType.APPORTION_FLEXIBLE_TASKS, new ApportionFlexibleTasksCommand());
		}
		
		public void AddCommand(CommandType commandType, ICommand command)
		{
			this.commands.Add(commandType, command);
		}
		
		public ICommand GetCommand(CommandType commandType)
		{
			ICommand command;
			this.commands.TryGetValue(commandType, out command);
			return command;
		}
	}
}
