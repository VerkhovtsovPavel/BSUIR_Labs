namespace Lab4
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
        	this.pictureBox1 = new System.Windows.Forms.PictureBox();
        	this.openFileDialog1 = new System.Windows.Forms.OpenFileDialog();
        	this.button4 = new System.Windows.Forms.Button();
        	this.groupBox2 = new System.Windows.Forms.GroupBox();
        	this.button5 = new System.Windows.Forms.Button();
        	this.label2 = new System.Windows.Forms.Label();
        	this.textBox4 = new System.Windows.Forms.TextBox();
        	this.panel1 = new System.Windows.Forms.Panel();
        	this.tabControl1 = new System.Windows.Forms.TabControl();
        	this.tabPage3 = new System.Windows.Forms.TabPage();
        	this.textBox3 = new System.Windows.Forms.TextBox();
        	this.groupBox3 = new System.Windows.Forms.GroupBox();
        	this.numericUpDown1 = new System.Windows.Forms.NumericUpDown();
        	this.textBox1 = new System.Windows.Forms.TextBox();
        	this.button9 = new System.Windows.Forms.Button();
        	this.button8 = new System.Windows.Forms.Button();
        	this.textBox2 = new System.Windows.Forms.TextBox();
        	this.tabPage2 = new System.Windows.Forms.TabPage();
        	this.txtDstFile = new System.Windows.Forms.TextBox();
        	this.button7 = new System.Windows.Forms.Button();
        	this.txtSrcFile = new System.Windows.Forms.TextBox();
        	this.button2 = new System.Windows.Forms.Button();
        	this.groupBox1 = new System.Windows.Forms.GroupBox();
        	this.button3 = new System.Windows.Forms.Button();
        	this.label1 = new System.Windows.Forms.Label();
        	this.txtMessage = new System.Windows.Forms.TextBox();
        	this.txtKey = new System.Windows.Forms.TextBox();
        	this.button6 = new System.Windows.Forms.Button();
        	this.tabPage1 = new System.Windows.Forms.TabPage();
        	this.button1 = new System.Windows.Forms.Button();
        	this.saveFileDialog1 = new System.Windows.Forms.SaveFileDialog();
        	this.button10 = new System.Windows.Forms.Button();
        	((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
        	this.groupBox2.SuspendLayout();
        	this.panel1.SuspendLayout();
        	this.tabControl1.SuspendLayout();
        	this.tabPage3.SuspendLayout();
        	this.groupBox3.SuspendLayout();
        	((System.ComponentModel.ISupportInitialize)(this.numericUpDown1)).BeginInit();
        	this.tabPage2.SuspendLayout();
        	this.groupBox1.SuspendLayout();
        	this.tabPage1.SuspendLayout();
        	this.SuspendLayout();
        	// 
        	// pictureBox1
        	// 
        	this.pictureBox1.Dock = System.Windows.Forms.DockStyle.Fill;
        	this.pictureBox1.Location = new System.Drawing.Point(0, 0);
        	this.pictureBox1.Name = "pictureBox1";
        	this.pictureBox1.Size = new System.Drawing.Size(266, 237);
        	this.pictureBox1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
        	this.pictureBox1.TabIndex = 1;
        	this.pictureBox1.TabStop = false;
        	// 
        	// button4
        	// 
        	this.button4.Location = new System.Drawing.Point(6, 59);
        	this.button4.Name = "button4";
        	this.button4.Size = new System.Drawing.Size(107, 35);
        	this.button4.TabIndex = 6;
        	this.button4.Text = "Поставить водяной знак";
        	this.button4.UseVisualStyleBackColor = true;
        	this.button4.Click += new System.EventHandler(this.makeWatermark_Click);
        	// 
        	// groupBox2
        	// 
        	this.groupBox2.Controls.Add(this.button5);
        	this.groupBox2.Controls.Add(this.label2);
        	this.groupBox2.Controls.Add(this.textBox4);
        	this.groupBox2.Controls.Add(this.button4);
        	this.groupBox2.Location = new System.Drawing.Point(6, 32);
        	this.groupBox2.Name = "groupBox2";
        	this.groupBox2.Size = new System.Drawing.Size(268, 100);
        	this.groupBox2.TabIndex = 0;
        	this.groupBox2.TabStop = false;
        	this.groupBox2.Text = "Patchwork";
        	// 
        	// button5
        	// 
        	this.button5.Location = new System.Drawing.Point(147, 59);
        	this.button5.Name = "button5";
        	this.button5.Size = new System.Drawing.Size(107, 35);
        	this.button5.TabIndex = 12;
        	this.button5.Text = "Проверить водяной знак";
        	this.button5.UseVisualStyleBackColor = true;
        	this.button5.Click += new System.EventHandler(this.validatePatchwork_Click);
        	// 
        	// label2
        	// 
        	this.label2.AutoSize = true;
        	this.label2.Location = new System.Drawing.Point(6, 18);
        	this.label2.Name = "label2";
        	this.label2.Size = new System.Drawing.Size(33, 13);
        	this.label2.TabIndex = 9;
        	this.label2.Text = "Ключ";
        	// 
        	// textBox4
        	// 
        	this.textBox4.Location = new System.Drawing.Point(6, 35);
        	this.textBox4.Name = "textBox4";
        	this.textBox4.Size = new System.Drawing.Size(248, 20);
        	this.textBox4.TabIndex = 0;
        	// 
        	// panel1
        	// 
        	this.panel1.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
        	this.panel1.Controls.Add(this.pictureBox1);
        	this.panel1.Location = new System.Drawing.Point(6, 138);
        	this.panel1.Name = "panel1";
        	this.panel1.Size = new System.Drawing.Size(268, 239);
        	this.panel1.TabIndex = 10;
        	// 
        	// tabControl1
        	// 
        	this.tabControl1.Controls.Add(this.tabPage3);
        	this.tabControl1.Controls.Add(this.tabPage2);
        	this.tabControl1.Controls.Add(this.tabPage1);
        	this.tabControl1.Location = new System.Drawing.Point(12, 12);
        	this.tabControl1.Name = "tabControl1";
        	this.tabControl1.SelectedIndex = 0;
        	this.tabControl1.Size = new System.Drawing.Size(288, 406);
        	this.tabControl1.TabIndex = 11;
        	// 
        	// tabPage3
        	// 
        	this.tabPage3.Controls.Add(this.textBox3);
        	this.tabPage3.Controls.Add(this.groupBox3);
        	this.tabPage3.Location = new System.Drawing.Point(4, 22);
        	this.tabPage3.Name = "tabPage3";
        	this.tabPage3.Size = new System.Drawing.Size(280, 380);
        	this.tabPage3.TabIndex = 2;
        	this.tabPage3.Text = "Text";
        	this.tabPage3.UseVisualStyleBackColor = true;
        	// 
        	// textBox3
        	// 
        	this.textBox3.Location = new System.Drawing.Point(3, 113);
        	this.textBox3.Multiline = true;
        	this.textBox3.Name = "textBox3";
        	this.textBox3.ScrollBars = System.Windows.Forms.ScrollBars.Both;
        	this.textBox3.Size = new System.Drawing.Size(274, 264);
        	this.textBox3.TabIndex = 1;
        	// 
        	// groupBox3
        	// 
        	this.groupBox3.Controls.Add(this.numericUpDown1);
        	this.groupBox3.Controls.Add(this.textBox1);
        	this.groupBox3.Controls.Add(this.button9);
        	this.groupBox3.Controls.Add(this.button8);
        	this.groupBox3.Controls.Add(this.textBox2);
        	this.groupBox3.Location = new System.Drawing.Point(3, 3);
        	this.groupBox3.Name = "groupBox3";
        	this.groupBox3.Size = new System.Drawing.Size(274, 104);
        	this.groupBox3.TabIndex = 0;
        	this.groupBox3.TabStop = false;
        	this.groupBox3.Text = "Словарь";
        	// 
        	// numericUpDown1
        	// 
        	this.numericUpDown1.Location = new System.Drawing.Point(6, 74);
        	this.numericUpDown1.Name = "numericUpDown1";
        	this.numericUpDown1.Size = new System.Drawing.Size(75, 20);
        	this.numericUpDown1.TabIndex = 2;
        	// 
        	// textBox1
        	// 
        	this.textBox1.Location = new System.Drawing.Point(6, 46);
        	this.textBox1.Name = "textBox1";
        	this.textBox1.Size = new System.Drawing.Size(262, 20);
        	this.textBox1.TabIndex = 0;
        	// 
        	// button9
        	// 
        	this.button9.Location = new System.Drawing.Point(6, 17);
        	this.button9.Name = "button9";
        	this.button9.Size = new System.Drawing.Size(75, 23);
        	this.button9.TabIndex = 1;
        	this.button9.Text = "Открыть";
        	this.button9.UseVisualStyleBackColor = true;
        	this.button9.Click += new System.EventHandler(this.loadDictionary_Click);
        	// 
        	// button8
        	// 
        	this.button8.Location = new System.Drawing.Point(177, 71);
        	this.button8.Name = "button8";
        	this.button8.Size = new System.Drawing.Size(91, 23);
        	this.button8.TabIndex = 1;
        	this.button8.Text = "Генерировать";
        	this.button8.UseVisualStyleBackColor = true;
        	this.button8.Click += new System.EventHandler(this.generateText_Click);
        	// 
        	// textBox2
        	// 
        	this.textBox2.Location = new System.Drawing.Point(87, 19);
        	this.textBox2.Name = "textBox2";
        	this.textBox2.ReadOnly = true;
        	this.textBox2.Size = new System.Drawing.Size(181, 20);
        	this.textBox2.TabIndex = 0;
        	// 
        	// tabPage2
        	// 
        	this.tabPage2.Controls.Add(this.txtDstFile);
        	this.tabPage2.Controls.Add(this.button7);
        	this.tabPage2.Controls.Add(this.txtSrcFile);
        	this.tabPage2.Controls.Add(this.button2);
        	this.tabPage2.Controls.Add(this.groupBox1);
        	this.tabPage2.Location = new System.Drawing.Point(4, 22);
        	this.tabPage2.Name = "tabPage2";
        	this.tabPage2.Padding = new System.Windows.Forms.Padding(3);
        	this.tabPage2.Size = new System.Drawing.Size(280, 380);
        	this.tabPage2.TabIndex = 1;
        	this.tabPage2.Text = "LSB";
        	this.tabPage2.UseVisualStyleBackColor = true;
        	// 
        	// txtDstFile
        	// 
        	this.txtDstFile.Location = new System.Drawing.Point(87, 35);
        	this.txtDstFile.Name = "txtDstFile";
        	this.txtDstFile.ReadOnly = true;
        	this.txtDstFile.Size = new System.Drawing.Size(187, 20);
        	this.txtDstFile.TabIndex = 14;
        	// 
        	// button7
        	// 
        	this.button7.Location = new System.Drawing.Point(6, 32);
        	this.button7.Name = "button7";
        	this.button7.Size = new System.Drawing.Size(75, 23);
        	this.button7.TabIndex = 13;
        	this.button7.Text = "Сохранить";
        	this.button7.UseVisualStyleBackColor = true;
        	this.button7.Click += new System.EventHandler(this.saveWav_Click);
        	// 
        	// txtSrcFile
        	// 
        	this.txtSrcFile.Location = new System.Drawing.Point(87, 9);
        	this.txtSrcFile.Name = "txtSrcFile";
        	this.txtSrcFile.ReadOnly = true;
        	this.txtSrcFile.Size = new System.Drawing.Size(187, 20);
        	this.txtSrcFile.TabIndex = 14;
        	// 
        	// button2
        	// 
        	this.button2.Location = new System.Drawing.Point(6, 6);
        	this.button2.Name = "button2";
        	this.button2.Size = new System.Drawing.Size(75, 23);
        	this.button2.TabIndex = 13;
        	this.button2.Text = "Открыть";
        	this.button2.UseVisualStyleBackColor = true;
        	this.button2.Click += new System.EventHandler(this.openWav_Click);
        	// 
        	// groupBox1
        	// 
        	this.groupBox1.Controls.Add(this.button3);
        	this.groupBox1.Controls.Add(this.label1);
        	this.groupBox1.Controls.Add(this.txtMessage);
        	this.groupBox1.Controls.Add(this.txtKey);
        	this.groupBox1.Controls.Add(this.button6);
        	this.groupBox1.Location = new System.Drawing.Point(6, 61);
        	this.groupBox1.Name = "groupBox1";
        	this.groupBox1.Size = new System.Drawing.Size(268, 316);
        	this.groupBox1.TabIndex = 12;
        	this.groupBox1.TabStop = false;
        	this.groupBox1.Text = "Stenography";
        	// 
        	// button3
        	// 
        	this.button3.Location = new System.Drawing.Point(147, 59);
        	this.button3.Name = "button3";
        	this.button3.Size = new System.Drawing.Size(107, 35);
        	this.button3.TabIndex = 12;
        	this.button3.Text = "Извлеч текст";
        	this.button3.UseVisualStyleBackColor = true;
        	this.button3.Click += new System.EventHandler(this.extractFromWav_Click);
        	// 
        	// label1
        	// 
        	this.label1.AutoSize = true;
        	this.label1.Location = new System.Drawing.Point(6, 18);
        	this.label1.Name = "label1";
        	this.label1.Size = new System.Drawing.Size(33, 13);
        	this.label1.TabIndex = 9;
        	this.label1.Text = "Ключ";
        	// 
        	// txtMessage
        	// 
        	this.txtMessage.Location = new System.Drawing.Point(6, 100);
        	this.txtMessage.Multiline = true;
        	this.txtMessage.Name = "txtMessage";
        	this.txtMessage.ScrollBars = System.Windows.Forms.ScrollBars.Both;
        	this.txtMessage.Size = new System.Drawing.Size(248, 210);
        	this.txtMessage.TabIndex = 0;
        	// 
        	// txtKey
        	// 
        	this.txtKey.Location = new System.Drawing.Point(6, 35);
        	this.txtKey.Name = "txtKey";
        	this.txtKey.Size = new System.Drawing.Size(248, 20);
        	this.txtKey.TabIndex = 0;
        	// 
        	// button6
        	// 
        	this.button6.Location = new System.Drawing.Point(6, 59);
        	this.button6.Name = "button6";
        	this.button6.Size = new System.Drawing.Size(107, 35);
        	this.button6.TabIndex = 6;
        	this.button6.Text = "Спрятать текст";
        	this.button6.UseVisualStyleBackColor = true;
        	this.button6.Click += new System.EventHandler(this.hideInWav_Click);
        	// 
        	// tabPage1
        	// 
        	this.tabPage1.Controls.Add(this.button10);
        	this.tabPage1.Controls.Add(this.button1);
        	this.tabPage1.Controls.Add(this.panel1);
        	this.tabPage1.Controls.Add(this.groupBox2);
        	this.tabPage1.Location = new System.Drawing.Point(4, 22);
        	this.tabPage1.Name = "tabPage1";
        	this.tabPage1.Padding = new System.Windows.Forms.Padding(3);
        	this.tabPage1.Size = new System.Drawing.Size(280, 380);
        	this.tabPage1.TabIndex = 0;
        	this.tabPage1.Text = "Watermark";
        	this.tabPage1.UseVisualStyleBackColor = true;
        	// 
        	// button1
        	// 
        	this.button1.Location = new System.Drawing.Point(6, 3);
        	this.button1.Name = "button1";
        	this.button1.Size = new System.Drawing.Size(75, 23);
        	this.button1.TabIndex = 11;
        	this.button1.Text = "Открыть";
        	this.button1.UseVisualStyleBackColor = true;
        	this.button1.Click += new System.EventHandler(this.loadImage_Click);
        	// 
        	// saveFileDialog1
        	// 
        	this.saveFileDialog1.Filter = "wav (*.wav)|*.wav";
        	// 
        	// button10
        	// 
        	this.button10.Location = new System.Drawing.Point(87, 3);
        	this.button10.Name = "button10";
        	this.button10.Size = new System.Drawing.Size(86, 23);
        	this.button10.TabIndex = 12;
        	this.button10.Text = "Сохранить";
        	this.button10.UseVisualStyleBackColor = true;
        	this.button10.Click += new System.EventHandler(this.saveImage_Click);
        	// 
        	// Form1
        	// 
        	this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
        	this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
        	this.ClientSize = new System.Drawing.Size(307, 432);
        	this.Controls.Add(this.tabControl1);
        	this.Name = "Form1";
        	this.Text = "ZIRKSiS Lab4";
        	((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
        	this.groupBox2.ResumeLayout(false);
        	this.groupBox2.PerformLayout();
        	this.panel1.ResumeLayout(false);
        	this.tabControl1.ResumeLayout(false);
        	this.tabPage3.ResumeLayout(false);
        	this.tabPage3.PerformLayout();
        	this.groupBox3.ResumeLayout(false);
        	this.groupBox3.PerformLayout();
        	((System.ComponentModel.ISupportInitialize)(this.numericUpDown1)).EndInit();
        	this.tabPage2.ResumeLayout(false);
        	this.tabPage2.PerformLayout();
        	this.groupBox1.ResumeLayout(false);
        	this.groupBox1.PerformLayout();
        	this.tabPage1.ResumeLayout(false);
        	this.ResumeLayout(false);

        }
     

        #endregion
        private System.Windows.Forms.PictureBox pictureBox1;
        private System.Windows.Forms.OpenFileDialog openFileDialog1;
        private System.Windows.Forms.Button button4;
        private System.Windows.Forms.GroupBox groupBox2;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox textBox4;
        private System.Windows.Forms.Button button5;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.TabControl tabControl1;
        private System.Windows.Forms.TabPage tabPage1;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.TabPage tabPage2;
        private System.Windows.Forms.Button button2;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.Button button3;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox txtMessage;
        private System.Windows.Forms.TextBox txtKey;
        private System.Windows.Forms.Button button6;
        private System.Windows.Forms.TextBox txtDstFile;
        private System.Windows.Forms.Button button7;
        private System.Windows.Forms.TextBox txtSrcFile;
        private System.Windows.Forms.SaveFileDialog saveFileDialog1;
        private System.Windows.Forms.TabPage tabPage3;
        private System.Windows.Forms.GroupBox groupBox3;
        private System.Windows.Forms.NumericUpDown numericUpDown1;
        private System.Windows.Forms.TextBox textBox1;
        private System.Windows.Forms.Button button9;
        private System.Windows.Forms.Button button8;
        private System.Windows.Forms.TextBox textBox2;
        private System.Windows.Forms.Button button10;
        private System.Windows.Forms.TextBox textBox3;
    }
}

