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
    	 private System.Object queueLock = new System.Object();
    	
    	
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
            	threads.Add(new Thread(ThreadFunction));
            }
        }

        public void addTask(Task task)
        {
        	lock(queueLock){
            	taskQueue.Enqueue(task);
        	}
        }
        
        public Task getTask()
        {
        	lock(queueLock){
        		return taskQueue.Peek();
        	}
        }
        
        private void checkThreads(){
        	foreach(Thread thread in threads){
        		if(!thread.IsAlive){
        			threads.Remove(thread);
        		}
        	}
        }
        
        private void ThreadFunction(){
        	int sleepTime = 0;
        	while(sleepTime<this.threadLifeTime){
	        	Task task = getTask();
	        	if(task==null)
	        	{
	        		Thread.Sleep(1000);
	        		sleepTime++;
	        	}
	        	else
	        	{
	        		sleepTime=0;
	        		task.process();
	       		}	
        	}	
        }


    }
}