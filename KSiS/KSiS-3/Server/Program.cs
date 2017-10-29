using System;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using System.Threading;

namespace Server
{
    class Program
    {
        private static Socket socketTimeServer, socketDayTimeServer;

        static void Main(string[] args)
        {
            Thread threadTime = new Thread(TimeThreadFunction);
            Thread threadDateTime = new Thread(DayTimeThreadFunction);

            try
            {
                threadTime.Start();
                threadDateTime.Start();

                Console.WriteLine("Press ENTER to close program");
                Console.ReadLine();

                socketTimeServer.Close();
                socketDayTimeServer.Close();
            }
            catch
            {
                Console.WriteLine("Во время выполнения программы произошла ошибка");
            }
        }

        private static void TimeThreadFunction()
        {
            try
            {
                socketTimeServer = SocketFactory.CreateTimeServerSocket();

                while (true)
                {
                    Socket handler = socketTimeServer.Accept();

                    handler.Send(ConvertToNtp((decimal)((DateTime.UtcNow - new DateTime(1900, 1, 1, 0, 0, 0)).TotalMilliseconds)));

                    Console.WriteLine(string.Format("{0}:{1}:{2} Сервер отправил ответ на Time запрос", DateTime.Now.Hour, DateTime.Now.Minute, DateTime.Now.Second));

                    handler.Shutdown(SocketShutdown.Both);
                    handler.Close();
                }
            }
            catch
            {
                return;
            }
        }

        private static void DayTimeThreadFunction()
        {
            try
            {
                socketDayTimeServer = SocketFactory.CreateDayTimeServerSocket();

                while (true)
                {
                    Socket handler = socketDayTimeServer.Accept();

                    handler.Send(Encoding.UTF8.GetBytes(string.Format("{0} {1}-{2}-{3} {4}:{5}:{6} UTC", DateTime.UtcNow.DayOfWeek, DateTime.UtcNow.Year, DateTime.UtcNow.Month, DateTime.UtcNow.Day, DateTime.UtcNow.Hour, DateTime.UtcNow.Minute, DateTime.UtcNow.Second)));
                    Console.WriteLine(string.Format("{0}:{1}:{2} Сервер отправил ответ на DayTime запрос", DateTime.Now.Hour, DateTime.Now.Minute, DateTime.Now.Second));

                    handler.Shutdown(SocketShutdown.Both);
                    handler.Close();
                }
            }
            catch
            {
                return;
            }
        }

        public static byte[] ConvertToNtp(decimal milliseconds)
        {
            decimal intpart = 0, fractpart = 0;
            var ntpData = new byte[8];

            intpart = milliseconds / 1000;
            fractpart = ((milliseconds % 1000) * 0x100000000L) / 1000m;

            var temp = intpart;
            for (var i = 3; i >= 0; i--)
            {
                ntpData[i] = (byte)(temp % 256);
                temp = temp / 256;
            }

            temp = fractpart;
            for (var i = 7; i >= 4; i--)
            {
                ntpData[i] = (byte)(temp % 256);
                temp = temp / 256;
            }
            return ntpData;
        }
    }
}
