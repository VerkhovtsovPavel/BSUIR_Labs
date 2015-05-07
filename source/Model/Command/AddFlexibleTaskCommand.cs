using System;
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
			throw new NotImplementedException();
		}
	}
}
