using System;
using System.Drawing;
using System.IO;
using System.Text;
using System.Windows.Forms;
using SteganoWave;
using System.Text.RegularExpressions;
using System.Linq;
using System.Collections.Generic;

namespace Lab4 {
    public partial class Form1 : Form {

        private Bitmap scrImage;

        public Form1() {
            InitializeComponent();
        }

        private void loadImage_Click(object sender, EventArgs e) {
            openFileDialog1.Filter = "jpg (*.jpg)|*.jpg";
            if (openFileDialog1.ShowDialog() == DialogResult.OK) {
                try {
                    scrImage = new Bitmap(openFileDialog1.FileName);
                    pictureBox1.Image = scrImage;
                } catch (Exception ex) {
                    MessageBox.Show("Error: Could not read file from disk. Original error: " + ex.Message);
                }
            }
        }
        
        private void saveImage_Click(object sender, EventArgs e) {
            saveFileDialog1.Filter = "jpg (*.jpg)|*.jpg";
            if (saveFileDialog1.ShowDialog() == DialogResult.OK) {
                try {
                    scrImage.Save(saveFileDialog1.FileName);
                } catch (Exception ex) {
                    MessageBox.Show("Error: Could not read file from disk. Original error: " + ex.Message);
                }
            }
        }

        private void openWav_Click(object sender, EventArgs e) {
            openFileDialog1.Filter = "wav (*.wav)|*.wav";
            if (openFileDialog1.ShowDialog() == DialogResult.OK) {
                txtSrcFile.Text = openFileDialog1.FileName;
            }
        }

        private void saveWav_Click(object sender, EventArgs e) {
        	saveFileDialog1.Filter = "wav (*.wav)|*.wav";
            if (saveFileDialog1.ShowDialog() == DialogResult.OK) {
                txtDstFile.Text = saveFileDialog1.FileName;
            }
        }

        private void makeWatermark_Click(object sender, EventArgs e) {
            pictureBox1.Image = watermark(textBox4.Text);
            MessageBox.Show("Водяной знак поставлен!");
        }

        private Image watermark(string key) {
            var resImage = scrImage;
            var intKey = key.Sum(x => (int)x);
            Random rand = new Random(intKey);

            int R;
            int G;
            int B;
            byte n = 5;
            for (int i = 0; i < 10000; i++) {
                int xa = rand.Next(0, resImage.Width);
                int ya = rand.Next(0, resImage.Height);
                Color pixel = resImage.GetPixel(xa, ya);
                R = pixel.R + n;
                G = pixel.G + n;
                B = pixel.B + n;
                if (R > 255) {
                    R = 255;
                }
                if (G > 255) {
                    G = 255;
                }
                if (B > 255) {
                    B = 255;
                }
                resImage.SetPixel(xa, ya, Color.FromArgb(R, G, B));

                int xb = rand.Next(0, resImage.Width);
                int yb = rand.Next(0, resImage.Height);
                Color pixelb = resImage.GetPixel(xb, yb);
                R = pixelb.R - n;
                G = pixelb.G - n;
                B = pixelb.B - n;
                if (R < 0) {
                    R = 0;
                }
                if (G < 0) {
                    G = 0;
                }
                if (B < 0) {
                    B = 0;
                }
                resImage.SetPixel(xb, yb, Color.FromArgb(R, G, B));
            }
            return resImage;
        }

        private void validatePatchwork_Click(object sender, EventArgs e) {
        	var resImage = scrImage;
            var intKey =  textBox4.Text.Sum(x => (int)x);
            Random rand = new Random(intKey);

            int dR;
            int dG;
            int dB;
            long result = 0;

            for (int i = 0; i < 10000; i++) {
                int xa = rand.Next(0, resImage.Width);
                int ya = rand.Next(0, resImage.Height);
                Color pixel = resImage.GetPixel(xa, ya);
                int xb = rand.Next(0, resImage.Width);
                int yb = rand.Next(0, resImage.Height);
                Color pixelb = resImage.GetPixel(xb, yb);
                dR = pixel.R - pixelb.R;
                dG = pixel.G - pixelb.G;
                dB = pixel.B - pixelb.B;
                result+=(dR+dG+dB);
            }
            
			const double range = (2 * 5 * 10000) * 0.8;
            if (result > range) {
                MessageBox.Show("Водяной знак валиден!");
            } else {
                MessageBox.Show("Водяной знак не валиден!");
            }
        }

        private Stream GetStream(string text) {
            BinaryWriter messageWriter = new BinaryWriter(new MemoryStream());
            messageWriter.Write(text.Length);
            messageWriter.Write(Encoding.ASCII.GetBytes(text));
            messageWriter.Seek(0, SeekOrigin.Begin);
            return messageWriter.BaseStream;
        }

