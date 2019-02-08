using System;
using System.IO;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Windows.Forms;

namespace Lab2 {
    public partial class Form1 : Form {
        private readonly DigitalSignatureHelper _ds = new DigitalSignatureHelper();

        public Form1() {
            InitializeComponent();
        }

        private byte[] GetIV() {
            var result = Encoding.UTF8.GetBytes(Environment.UserName);
            return result;
        }

        private void buttonValidate_Click(object sender, EventArgs e) {
            var key = textBoxKey.Text;
            string messageAndHash = textBoxOutMessage.Text;
            string message = messageAndHash.Substring(0, messageAndHash.IndexOf("~~~", StringComparison.Ordinal));
            string inHash = messageAndHash.Substring(messageAndHash.IndexOf("~~~", StringComparison.Ordinal)+3);
         
            string outHash = string.Empty;
            if (radioButton1.Checked) {
				var hasher = SHA1.Create();
                byte[] inputBytes2 = Encoding.UTF8.GetBytes(key + message);
                byte[] hashmessage = hasher.ComputeHash(inputBytes2);
                outHash = ByteToString(hashmessage);
            } else {
                byte[] keyByte = Encoding.UTF8.GetBytes(key);
                HMACMD5 hmacmd5 = new HMACMD5(keyByte);
                byte[] message2Bytes = Encoding.UTF8.GetBytes(message);
                byte[] hashmessage = hmacmd5.ComputeHash(message2Bytes);
                outHash = ByteToString(hashmessage);
            }

            MessageBox.Show(!inHash.Equals(outHash)
                ? "Сообщения отличаются"
                : "Сообщения идентичны");
        }
        
         private void buttonHash_Click(object sender, EventArgs e) {
            var key = textBoxKey.Text;
            string message = textBoxInMessage.Text;
				
            if (radioButton1.Checked) {
                var hasher = SHA1.Create();
                byte[] inputBytes = Encoding.UTF8.GetBytes(key + message);
                byte[] hashmessage = hasher.ComputeHash(inputBytes);
                textBoxOutMessage.Text = message + "~~~" + ByteToString(hashmessage);;
              } else {
                byte[] keyByte = Encoding.UTF8.GetBytes(key);
                HMACMD5 hmacmd5 = new HMACMD5(keyByte);
                byte[] messageBytes = Encoding.UTF8.GetBytes(message);
                byte[] hashmessage = hmacmd5.ComputeHash(messageBytes);
                textBoxOutMessage.Text = message + "~~~" + ByteToString(hashmessage);;
            }
        }
        
        

        public static string ByteToString(byte[] buff) {
            string sbinary = buff.Aggregate("", (current, t) => current + t.ToString("X2"));

            return (sbinary);
        }

        private void buttonSaveDigSign_Click(object sender, EventArgs e) {
            if (saveFileDialog1.ShowDialog() == DialogResult.OK) {
                textBoxSaveDigSign.Text = saveFileDialog1.FileName;
            }
        }

        private void buttonCreateSign_Click(object sender, EventArgs e) {
            if (!string.IsNullOrEmpty(textBoxSaveDigSign.Text)) {
                byte[] hash = HashHelper.GetHash(GetIV());
                byte[] signedhash = _ds.CreateSignature(hash);

                var fsOut = new FileStream(textBoxSaveDigSign.Text, FileMode.OpenOrCreate, FileAccess.Write);
                fsOut.Write(signedhash, 0, signedhash.Length);
                fsOut.Close();
                MessageBox.Show("Цифровая подпись сгенерирована");
            } else {
                MessageBox.Show("Выберите путь сохранения цифровой подписи");
            }
        }

        private void buttonOpenFile_Click(object sender, EventArgs e) {
            openFileDialog1.FilterIndex = 1;
            if (openFileDialog1.ShowDialog() == DialogResult.OK) {
                textBoxOpenFile.Text = openFileDialog1.FileName;
            }
        }

        private void buttonOpenDigSign_Click(object sender, EventArgs e) {
            openFileDialog1.FilterIndex = 2;
            if (openFileDialog1.ShowDialog() == DialogResult.OK) {
                textBoxOpenDigSign.Text = openFileDialog1.FileName;
            }
        }

