using System;
using System.Collections.Generic;
using Course_project.Entity;
using Course_project.Storage;
using Course_project.Utils;

namespace Course_project.Model.Command
{

	public class AddFlexibleTaskCommand : ICommand
	{

		public object Execute(RequestParameters parameters)
		{
			FlexibleTasksStorage.getInstance().addTask(parseFlexibleTaskParameters(parameters));
			return true;
		}

	
		private FlexibleTask parseFlexibleTaskParameters(RequestParameters parameters)
		{
			FlexibleTask flexibleTask = new FlexibleTask();
			
			flexibleTask.Owner = Session.GetSession().UserName;
			
			flexibleTask.StartTime = TimeUtils.DateTimeToUnixTime(parameters.GetParameter<DateTime>("StartTime"));
			flexibleTask.EndTime = TimeUtils.DateTimeToUnixTime(parameters.GetParameter<DateTime>("StopTime"));
			

			flexibleTask.Group = parameters.GetParameter<String>("Group");
			flexibleTask.Title = parameters.GetParameter<String>("Title");
			
			flexibleTask.MaxParts = parameters.GetParameter<int>("MaxParts");
			flexibleTask.MinTimeOfOnePart = parameters.GetParameter<int>("MinTimeFromPart");
			
			//TODO Get task by title or store title array
			flexibleTask.DependedTasks = parameters.GetParameter<List<String>>("DependentTasks");
			
		}
	}
}