        private void hideInWav_Click(object sender, EventArgs e) {
            if (txtKey.Text.Length == 0 || txtMessage.Text.Length == 0 || txtSrcFile.Text.Length == 0 ||
                txtDstFile.Text.Length == 0) {
                MessageBox.Show("Error");
            } else {
                Stream sourceStream = null;
                FileStream destinationStream = null;
                WaveStream audioStream = null;
                
                var messageStream = GetStream(txtMessage.Text);
                var keyStream = GetStream(txtKey.Text);
                try {

                    long countSamplesRequired = WaveUtility.CheckKeyForMessage(keyStream, messageStream.Length);

                    if (countSamplesRequired > Int32.MaxValue) {
                        throw new Exception("Message too long, or bad key! This message/key combination requires " +
                                            countSamplesRequired + " samples, only " + Int32.MaxValue +
                                            " samples are allowed.");
                    }

                    sourceStream = new FileStream(txtSrcFile.Text, FileMode.Open);

                    this.Cursor = Cursors.WaitCursor;

                    destinationStream = new FileStream(txtDstFile.Text, FileMode.Create);

                    audioStream = new WaveStream(sourceStream, destinationStream);
                    if (audioStream.Length <= 0) {
                        throw new Exception("Invalid WAV file");
                    }

                    if (countSamplesRequired > audioStream.CountSamples) {
                        String errorReport = "The carrier file is too small for this message and key!\r\n"
                                             + "Samples available: " + audioStream.CountSamples + "\r\n"
                                             + "Samples needed: " + countSamplesRequired;
                        throw new Exception(errorReport);
                    }

                    WaveUtility utility = new WaveUtility(audioStream, destinationStream);
                    utility.Hide(messageStream, keyStream);
                } catch (Exception ex) {
                    MessageBox.Show(ex.Message);
                } finally {
                    if (keyStream != null) {
                        keyStream.Close();
                    }
                    if (messageStream != null) {
                        messageStream.Close();
                    }
                    if (audioStream != null) {
                        audioStream.Close();
                    }
                    if (sourceStream != null) {
                        sourceStream.Close();
                    }
                    if (destinationStream != null) {
                        destinationStream.Close();
                    }
                    this.Cursor = Cursors.Default;
                    MessageBox.Show("Done");
                }
            }
        }

        private void extractFromWav_Click(object sender, EventArgs e) {
            if (txtSrcFile.Text.Length == 0 || txtKey.Text.Length == 0) {
                MessageBox.Show("Error");
            } else {

                this.Cursor = Cursors.WaitCursor;

                FileStream sourceStream = null;
                WaveStream audioStream = null;
                MemoryStream messageStream = new MemoryStream();
                var keyStream = GetStream(txtKey.Text);

                try {
                    sourceStream = new FileStream(txtSrcFile.Text, FileMode.Open);
                    audioStream = new WaveStream(sourceStream);
                    WaveUtility utility = new WaveUtility(audioStream);

                    utility.Extract(messageStream, keyStream);

                    messageStream.Seek(0, SeekOrigin.Begin);
                    txtMessage.Text = new StreamReader(messageStream).ReadToEnd();
                } catch (Exception ex) {
                    MessageBox.Show(ex.Message);
                } finally {
                    if (keyStream != null) {
                        keyStream.Close();
                    }
                    if (messageStream != null) {
                        messageStream.Close();
                    }
                    if (audioStream != null) {
                        audioStream.Close();
                    }
                    if (sourceStream != null) {
                        sourceStream.Close();
                    }
                    this.Cursor = Cursors.Default;
                    MessageBox.Show("Done");
                }
            }
        }

        private void loadDictionary_Click(object sender, EventArgs e) {
            openFileDialog1.Filter = "Text (*.txt)|*.txt";
            if (openFileDialog1.ShowDialog() == DialogResult.OK) {
                textBox2.Text = openFileDialog1.FileName;
            }
        }

        private void generateText_Click(object sender, EventArgs e) {
            if (textBox2.Text.Length == 0 || textBox1.Text.Length == 00) {
                MessageBox.Show("Error");
            } else {
                var text = File.OpenText(textBox2.Text);
                var matches = Regex.Matches(text.ReadToEnd(), @"\b([а-яa-z])+\b",RegexOptions.IgnoreCase);
                var words = new List<string>(matches.Count);
                foreach(Match match in matches) {
                    words.Add(match.Value);
                }
                textBox3.Text = GenerateText(words, textBox1.Text.ToLower(), (int)numericUpDown1.Value);
            }
        }

        public string GenerateText(List<string> words, string message, int index) {
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < message.Length; i++) {
                result.Append(FindWord(words, message[i], index));
            }

            return result.ToString();
        }

        private Random random = new Random();

        public string FindWord(List<string> words, char symbol, int index) {
            string result = string.Empty;
            var tempWords = words.FindAll(i => i.Length > index && i[index-1] == symbol);
            if (tempWords.Count > 0) {
                result = tempWords[random.Next(tempWords.Count)] + " ";
            }else{
            	tempWords = words.FindAll(i => i.Length > index);
            	result = tempWords[random.Next(tempWords.Count)] + " ";
            	result.Remove(index-1, 1);
            	result = result.Insert(index-1, ""+symbol);
            }
            return result;
        }
    }
}
