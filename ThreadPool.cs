/*
 * Created by SharpDevelop.
 * User: user
 * Date: 08.03.2015
 * Time: 11:43
 * 
 * To change this template use Tools | Options | Coding | Edit Standard Headers.
 */

using System.Collections.Generic;
using System.Threading;

namespace OSiSP_2
{
    /// <summary>
    /// Description of ThreadPool.
    /// </summary>
    public class ThreadPool
    {
        private int minThreads;
        private int maxThreads;
        private int threadLifeTime;
        private List<Thread> threads = new List<Thread>();

        private Queue<Task> taskQueue = new Queue<Task>();

        public ThreadPool(int minThreads, int maxTreads, int lifeTime)
        {
            this.minThreads = minThreads;
			this.maxThreads = maxTreads;
            threadLifeTime = lifeTime;
        }

        public int GetMinThreads()
        {
            return minThreads;
        }

        public int GetMaxThreads()
        {
			return maxThreads;
        }

        public int getThreadLifeTime()
        {
			return threadLifeTime;
        }

        public void initialazeMinThread()
        {
            for (int i = 0; i < this.minThreads; i++)
            {
                threads.Add(null);
            }
        }

        public void addTask(Task task)
        {
            taskQueue.Enqueue(task);
        }


    }
}