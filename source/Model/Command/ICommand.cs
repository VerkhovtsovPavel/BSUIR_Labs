using System;
using Course_project.Utils;

namespace Course_project.Model.Command
{
	public interface ICommand
	{
		object Execute(RequestParameters parameters);
	}
}
