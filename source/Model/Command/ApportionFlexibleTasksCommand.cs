using System;
using System.Collections;
using System.Collections.Generic;
using Course_project.Entity;
using Course_project.Utils;

namespace Course_project.Model.Command
{
	public class ApportionFlexibleTasksCommand : ICommand
	{
		public object Execute(RequestParameters parameters)
		{
			Queue<FlexibleTask> taskQueue = parameters.GetParameter<Queue<FlexibleTask>>("taskQueue");
			
			
			
			return true;
		}
		
		
		/*
		 * foreach(Task)
		 if(task.DependentTasks.Contants(resolvedTask)){
			task.StartTime = Math.Max(task.StartTime, resolvedTask.EndTime)
}
		 */
	}
}
