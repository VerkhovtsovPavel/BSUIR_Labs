using System.Collections.Generic;
using System.Windows.Forms;

namespace Lab3 {
    public partial class SKeyServer : Form {
        public SKeyServer() {
            InitializeComponent();
        }

        private const int DEFAULT_N = 5;

        private class Client {
            public string Name;
            public byte[] Key;
            public int N;

            public Client(string name, byte[] key, int n) {
                Name = name;
                Key = key;
                N = n;
            }
        }

        List<Client> clients = new List<Client>();

        private int GetClientIndexByName(string name) {
            for (int i = 0; i < clients.Count; i++) {
                if (clients[i].Name == name)
                    return i;
            }
            return -1;
        }

        private byte[] GenerateKey(byte[] key, int N) {
            if (key.Length > -1) {
                var newKey = Crypto.HashData(key);
                for (int i = 0; i < N - 1; i++) {
                    newKey = Crypto.HashData(newKey);
                }
                return newKey;
            }
            return key;
        }

        private bool RemoveClient(int index) {
            if ((index >= 0) && (index < clients.Count)) {
                clients.RemoveAt(index);
                return true;
            }
            return false;
        }

        private bool CompareKeys(byte[] key1, byte[] key2) {
            if (key1.Length == key2.Length) {
                for (int i = 0; i < key1.Length; i++) {
                    if (key1[i] != key2[i]) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }


        public bool Register(string name, byte[] key) {
            if ((name.Length > 3) && (key.Length > 5) && (GetClientIndexByName(name) == -1)) {
                var newKey = GenerateKey(key, DEFAULT_N);
                var newClient = new Client(name, newKey, DEFAULT_N);
                clients.Add(newClient);
                tbxLog.Text += "--Register-- Client added: Name = " + name + ", N = " + DEFAULT_N.ToString() + "\r\n\r\n";
                return true;
            }
            tbxLog.Text += "--Register-- Failed client add\r\n\r\n";
            return false;
        }

        public int TryConnect(string name) {
            var clientIndex = GetClientIndexByName(name);
            if (clientIndex > -1) {
                if ((clients[clientIndex].N - 1) == 0) {
                    tbxLog.Text += "--TryConnect-- Client removed: Name = " + name + ", N = " +
                                   (clients[clientIndex].N - 1).ToString() + "\r\n\r\n";
                    RemoveClient(clientIndex);
                    return 0;
                }
                tbxLog.Text += "--TryConnect-- Return N to client: Name = " + name + ", N = " +
                                   (clients[clientIndex].N - 1).ToString() + "\r\n\r\n";
                return clients[clientIndex].N - 1;
            }
            tbxLog.Text += "--TryConnect-- Failed client: Name = " + name + "\r\n\r\n";
            return -1;
        }

        public bool Connect(string name, byte[] key) {
            var clientIndex = GetClientIndexByName(name);
            if (clientIndex > -1) {
                var newKey = GenerateKey(key, 1);
                if (CompareKeys(newKey, clients[clientIndex].Key)) {
                    clients[clientIndex].Key = key;
                    clients[clientIndex].N--;
                    tbxLog.Text += "--Connect-- Client connected: Name = " + name + ", newN = " +
                                   (clients[clientIndex].N).ToString() + "\r\n\r\n";
                    return true;
                }
            }
            tbxLog.Text += "--Connect-- Failed connection: Name = " + name + "\r\n\r\n";
            return false;
        }
    }
}
