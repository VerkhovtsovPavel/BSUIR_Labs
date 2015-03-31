using System;
using System.Threading;

namespace OSiSP_2
{
	class Program
	{
		public static void Main()
		{
			var threadPool = new ThreadPool(10, 100, 3);
			
			new Thread(AddTasks).Start(threadPool);
			threadPool.Start();
		}
		
		
		private static void AddTasks(Object threadPoll){
			var threadPool = (ThreadPool) threadPoll;
			for(int taskIndex = 0; taskIndex < 1000; taskIndex++){
				threadPool.AddTask(new PrintTask(Convert.ToString(taskIndex)));
			}
			
			Thread.Sleep(10000);
			
			for(int taskIndex = 1000; taskIndex < 20000; taskIndex++){
				threadPool.AddTask(new PrintTask(Convert.ToString(taskIndex)));
			}
		}
	}
}