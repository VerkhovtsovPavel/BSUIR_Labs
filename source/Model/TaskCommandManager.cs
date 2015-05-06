/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 5/6/2015
 * Time: 21:47
 */
using System;
using Course_project.Controller;
using Course_project.Utils;

namespace Course_project.Model
{
	/// <summary>
	/// Description of TaskCommandManager.
	/// </summary>
	public class TaskCommandManager
	{
		
		private readonly CommandStore commandStore;
		public TaskCommandManager()
		{
			commandStore = new CommandStore();
		}
		
		public object process(CommandTypes commandType, RequestParameters parameters){
			return commandStore.getCommand(commandType).execute(parameters);
		}
	}
}
