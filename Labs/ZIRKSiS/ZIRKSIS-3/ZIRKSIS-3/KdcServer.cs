using System;
using System.Collections.Generic;
using System.Data;
using System.IO;
using System.Linq;
using System.Windows.Forms;

namespace Lab3 {
    public partial class KdcServer : Form {
        static List<Client> clients = new List<Client>();
        static List<Client> servers = new List<Client>();
        private static byte[] KeyAsTgs = Crypto.HashData(new byte[] { 1, 2, 3, 4, 5 });

        public KdcServer() {
            InitializeComponent();
            clients = ReadInfoFromFile("Clients.txt");
            servers = ReadInfoFromFile("Servers.txt");
        }

        class Client {
            public byte[] Name;
            public byte[] Key;
            public byte[] KeyTgs;
            public byte[] KeySTgs;
            public Client(byte[] name, byte[] key) {
                Name = name;
                Key = key;
            }
        }

        static List<Client> ReadInfoFromFile(string fileName) {
            var result = new List<Client>();
            using (var fsr = new FileStream(fileName, FileMode.Open, FileAccess.Read))
            using (var sr = new StreamReader(fsr)) {
                while (sr.Peek() > -1) {
                    var words = sr.ReadLine().Split(new char[] { ',' }, StringSplitOptions.RemoveEmptyEntries);
                    var name = Crypto.HashData(new System.Text.ASCIIEncoding().GetBytes(words[0]));
                    var key = Crypto.HashData(new System.Text.ASCIIEncoding().GetBytes(words[1]));
                    var el = new Client(name, key);
                    result.Add(el);
                }

            }
            return result;
        }

        bool Compare(byte[] ar1, byte[] ar2) {
            if (ar1.Length != ar2.Length)
                return false;
            return !ar1.Where((t, i) => t != ar2[i]).Any();
        }

        private int GetIndexByName(List<Client> list, byte[] name) {
            for (var i = 0; i < list.Count; i++) {
                if (Compare(list[i].Name, name))
                    return i;
            }
            return -1;
        }

        private byte[] Merge(byte[] ar1, byte[] ar2) {
            var result = new byte[ar1.Length + ar2.Length];
            var i = 0;
            for (i = 0; i < ar1.Length; i++) {
                result[i] = ar1[i];
            }
            foreach (byte t in ar2) {
                result[i] = t;
                i++;
            }
            return result;
        }

        private byte[] GenerateTgsMondat(byte[] name, byte[] ip, byte[] keyTgs, byte[] keyAsTgs) {
            var time = BitConverter.GetBytes(DateTime.Now.TimeOfDay.TotalMilliseconds);
            var mondat = Merge(name, Merge(ip, Merge(time, keyTgs)));
            return CryptoKerberos.EncryptData(mondat, keyAsTgs);
        }


        public byte[] AsTryConnect(byte[] name) {
            var clientIndex = GetIndexByName(clients, name);
            if (clientIndex > -1) {
                var r = new Random();
                clients[clientIndex].KeyTgs =
                    Crypto.HashData(new System.Text.ASCIIEncoding().GetBytes(r.Next().ToString()));
                var cryptKeyTgs = CryptoKerberos.EncryptData(clients[clientIndex].KeyTgs, clients[clientIndex].Key);
                var ip = new byte[] { 127, 0, 0, 1 };

                tbxLog.Text += "AS -- TryConnect successfull \r\n\r\n";
                var mondat = GenerateTgsMondat(clients[clientIndex].Name, ip, clients[clientIndex].KeyTgs,
                                         KeyAsTgs);
                return Merge(cryptKeyTgs, mondat);
            } else {
                tbxLog.Text += "AS  -- TryConnect failed \r\n\r\n";
                return new byte[0];
            }
        }

