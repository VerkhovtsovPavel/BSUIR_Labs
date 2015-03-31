using System;

namespace OSiSP_2
{

	public class PrintTask : ITask
	{
		
		private readonly string _text;
		
		public PrintTask(string text)
		{
			_text = text;
		}

	    public void Process()
		{
			Console.WriteLine (_text);
		}
	}
}
