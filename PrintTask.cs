/*
 * Created by SharpDevelop.
 * User: user
 * Date: 19.03.2015
 * Time: 9:12
 * 
 * To change this template use Tools | Options | Coding | Edit Standard Headers.
 */
using System;

namespace OSiSP_2
{
	/// <summary>
	/// Description of Class1.
	/// </summary>
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
