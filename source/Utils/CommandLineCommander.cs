/*
 * Created by SharpDevelop.
 * User: VerkhovtsovPavel
 * Date: 04.05.2015
 * Time: 16:34
 */
using System;
using System.Diagnostics;
using System.Text;

namespace Course_project.Utils
{
	/// <summary>
	/// Description of CommandLineCommander.
	/// </summary>
	public static class CommandLineCommander
	{
		public static Process executeCommand(string command)
		{
                ProcessStartInfo procStartInfo = new ProcessStartInfo("cmd", "/c " + command);
                procStartInfo.RedirectStandardOutput = true;
                procStartInfo.UseShellExecute = false;
                procStartInfo.CreateNoWindow = true;
 
                Process proc = new Process();
                procStartInfo.StandardOutputEncoding = Encoding.GetEncoding(866);
                proc.StartInfo = procStartInfo;
                proc.Start();
				
                return proc;
		}
	}
}
