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
class myThread 
        {
            Thread thread;

            public myThread(string name, int num) //Конструктор получает имя функции и номер до кторого ведется счет
            {

                thread = new Thread(this.func);
                thread.Name = name;
                thread.Start(num);//передача параметра в поток
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
            myThread t1 = new myThread("Thread 1", 6);
            myThread t2 = new myThread("Thread 2", 3);
            myThread t3 = new myThread("Thread 3", 2);

            Console.Read();

        }

    }
}