using System;
using System.Windows.Forms;

namespace Lab3 {
    public partial class Form1 : Form {
        private SKeyServer _sKeyServer = new SKeyServer();
        private KdcServer _kdcServer = new KdcServer();

        public Form1() {
            InitializeComponent();
            _kdcServer.Show();
        }

        bool ValidateInputValues() {
            if (tbxPassword.Text == "") {
                MessageBox.Show("Please enter the password");
                return false;
            }
            if (tbxName.Text == "") {
                MessageBox.Show("Please enter the data filename");
                return false;
            }
            return true;
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

        private void btnRegisterOnServer_Click(object sender, EventArgs e) {
            if (ValidateInputValues()) {
                tbxLog.Text += "  --Register-- Name = " + tbxName.Text + "\r\n";
                if (_sKeyServer.Register(tbxName.Text, new System.Text.ASCIIEncoding().GetBytes(tbxPassword.Text))) {
                    tbxLog.Text += "  --Register-- Success registration: Name = " + tbxName.Text + "\r\n";
                } else {
                    tbxLog.Text += "  --Register-- Registration failed: Name = " + tbxName.Text + "\r\n";
                }
                tbxLog.Text += "\r\n\r\n";
            }
        }

        private void btnIdentificateClient_Click(object sender, EventArgs e) {
            if (ValidateInputValues()) {
                tbxLog.Text += "  --TryConnect-- Name = " + tbxName.Text + "\r\n";
                var N = _sKeyServer.TryConnect(tbxName.Text);
                switch (N) {
                    case -1:
                        {
                            tbxLog.Text += "  --TryConnect-- TryConnection failed: Name = " + tbxName.Text + "\r\n";
                            break;
                        }
                    case 0:
                        {
                            tbxLog.Text += "  --TryConnect-- TryConnection failed(please reregister): Name = " +
                                           tbxName.Text + "\r\n";
                            break;
                        }
                    default:
                        {
                            tbxLog.Text += "  --TryConnect-- Success TryConnection: Name = " + tbxName.Text +
                                           "\r\n";
                            var key = GenerateKey(new System.Text.ASCIIEncoding().GetBytes(tbxPassword.Text), N);

                            tbxLog.Text += "  --Connect-- Name = " + tbxName.Text + "\r\n";
                            if (_sKeyServer.Connect(tbxName.Text, key)) {
                                tbxLog.Text += "  --Connect-- Success connection: Name = " + tbxName.Text + " N=" + N +
                                           "\r\n";

                                tbxLog.Text += "\r\n";
                                return;
                            }
                            tbxLog.Text += "  --Connect-- Connection failed: Name = " + tbxName.Text +
                                           "\r\n";
                            break;
                        }
                }
                tbxLog.Text += "\r\n\r\n";
            }
        }

        bool ValidateKerberosInputValues() {
            if (tbxPassordKerberos.Text == "") {
                MessageBox.Show("Please enter the password");
                return false;
            }
            if (tbxNameKerberos.Text == "") {
                MessageBox.Show("Please enter the data filename");
                return false;
            }
            return true;
        }

        private void btnIdentificateKerberos_Click(object sender, EventArgs e) {
            if (ValidateKerberosInputValues()) {
                name = Crypto.HashData(new System.Text.ASCIIEncoding().GetBytes(tbxNameKerberos.Text));
                server = Crypto.HashData(new System.Text.ASCIIEncoding().GetBytes("Server1"));
                password = Crypto.HashData(new System.Text.ASCIIEncoding().GetBytes(tbxPassordKerberos.Text));
                ConnectToAs();
            }
        }
        
        private static byte[] name;
        private static byte[] password;
        private static byte[] server;

        byte[] Merge(byte[] ar1, byte[] ar2) {
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

        byte[] GenerateCertificate(byte[] name, byte[] keyTgs) {
            var time = BitConverter.GetBytes(DateTime.Now.TimeOfDay.TotalMilliseconds);
            var certificate = Merge(name, time);
            return CryptoKerberos.EncryptData(certificate, keyTgs);
        }

        void ConnectToServer(byte[] data) {
            tbxLogKerberos.Text += "\r\nClient: try connect to server \r\n";
            var res = _kdcServer.ServerTryConnect(data, server);

            tbxLogKerberos.Text += (res.Length > 0
                                    ? ": connection to server successfull \r\n"
                                    : ": connection to server failed \r\n");
        }

        void ConnectToTgs(byte[] data) {
            tbxLogKerberos.Text += "\r\nClient: try connect to TGS \r\n";
            var res = _kdcServer.TgsTryConnect(data);
            if (res.Length > 0) {
                tbxLogKerberos.Text += ": connection to TGS successfull \r\n";

                var cryptKeySTgs = new byte[24];
                var mondat = new byte[56];
                Array.ConstrainedCopy(res, 0, cryptKeySTgs, 0, cryptKeySTgs.Length);
                Array.ConstrainedCopy(res, 24, mondat, 0, mondat.Length);

                var keySTgs = CryptoKerberos.DecryptData(cryptKeySTgs, password);
                var certificate = GenerateCertificate(name, keySTgs);

                ConnectToServer(Merge(certificate, mondat)); ;
            } else
                tbxLogKerberos.Text += ": connection to TGS failed \r\n";

        }

        void ConnectToAs() {
            tbxLogKerberos.Text += ": try connect to AS \r\n";
            var res = _kdcServer.AsTryConnect(name);
            if (res.Length > 0) {
                tbxLogKerberos.Text += ": connection to AS successfull \r\n";
                var cryptKeyTgs = new byte[24];
                var mondat = new byte[56];
                Array.ConstrainedCopy(res, 0, cryptKeyTgs, 0, cryptKeyTgs.Length);
                Array.ConstrainedCopy(res, 24, mondat, 0, mondat.Length);

                var keyTgs = CryptoKerberos.DecryptData(cryptKeyTgs, password);
                var certificate = GenerateCertificate(name, keyTgs);

                ConnectToTgs(Merge(certificate, Merge(mondat, server)));
            } else
                tbxLogKerberos.Text += ": connection to AS failed \r\n";
        }

        private void tabControl1_SelectedIndexChanged(object sender, EventArgs e) {
            switch (tabControl1.SelectedIndex) {
                case 0:
                    _sKeyServer.Hide();
                    _kdcServer.Show();
                    Focus();
                    break;
                case 1:
                    _kdcServer.Hide();
                    _sKeyServer.Show();
                    Focus();
                    break;
            }
        }
    }
}