        public byte[] TgsTryConnect(byte[] data) {
            var certificate = new byte[32];
            var clientMondat = new byte[56];
            var server = new byte[20];
            Array.ConstrainedCopy(data, 0, certificate, 0, certificate.Length);
            Array.ConstrainedCopy(data, 32, clientMondat, 0, clientMondat.Length);
            Array.ConstrainedCopy(data, 88, server, 0, server.Length);

            var decryptMondat = CryptoKerberos.DecryptData(clientMondat, KeyAsTgs);
            var clientName2 = new byte[20];
            var ip = new byte[4];
            var time2 = new byte[8];
            var keyTgs = new byte[20];
            Array.ConstrainedCopy(decryptMondat, 0, clientName2, 0, clientName2.Length);
            Array.ConstrainedCopy(decryptMondat, 20, ip, 0, ip.Length);
            Array.ConstrainedCopy(decryptMondat, 24, time2, 0, time2.Length);
            Array.ConstrainedCopy(decryptMondat, 32, keyTgs, 0, keyTgs.Length);

            var decryptCertificate = CryptoKerberos.DecryptData(certificate, keyTgs);
            var clientName1 = new byte[20];
            var time1 = new byte[8];
            Array.ConstrainedCopy(decryptCertificate, 0, clientName1, 0, clientName1.Length);
            Array.ConstrainedCopy(decryptCertificate, 20, time1, 0, time1.Length);



            if (!Compare(clientName1, clientName2)) {
                tbxLog.Text += "TGS -- TryConnect failed \r\n\r\n";
                return new byte[0];
            }

            var ping = BitConverter.ToDouble(time1, 0) - BitConverter.ToDouble(time2, 0);
            if (ping > 25000) {
                tbxLog.Text += "TGS -- TryConnect failed \r\n\r\n";
                return new byte[0];
            }

            if (!Compare(ip, new byte[] { 127, 0, 0, 1 })) {
                tbxLog.Text += "TGS -- TryConnect failed \r\n\r\n";
                return new byte[0];
            }

            var clientIndex = GetIndexByName(clients, clientName1);
            var serverIndex = GetIndexByName(servers, server);
            if ((clientIndex > -1) && (serverIndex > -1)) {
                var r = new Random();
                clients[clientIndex].KeySTgs =
                    Crypto.HashData(new System.Text.ASCIIEncoding().GetBytes(r.Next().ToString()));
                var cryptKeySTgs = CryptoKerberos.EncryptData(clients[clientIndex].KeySTgs, clients[clientIndex].Key);

                tbxLog.Text += "TGS -- TryConnect successfull \r\n\r\n";
                var mondat = GenerateTgsMondat(clients[clientIndex].Name, ip, clients[clientIndex].KeySTgs,
                                         servers[serverIndex].Key);
                return Merge(cryptKeySTgs, mondat);
            }
            tbxLog.Text += "TGS -- TryConnect failed \r\n\r\n";
            return new byte[0];
        }


        public byte[] ServerTryConnect(byte[] data, byte[] server) {
            var serverIndex = GetIndexByName(servers, server);

            var certificate = new byte[32];
            var clientMondat = new byte[56];
            Array.ConstrainedCopy(data, 0, certificate, 0, certificate.Length);
            Array.ConstrainedCopy(data, 32, clientMondat, 0, clientMondat.Length);

            var decryptMondat = CryptoKerberos.DecryptData(clientMondat, servers[serverIndex].Key);
            var clientName2 = new byte[20];
            var ip = new byte[4];
            var time2 = new byte[8];
            var keyTgs = new byte[20];
            Array.ConstrainedCopy(decryptMondat, 0, clientName2, 0, clientName2.Length);
            Array.ConstrainedCopy(decryptMondat, 20, ip, 0, ip.Length);
            Array.ConstrainedCopy(decryptMondat, 24, time2, 0, time2.Length);
            Array.ConstrainedCopy(decryptMondat, 32, keyTgs, 0, keyTgs.Length);

            var decryptCertificate = CryptoKerberos.DecryptData(certificate, keyTgs);
            var clientName1 = new byte[20];
            var time1 = new byte[8];
            Array.ConstrainedCopy(decryptCertificate, 0, clientName1, 0, clientName1.Length);
            Array.ConstrainedCopy(decryptCertificate, 20, time1, 0, time1.Length);

            if (!Compare(clientName1, clientName2)) {
                tbxLog.Text += "Serv -- TryConnect failed \r\n\r\n";
                return new byte[0];
            }

            var ping = BitConverter.ToDouble(time1, 0) - BitConverter.ToDouble(time2, 0);
            if (ping > 25000) {
                tbxLog.Text += "Serv -- TryConnect failed \r\n\r\n";
                return new byte[0];
            }

            if (!Compare(ip, new byte[] { 127, 0, 0, 1 })) {
                tbxLog.Text += "Serv -- TryConnect failed \r\n\r\n";
                return new byte[0];
            }

            tbxLog.Text += "Serv -- TryConnect successfull \r\n\r\n";
            return BitConverter.GetBytes(BitConverter.ToDouble(time1, 0) - 1);
        }
    }
}
