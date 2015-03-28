/*
 * Created by SharpDevelop.
 * User: Pavel_Verkhovtsov
 * Date: 3/27/2015
 * Time: 20:22
 * 
 * To change this template use Tools | Options | Coding | Edit Standard Headers.
 */
 using System.Threading;
 using System;
namespace OSiSP_2
{
class MyThread 
        {
            private Thread thread;

            public MyThread(string name) //Конструктор получает имя функции и номер до кторого ведется счет
            {

                thread = new Thread(func);
                thread.Name = name;

            }

    public void Start(int num)
    {
        thread.Start(num);
    }

    void func(object num)//Функция потока, передаем параметр
            {
                for (int i = 0;i < (int)num;i++ ) 
                {
                    Console.WriteLine(Thread.CurrentThread.Name + " выводит " + i);
                    Thread.Sleep(0);
                }
                Console.WriteLine(Thread.CurrentThread.Name + " завершился");
            }
        
        
             
             
        static void Main(string[] args)
        {
            var t1 = new MyThread("Thread 1");
            t1.Start(6);
            var t2 = new MyThread("Thread 2");
            t2.Start(3);
            var t3 = new MyThread("Thread 3");
            t3.Start(2);

            Console.Read();
        }
    }
}