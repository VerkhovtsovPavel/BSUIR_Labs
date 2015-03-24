/*
 * Created by SharpDevelop.
 * User: user
 * Date: 08.03.2015
 * Time: 11:41
 * 
 * To change this template use Tools | Options | Coding | Edit Standard Headers.
 */
using System;

namespace OSiSP_2
{
	class Program
	{
		public static void Main()
		{
			ThreadPool threadPool = new ThreadPool(10, 100, 30);

			for(int taskIndex = 0; taskIndex < 100; taskIndex++){
				threadPool.addTask(new PrintTask(Convert.ToString(taskIndex)));
			}
		}
	}
}