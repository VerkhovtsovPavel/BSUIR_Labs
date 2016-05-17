namespace Course_project.Model.Command
{
	using System;
	using System.Collections.Generic;
	using Course_project.Entity;
	using Course_project.Storage;
	using Course_project.Utils;

	public class AddFlexibleTaskCommand : ICommand
	{
		public object Execute(RequestParameters parameters)
		{
			FlexibleTasksStorage.GetInstance().AddTask(this.ParseFlexibleTaskParameters(parameters));
			FlexibleTasksStorage.GetInstance().UpdateTasksDependents();
			return true;
		}

		private FlexibleTask ParseFlexibleTaskParameters(RequestParameters parameters)
		{
			FlexibleTask flexibleTask = new FlexibleTask();
			
			flexibleTask.Owner = Session.GetSession().UserName;
			
			flexibleTask.StartTime = TimeUtils.DateTimeToUnixTime(parameters.GetParameter<DateTime>("StartTime"));
			flexibleTask.EndTime = TimeUtils.DateTimeToUnixTime(parameters.GetParameter<DateTime>("StopTime"));
			
			flexibleTask.Group = parameters.GetParameter<string>("Group");
			flexibleTask.Title = parameters.GetParameter<string>("Title");
			
			flexibleTask.RequiredTime = parameters.GetParameter<int>("RequestedTime") * 60;
			flexibleTask.MaxParts = parameters.GetParameter<int>("MaxParts");
			flexibleTask.MinTimeOfOnePart = parameters.GetParameter<int>("MinTimeFromPart");
			
			flexibleTask.DependedTasks =  parameters.GetParameter<List<FlexibleTask>>("DependentTasks");
			
			return flexibleTask;
		}
	}
}
