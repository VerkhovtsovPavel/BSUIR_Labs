/*
 * Created by SharpDevelop.
 * User: VerkhovtsovPavel
 * Date: 04.05.2015
 * Time: 16:34
 */
using System;
using System.Diagnostics;

namespace Course_project.Utils
{
	/// <summary>
	/// Description of CommandLineCommander.
	/// </summary>
	public static class CommandLineCommander
	{
		public static void executeCommand(string command)
		{
			Process process = new Process();
			ProcessStartInfo startInfo = new ProcessStartInfo();
			startInfo.WindowStyle = ProcessWindowStyle.Hidden;
			startInfo.FileName = "cmd.exe";
			startInfo.Arguments = "/C "+command;
			process.StartInfo = startInfo;
			process.Start();
		}
	}
}
