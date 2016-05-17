namespace Course_project.Model.Command
{
	using System;
	using Course_project.Utils;
	
	public interface ICommand
	{
		object Execute(RequestParameters parameters);
	}
}
