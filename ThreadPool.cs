/*
 * Created by SharpDevelop.
 * User: user
 * Date: 08.03.2015
 * Time: 11:43
 * 
 * To change this template use Tools | Options | Coding | Edit Standard Headers.
 */

using System;
using System.Collections.Generic;
using System.Threading;

namespace OSiSP_2
{
    /// <summary>
    ///     Description of ThreadPool.
    /// </summary>
    public class ThreadPool
    {
        private readonly int _maxThreads;
        private readonly int _minThreads;
        private readonly int _threadLifeTime;
        private readonly Object queueLock = new Object();

        private readonly Queue<ITask> taskQueue = new Queue<ITask>();
        private readonly List<Thread> threads = new List<Thread>();

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
                }
            }
        }

        private void StartTreads()
        {
            foreach (Thread thread in threads)
            {
                thread.Start();
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
            lock (queueLock)
            {
                taskQueue.Enqueue(task);
            }
        }

        public ITask GetTask()
        {
            lock (queueLock)
            {
                return taskQueue.Peek();
            }
        }

        private void CheckThreads()
        {
            foreach (Thread thread in threads)
            {
                if (!thread.IsAlive)
                {
                    threads.Remove(thread);
                }
            }
        }

        private void ThreadFunction()
        {
            int sleepTime = 0;
            while (sleepTime < _threadLifeTime)
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