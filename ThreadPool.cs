using System;
using System.Collections.Generic;
using System.Threading;

namespace OSiSP_2
{
    public class ThreadPool
    {
        private readonly int _maxThreads;
        private readonly int _minThreads;
        private readonly int _threadLifeTime;

        private readonly Queue<ITask> taskQueue = new Queue<ITask>();
        private List<Thread> threads = new List<Thread>();
        
        public ThreadPool(int minThreads, int maxTreads, int lifeTime)
        {
            _minThreads = minThreads;
            _maxThreads = maxTreads;
            _threadLifeTime = lifeTime;
            InitialazeMinThread();
        }

        public void Start()
        {
            StartTreads();

            while (true)
            {
                CheckThreads();
                if (taskQueue.Count != 0 && threads.Count < _maxThreads)
                {
                    var newThread = new Thread(ThreadFunction);
                    threads.Add(newThread);
                    newThread.Start(false);
                }
                Console.WriteLine("Current Treads count = {0}", threads.Count);
                Thread.Sleep(250);
            }
        }

        private void StartTreads()
        {
            foreach (Thread thread in threads)
            {
                thread.Start(true);
            }
        }

        public int GetMinThreads()
        {
            return _minThreads;
        }

        public int GetMaxThreads()
        {
            return _maxThreads;
        }

        public int GetThreadLifeTime()
        {
            return _threadLifeTime;
        }

        private void InitialazeMinThread()
        {
            for (int i = 0; i < _minThreads; i++)
            {
                threads.Add(new Thread(ThreadFunction));
            }
        }

        public void AddTask(ITask task)
        {
            lock (taskQueue)
            {
                taskQueue.Enqueue(task);
            }
        }

        public ITask GetTask()
        {
            lock (taskQueue)
            {
            	if(taskQueue.Count>0){
            		return taskQueue.Dequeue();
            	}
            	return null;
            }
        }

        private void CheckThreads()
        {
        	var undeadThreads = new List<Thread>(threads);
            foreach (Thread thread in threads)
            {
                if (!thread.IsAlive)
                {
                    undeadThreads.Remove(thread);
                }
            }
            threads = undeadThreads;
        }

        private void ThreadFunction(object isPrimary)
        {
        	bool isPrimaryThread = Convert.ToBoolean(isPrimary);
            int sleepTime = 0;
            while (sleepTime < _threadLifeTime || isPrimaryThread)
            {
                ITask task = GetTask();
                if (task == null)
                {
                    Thread.Sleep(1000);
                    sleepTime++;
                }
                else
                {
                    sleepTime = 0;
                    task.Process();
                }
            }
        }
    }
}