        private void buttonSignFile_Click(object sender, EventArgs e) {
            if (!string.IsNullOrEmpty(textBoxOpenFile.Text) || !File.Exists(textBoxOpenFile.Text)) {
                if (!string.IsNullOrEmpty(textBoxOpenDigSign.Text) || !File.Exists(textBoxOpenDigSign.Text)) {
                    string file = Path.GetDirectoryName(textBoxOpenFile.Text) + @"\" +
                                  Path.GetFileNameWithoutExtension(textBoxOpenFile.Text) + "_SIGNED" +
                                  Path.GetExtension(textBoxOpenFile.Text);
                    using (FileStream fsOut = new FileStream(file, FileMode.OpenOrCreate, FileAccess.Write)) {
                        int bufferLen = 4096;
                        using (FileStream fsIn = new FileStream(textBoxOpenDigSign.Text, FileMode.Open, FileAccess.Read)) {
                            byte[] sign = new byte[bufferLen];
                            int bytesRead;
                            do {
                                bytesRead = fsIn.Read(sign, 0, bufferLen);
                                fsOut.Write(sign, 0, bytesRead);
                            } while (bytesRead != 0);
                            fsIn.Close();

                            byte[] delimeter = Encoding.UTF8.GetBytes("||");
                            fsOut.Write(delimeter, 0, delimeter.Length);
                        }
                        using (var fsIn = new FileStream(textBoxOpenFile.Text, FileMode.Open, FileAccess.Read)) {
                            byte[] buffer = new byte[bufferLen];
                            int bytesRead;
                            do {
                                bytesRead = fsIn.Read(buffer, 0, bufferLen);
                                fsOut.Write(buffer, 0, bytesRead);
                            } while (bytesRead != 0);
                        }
                    }
                    MessageBox.Show("Файл подписан");
                } else {
                    MessageBox.Show("Необходимо выбрать файл цифроваой подписи");
                }
            } else {
                MessageBox.Show("Необходимо выбрать файл для подписывания");
            }
        }

        private void buttonOpenCheckFile_Click(object sender, EventArgs e) {
            openFileDialog1.FilterIndex = 1;
            if (openFileDialog1.ShowDialog() == DialogResult.OK) {
                textBoxOpenCheckFile.Text = openFileDialog1.FileName;
            }
        }

        private void buttonOpenCheckSign_Click(object sender, EventArgs e) {
            openFileDialog1.FilterIndex = 2;
            if (openFileDialog1.ShowDialog() == DialogResult.OK) {
                textBoxOpenCheckSign.Text = openFileDialog1.FileName;
            }
        }

        private void buttonCheckSign_Click(object sender, EventArgs e) {
            if (!string.IsNullOrEmpty(textBoxOpenCheckFile.Text) || !File.Exists(textBoxOpenCheckFile.Text)) {
                if (!string.IsNullOrEmpty(textBoxOpenCheckSign.Text) || !File.Exists(textBoxOpenCheckSign.Text)) {
                    if (!string.IsNullOrEmpty(textBoxCheckUserName.Text)) {
                        FileStream fsIn = new FileStream(textBoxOpenCheckSign.Text, FileMode.Open, FileAccess.Read);
                        int bufferLen = 2048;
                        byte[] buffer = new byte[bufferLen];
                        int bytesRead;
                        int counter = 0;
                        do {
                            bytesRead = fsIn.Read(buffer, 0, bufferLen);
                            counter += bytesRead;
                        } while (bytesRead != 0);
                        fsIn.Close();
                        byte[] sign = new byte[counter];
                        for (int i = 0; i < counter; i++) {
                            sign[i] = buffer[i];
                        }

                        fsIn = new FileStream(textBoxOpenCheckFile.Text, FileMode.Open, FileAccess.Read);
                        byte[] bufferFile = new byte[bufferLen];
                        do {
                            bytesRead = fsIn.Read(bufferFile, 0, bufferLen);
                            counter += bytesRead;
                        } while (bytesRead != 0);
                        fsIn.Close();
                        string fromFile = Encoding.UTF8.GetString(bufferFile);
                        fromFile = fromFile.Substring(0, fromFile.IndexOf("||", StringComparison.Ordinal));
                        string signValue = Encoding.UTF8.GetString(sign);
                        
                        byte[] hash = HashHelper.GetHash(textBoxCheckUserName.Text);
                        if (signValue.Equals(fromFile) && _ds.VerifySignature(hash, sign)) {
                            MessageBox.Show("Подпись в документе совпадает.");
                        } else {
                            MessageBox.Show("Подпись в документе не совпадает.");
                        }
                    } else {
                        MessageBox.Show("Необходимо ввести имя пользователя");
                    }
                } else {
                    MessageBox.Show("Необходимо выбрать файл цифроваой подписи");
                }
            } else {
                MessageBox.Show("Необходимо выбрать файл для подписывания");
            }
        }
		void TextBoxKeyTextChanged(object sender, EventArgs e)
		{
	
		}
    }
}
