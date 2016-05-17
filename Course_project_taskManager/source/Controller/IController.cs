namespace Course_project.Controller
{
	using System;
	using Course_project.Utils;

	public interface IController
	{
		object Process(string request, RequestParameters parameters);
	}
}
