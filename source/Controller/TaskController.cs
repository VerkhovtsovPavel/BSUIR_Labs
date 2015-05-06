/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 5/6/2015
 * Time: 21:20
 */
using System;
using System.Windows.Input;
using Course_project.Exception;
using Course_project.Model;
using Course_project.Utils;

namespace Course_project.Controller
{
	/// <summary>
	/// Description of Class1.
	/// </summary>Class1
	public class TaskController : IController
	{
		private static TaskController instance;
		private TaskCommandManager commandManager;
		
		public static TaskController getInstance(){
			if(instance == null){
				instance = new TaskController();
			}
			
			return instance;
		}
		
		private TaskController()
		{
			commandManager = new TaskCommandManager();
		}

		#region IController implementation

		public object process(string request, Course_project.Utils.RequestParameters parameters)
		{
			try{
				CommandTypes commandType = EnumUtils.ToEnum<CommandTypes>(request);
				return commandManager.process(commandType, parameters);
			}catch(System.Exception){
				//TODO Change Exception on real exception type
				throw new IncorectCommandException();
			}
			
		}

		#endregion
	}
	
	
	
}
