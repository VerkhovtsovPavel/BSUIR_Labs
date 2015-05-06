/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 5/6/2015
 * Time: 21:48
 */
using System;
using Course_project.Utils;

namespace Course_project.Model
{
	/// <summary>
	/// Description of ICommand.
	/// </summary>
	public interface ICommand
	{
		object execute(RequestParameters parameters);
	}
}
