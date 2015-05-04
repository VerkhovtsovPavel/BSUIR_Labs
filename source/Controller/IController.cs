using System;
using Course_project.Utils;

namespace Course_project.Controller
{
	public interface IController
	{
		object process(String request, RequestParameters parameters);
	}
}
