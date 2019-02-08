using System;
using System.IO;
using System.Windows.Forms;

namespace ZIRKSiS1
{
	public partial class Form1 : Form
	{
		public Form1()
		{
			InitializeComponent();
		}

		private void radioButton2_CheckedChanged(object sender, EventArgs e)
		{
			textBoxUserKey.ReadOnly = !radioButton2.Checked;
		}

		private void buttonOpenFile_Click(object sender, EventArgs e)
		{
			if (openFileDialog1.ShowDialog() == DialogResult.OK) {
				textBox1.Text = openFileDialog1.FileName;
				textBox2.Text = string.Empty;
			}
		}

		private void buttonSaveFile_Click(object sender, EventArgs e)
		{
			if (saveFileDialog1.ShowDialog() == DialogResult.OK) {
				textBox2.Text = saveFileDialog1.FileName;
			}
		}

		private void buttonEncrypt_Click(object sender, EventArgs e)
		{
			if (File.Exists(textBox1.Text)) {
				TripleDES_112 tripleDES = radioButton1.Checked ? new TripleDES_112() : new TripleDES_112(textBoxUserKey.Text);
				string fileName = textBox1.Text;
				if(string.IsNullOrEmpty(textBox2.Text)){
					textBox2.Text = "ENC_"+fileName;	
				}
				try {
					File.WriteAllBytes(textBox2.Text, tripleDES.Encrypt(File.ReadAllBytes(textBox1.Text)));
					MessageBox.Show("Файл зашифрован.", "Сообщение");
				} catch (Exception) {
					MessageBox.Show("Не удалось зашифровать файл");
				}
			}
		}

		private void buttonDecrypt_Click(object sender, EventArgs e)
		{
			if (File.Exists(textBox1.Text)) {
				TripleDES_112 tripleDES = radioButton1.Checked ? new TripleDES_112() : new TripleDES_112(textBoxUserKey.Text);
				string fileName = textBox1.Text;
				if(string.IsNullOrEmpty(textBox2.Text)){
					textBox2.Text = "DEC_"+fileName;	
				}
			try {
				File.WriteAllBytes(textBox2.Text, tripleDES.Decrypt(File.ReadAllBytes(textBox1.Text)));
				MessageBox.Show("Файл расшифрован", "Сообщение");
			} catch (Exception) {
				MessageBox.Show("Не удалось расшифровать файл");
			}
		}
		}
	}
}
