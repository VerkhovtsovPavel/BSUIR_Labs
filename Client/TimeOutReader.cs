using System;
using System.Threading;

namespace OSiSP_6
{
	public class TimeOutReader
	{
		private static Thread inputThread;
		private static AutoResetEvent getInput, gotInput;
		private static string input = String.Empty;

		static TimeOutReader()
		{
			getInput = new AutoResetEvent(false);
			gotInput = new AutoResetEvent(false);
			inputThread = new Thread(reader);
			inputThread.IsBackground = true;
			inputThread.Start();
		}

		private static void reader()
		{
			while (true) {
				getInput.WaitOne();
				input = Console.ReadLine();
				gotInput.Set();
			}
		}

		public static string ReadLine(int timeOutMillisecs)
		{
			getInput.Set();
			bool success = gotInput.WaitOne(timeOutMillisecs);
			if (success)
				return input;
			else {
				Console.Out.Flush();
				throw new TimeoutException("User did not provide input within the timelimit.");

			}
		}
	}
}
