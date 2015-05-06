/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 5/6/2015
 * Time: 21:52
 */
using System;
using System.Collections.Generic;
using MongoDB.Driver;
using Course_project.Controller;

namespace Course_project.Model
{
	/// <summary>
	/// Description of CommandStore.
	/// </summary>
	public class CommandStore
	{
		private Dictionary<CommandType, ICommand> commands;
		
		public CommandStore()
		{
			commands = new Dictionary<CommandType, ICommand>();
		}
		
		public void addCommand(CommandType commandType, ICommand command){
			commands.Add(commandType, command);
		}
		
		public ICommand getCommand(CommandType commandType){
			ICommand command;
			commands.TryGetValue(commandType, out command);
			return command;
		}
	}
}
