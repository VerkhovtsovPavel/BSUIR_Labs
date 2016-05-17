namespace Course_project.Utils
{
	using System;
	using System.Diagnostics;
	using System.Text;

	public static class CommandLineCommander
	{
		public static Process ExecuteCommand(string command)
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
