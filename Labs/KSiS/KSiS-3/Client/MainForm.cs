using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Client
{
    public partial class MainForm : Form
    {
        #region Public methods
        public MainForm()
        {
            InitializeComponent();
        }
        #endregion

        #region Private methods
        #region Event handlers
        private void button1_Click(object sender, EventArgs e)
        {
            try
            {
                MessageBox.Show(ToTimeString(RequestToService(IP.Text, 37)));
            }
            catch
            {
                MessageBox.Show("Ошибка при обращении к серверу");
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            try
            {
                MessageBox.Show(ToDayTimeString(RequestToService(IP.Text, 13)));
            }
            catch
            {
                MessageBox.Show("Ошибка при обращении к серверу");
            }
        }
        #endregion

        #region Converters
        private string ToTimeString(byte[] bytes)
        {
            ulong intPart = SwapEndianness(BitConverter.ToUInt32(bytes, 0));
            ulong fractPart = SwapEndianness(BitConverter.ToUInt32(bytes, 4));

            ulong milliseconds = (intPart * 1000) + ((fractPart * 1000) / 0x100000000L);

            DateTime localTime = (new DateTime(1900, 1, 1, 0, 0, 0, DateTimeKind.Utc)).AddMilliseconds((long)milliseconds).ToLocalTime();

            return string.Format("{0}-{1}-{2} {3}:{4}:{5}", localTime.Year, localTime.Month, localTime.Day, localTime.Hour, localTime.Minute, localTime.Second);
        }

        private string ToDayTimeString(byte[] bytes)
        {
            return Encoding.UTF8.GetString(bytes, 0, bytes.Count());
        }
        #endregion

        #region Helpers
        private byte[] RequestToService(string ip, int port)
        {
            try
            {
                IPHostEntry ipHost = Dns.GetHostEntry(ip);
                IPAddress ipAddr = ipHost.AddressList[0];
                IPEndPoint ipEndPoint = new IPEndPoint(ipAddr, port);

                Socket socket = new Socket(ipAddr.AddressFamily, SocketType.Stream, ProtocolType.Tcp);

                socket.Connect(ipEndPoint);

                socket.Send(new byte[] { });

                socket.ReceiveTimeout = 10000;

                byte[] data = new byte[1024];
                socket.Receive(data);

                socket.Shutdown(SocketShutdown.Both);
                socket.Close();

                return data;
            }
            catch
            {
                throw;
            }
        }

        private uint SwapEndianness(ulong x)
        {
            return (uint)(((x & 0x000000ff) << 24) + ((x & 0x0000ff00) << 8) + ((x & 0x00ff0000) >> 8) + ((x & 0xff000000) >> 24));
        }
        #endregion
        #endregion
    }
}
