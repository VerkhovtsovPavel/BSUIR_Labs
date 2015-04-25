using System;

namespace Course_project.Controller
{
	public interface IController
	{
		object process(String request, Object parameters);
	}
}
