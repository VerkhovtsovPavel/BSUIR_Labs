namespace Lab2 {
    partial class Form1 {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing) {
            if (disposing && (components != null)) {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent() {
        	this.openFileDialog1 = new System.Windows.Forms.OpenFileDialog();
        	this.saveFileDialog1 = new System.Windows.Forms.SaveFileDialog();
        	this.tabPage2 = new System.Windows.Forms.TabPage();
        	this.panel5 = new System.Windows.Forms.Panel();
        	this.label8 = new System.Windows.Forms.Label();
        	this.textBoxCheckUserName = new System.Windows.Forms.TextBox();
        	this.buttonCheckSign = new System.Windows.Forms.Button();
        	this.label4 = new System.Windows.Forms.Label();
        	this.buttonOpenCheckSign = new System.Windows.Forms.Button();
        	this.label6 = new System.Windows.Forms.Label();
        	this.buttonOpenCheckFile = new System.Windows.Forms.Button();
        	this.textBoxOpenCheckFile = new System.Windows.Forms.TextBox();
        	this.textBoxOpenCheckSign = new System.Windows.Forms.TextBox();
        	this.panel4 = new System.Windows.Forms.Panel();
        	this.buttonSignFile = new System.Windows.Forms.Button();
        	this.buttonOpenDigSign = new System.Windows.Forms.Button();
        	this.buttonOpenFile = new System.Windows.Forms.Button();
        	this.textBoxOpenDigSign = new System.Windows.Forms.TextBox();
        	this.textBoxOpenFile = new System.Windows.Forms.TextBox();
        	this.label7 = new System.Windows.Forms.Label();
        	this.label9 = new System.Windows.Forms.Label();
        	this.panel3 = new System.Windows.Forms.Panel();
        	this.buttonCreateSign = new System.Windows.Forms.Button();
        	this.buttonSaveDigSign = new System.Windows.Forms.Button();
        	this.textBoxSaveDigSign = new System.Windows.Forms.TextBox();
        	this.label5 = new System.Windows.Forms.Label();
        	this.radioButton2 = new System.Windows.Forms.RadioButton();
        	this.radioButton1 = new System.Windows.Forms.RadioButton();
        	this.tabPage1 = new System.Windows.Forms.TabPage();
        	this.panel1 = new System.Windows.Forms.Panel();
        	this.label2 = new System.Windows.Forms.Label();
        	this.label1 = new System.Windows.Forms.Label();
        	this.textBoxInMessage = new System.Windows.Forms.TextBox();
        	this.textBoxOutMessage = new System.Windows.Forms.TextBox();
        	this.panel2 = new System.Windows.Forms.Panel();
        	this.button1 = new System.Windows.Forms.Button();
        	this.buttonValidate = new System.Windows.Forms.Button();
        	this.label3 = new System.Windows.Forms.Label();
        	this.textBoxKey = new System.Windows.Forms.TextBox();
        	this.tab = new System.Windows.Forms.TabControl();
        	this.tabPage2.SuspendLayout();
        	this.panel5.SuspendLayout();
        	this.panel4.SuspendLayout();
        	this.panel3.SuspendLayout();
        	this.tabPage1.SuspendLayout();
        	this.panel1.SuspendLayout();
        	this.panel2.SuspendLayout();
        	this.tab.SuspendLayout();
        	this.SuspendLayout();
        	// 
        	// openFileDialog1
        	// 
        	this.openFileDialog1.Filter = "text file (*.txt)|*.txt|signature (*.sig)|*sig";
        	// 
        	// saveFileDialog1
        	// 
        	this.saveFileDialog1.FileName = "signature.sig";
        	this.saveFileDialog1.Filter = "signature (.sig)|*.sig";
        	// 
        	// tabPage2
        	// 
        	this.tabPage2.Controls.Add(this.panel5);
        	this.tabPage2.Controls.Add(this.panel4);
        	this.tabPage2.Controls.Add(this.panel3);
        	this.tabPage2.Location = new System.Drawing.Point(4, 22);
        	this.tabPage2.Name = "tabPage2";
        	this.tabPage2.Padding = new System.Windows.Forms.Padding(3);
        	this.tabPage2.Size = new System.Drawing.Size(550, 305);
        	this.tabPage2.TabIndex = 1;
        	this.tabPage2.Text = "Signature";
        	this.tabPage2.UseVisualStyleBackColor = true;
        	// 
        	// panel5
        	// 
        	this.panel5.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
        	this.panel5.Controls.Add(this.label8);
        	this.panel5.Controls.Add(this.textBoxCheckUserName);
        	this.panel5.Controls.Add(this.buttonCheckSign);
        	this.panel5.Controls.Add(this.label4);
        	this.panel5.Controls.Add(this.buttonOpenCheckSign);
        	this.panel5.Controls.Add(this.label6);
        	this.panel5.Controls.Add(this.buttonOpenCheckFile);
        	this.panel5.Controls.Add(this.textBoxOpenCheckFile);
        	this.panel5.Controls.Add(this.textBoxOpenCheckSign);
        	this.panel5.Location = new System.Drawing.Point(6, 104);
        	this.panel5.Name = "panel5";
        	this.panel5.Size = new System.Drawing.Size(538, 136);
        	this.panel5.TabIndex = 2;
        	// 
        	// label8
        	// 
        	this.label8.AutoSize = true;
        	this.label8.Location = new System.Drawing.Point(3, 83);
        	this.label8.Name = "label8";
        	this.label8.Size = new System.Drawing.Size(103, 13);
        	this.label8.TabIndex = 5;
        	this.label8.Text = "Имя пользователя";
        	// 
        	// textBoxCheckUserName
        	// 
        	this.textBoxCheckUserName.Location = new System.Drawing.Point(6, 99);
        	this.textBoxCheckUserName.Name = "textBoxCheckUserName";
        	this.textBoxCheckUserName.Size = new System.Drawing.Size(403, 20);
        	this.textBoxCheckUserName.TabIndex = 4;
        	// 
        	// buttonCheckSign
        	// 
        	this.buttonCheckSign.Location = new System.Drawing.Point(415, 97);
        	this.buttonCheckSign.Name = "buttonCheckSign";
        	this.buttonCheckSign.Size = new System.Drawing.Size(118, 23);
        	this.buttonCheckSign.TabIndex = 5;
        	this.buttonCheckSign.Text = "Проверить подпись";
        	this.buttonCheckSign.UseVisualStyleBackColor = true;
        	this.buttonCheckSign.Click += new System.EventHandler(this.buttonCheckSign_Click);
        	// 
        	// label4
        	// 
        	this.label4.AutoSize = true;
        	this.label4.Location = new System.Drawing.Point(3, 4);
        	this.label4.Name = "label4";
        	this.label4.Size = new System.Drawing.Size(106, 13);
        	this.label4.TabIndex = 0;
        	this.label4.Text = "Подписанный файл";
        	// 
        	// buttonOpenCheckSign
        	// 
        	this.buttonOpenCheckSign.Location = new System.Drawing.Point(381, 56);
        	this.buttonOpenCheckSign.Name = "buttonOpenCheckSign";
        	this.buttonOpenCheckSign.Size = new System.Drawing.Size(28, 23);
        	this.buttonOpenCheckSign.TabIndex = 2;
        	this.buttonOpenCheckSign.Text = "...";
        	this.buttonOpenCheckSign.UseVisualStyleBackColor = true;
        	this.buttonOpenCheckSign.Click += new System.EventHandler(this.buttonOpenCheckSign_Click);
        	// 
        	// label6
        	// 
        	this.label6.AutoSize = true;
        	this.label6.Location = new System.Drawing.Point(3, 43);
        	this.label6.Name = "label6";
        	this.label6.Size = new System.Drawing.Size(104, 13);
        	this.label6.TabIndex = 0;
        	this.label6.Text = "Цифровая подпись";
        	// 
        	// buttonOpenCheckFile
        	// 
        	this.buttonOpenCheckFile.Location = new System.Drawing.Point(381, 18);
        	this.buttonOpenCheckFile.Name = "buttonOpenCheckFile";
        	this.buttonOpenCheckFile.Size = new System.Drawing.Size(28, 23);
        	this.buttonOpenCheckFile.TabIndex = 0;
        	this.buttonOpenCheckFile.Text = "...";
        	this.buttonOpenCheckFile.UseVisualStyleBackColor = true;
        	this.buttonOpenCheckFile.Click += new System.EventHandler(this.buttonOpenCheckFile_Click);
        	// 
        	// textBoxOpenCheckFile
        	// 
        	this.textBoxOpenCheckFile.Location = new System.Drawing.Point(3, 20);
        	this.textBoxOpenCheckFile.Name = "textBoxOpenCheckFile";
        	this.textBoxOpenCheckFile.ReadOnly = true;
        	this.textBoxOpenCheckFile.Size = new System.Drawing.Size(372, 20);
        	this.textBoxOpenCheckFile.TabIndex = 1;
        	this.textBoxOpenCheckFile.TabStop = false;
        	// 
        	// textBoxOpenCheckSign
        	// 
        	this.textBoxOpenCheckSign.Location = new System.Drawing.Point(3, 59);
        	this.textBoxOpenCheckSign.Name = "textBoxOpenCheckSign";
        	this.textBoxOpenCheckSign.ReadOnly = true;
        	this.textBoxOpenCheckSign.Size = new System.Drawing.Size(372, 20);
        	this.textBoxOpenCheckSign.TabIndex = 3;
        	this.textBoxOpenCheckSign.TabStop = false;
        	// 
        	// panel4
        	// 
        	this.panel4.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
        	this.panel4.Controls.Add(this.buttonSignFile);
        	this.panel4.Controls.Add(this.buttonOpenDigSign);
        	this.panel4.Controls.Add(this.buttonOpenFile);
        	this.panel4.Controls.Add(this.textBoxOpenDigSign);
        	this.panel4.Controls.Add(this.textBoxOpenFile);
        	this.panel4.Controls.Add(this.label7);
        	this.panel4.Controls.Add(this.label9);
        	this.panel4.Location = new System.Drawing.Point(6, 6);
        	this.panel4.Name = "panel4";
        	this.panel4.Size = new System.Drawing.Size(538, 92);
        	this.panel4.TabIndex = 1;
        	// 
        	// buttonSignFile
        	// 
        	this.buttonSignFile.Location = new System.Drawing.Point(415, 56);
        	this.buttonSignFile.Name = "buttonSignFile";
        	this.buttonSignFile.Size = new System.Drawing.Size(118, 23);
        	this.buttonSignFile.TabIndex = 4;
        	this.buttonSignFile.Text = "Подписать файл";
        	this.buttonSignFile.UseVisualStyleBackColor = true;
        	this.buttonSignFile.Click += new System.EventHandler(this.buttonSignFile_Click);
        	// 
        	// buttonOpenDigSign
        	// 
        	this.buttonOpenDigSign.Location = new System.Drawing.Point(381, 55);
        	this.buttonOpenDigSign.Name = "buttonOpenDigSign";
        	this.buttonOpenDigSign.Size = new System.Drawing.Size(28, 23);
        	this.buttonOpenDigSign.TabIndex = 2;
        	this.buttonOpenDigSign.Text = "...";
        	this.buttonOpenDigSign.UseVisualStyleBackColor = true;
        	this.buttonOpenDigSign.Click += new System.EventHandler(this.buttonOpenDigSign_Click);
        	// 
        	// buttonOpenFile
        	// 
        	this.buttonOpenFile.Location = new System.Drawing.Point(381, 16);
        	this.buttonOpenFile.Name = "buttonOpenFile";
        	this.buttonOpenFile.Size = new System.Drawing.Size(28, 23);
        	this.buttonOpenFile.TabIndex = 0;
        	this.buttonOpenFile.Text = "...";
        	this.buttonOpenFile.UseVisualStyleBackColor = true;
        	this.buttonOpenFile.Click += new System.EventHandler(this.buttonOpenFile_Click);
        	// 
        	// textBoxOpenDigSign
        	// 
        	this.textBoxOpenDigSign.Location = new System.Drawing.Point(3, 58);
        	this.textBoxOpenDigSign.Name = "textBoxOpenDigSign";
        	this.textBoxOpenDigSign.ReadOnly = true;
        	this.textBoxOpenDigSign.Size = new System.Drawing.Size(372, 20);
        	this.textBoxOpenDigSign.TabIndex = 3;
        	this.textBoxOpenDigSign.TabStop = false;
        	// 
        	// textBoxOpenFile
        	// 
        	this.textBoxOpenFile.Location = new System.Drawing.Point(3, 19);
        	this.textBoxOpenFile.Name = "textBoxOpenFile";
        	this.textBoxOpenFile.ReadOnly = true;
        	this.textBoxOpenFile.Size = new System.Drawing.Size(372, 20);
        	this.textBoxOpenFile.TabIndex = 1;
        	this.textBoxOpenFile.TabStop = false;
        	// 
        	// label7
        	// 
        	this.label7.AutoSize = true;
        	this.label7.Location = new System.Drawing.Point(3, 42);
        	this.label7.Name = "label7";
        	this.label7.Size = new System.Drawing.Size(104, 13);
        	this.label7.TabIndex = 0;
        	this.label7.Text = "Цифровая подпись";
        	// 
        	// label9
        	// 
        	this.label9.AutoSize = true;
        	this.label9.Location = new System.Drawing.Point(3, 3);
        	this.label9.Name = "label9";
        	this.label9.Size = new System.Drawing.Size(78, 13);
        	this.label9.TabIndex = 0;
        	this.label9.Text = "Входной файл";
        	// 
        	// panel3
        	// 
        	this.panel3.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
        	this.panel3.Controls.Add(this.buttonCreateSign);
        	this.panel3.Controls.Add(this.buttonSaveDigSign);
        	this.panel3.Controls.Add(this.textBoxSaveDigSign);
        	this.panel3.Controls.Add(this.label5);
        	this.panel3.Location = new System.Drawing.Point(6, 246);
        	this.panel3.Name = "panel3";
        	this.panel3.Size = new System.Drawing.Size(538, 53);
        	this.panel3.TabIndex = 0;
        	// 
        	// buttonCreateSign
        	// 
        	this.buttonCreateSign.Location = new System.Drawing.Point(415, 17);
        	this.buttonCreateSign.Name = "buttonCreateSign";
        	this.buttonCreateSign.Size = new System.Drawing.Size(118, 23);
        	this.buttonCreateSign.TabIndex = 2;
        	this.buttonCreateSign.Text = "Создать подпись";
        	this.buttonCreateSign.UseVisualStyleBackColor = true;
        	this.buttonCreateSign.Click += new System.EventHandler(this.buttonCreateSign_Click);
        	// 
        	// buttonSaveDigSign
        	// 
        	this.buttonSaveDigSign.Location = new System.Drawing.Point(381, 17);
        	this.buttonSaveDigSign.Name = "buttonSaveDigSign";
        	this.buttonSaveDigSign.Size = new System.Drawing.Size(28, 23);
        	this.buttonSaveDigSign.TabIndex = 0;
        	this.buttonSaveDigSign.Text = "...";
        	this.buttonSaveDigSign.UseVisualStyleBackColor = true;
        	this.buttonSaveDigSign.Click += new System.EventHandler(this.buttonSaveDigSign_Click);
        	// 
        	// textBoxSaveDigSign
        	// 
        	this.textBoxSaveDigSign.Location = new System.Drawing.Point(3, 19);
        	this.textBoxSaveDigSign.Name = "textBoxSaveDigSign";
        	this.textBoxSaveDigSign.ReadOnly = true;
        	this.textBoxSaveDigSign.Size = new System.Drawing.Size(372, 20);
        	this.textBoxSaveDigSign.TabIndex = 1;
        	this.textBoxSaveDigSign.TabStop = false;
        	// 
        	// label5
        	// 
        	this.label5.AutoSize = true;
        	this.label5.Location = new System.Drawing.Point(3, 3);
        	this.label5.Name = "label5";
        	this.label5.Size = new System.Drawing.Size(104, 13);
        	this.label5.TabIndex = 0;
        	this.label5.Text = "Цифровая подпись";
        	// 
        	// radioButton2
        	// 
        	this.radioButton2.AutoSize = true;
        	this.radioButton2.Location = new System.Drawing.Point(72, 8);
        	this.radioButton2.Name = "radioButton2";
        	this.radioButton2.Size = new System.Drawing.Size(56, 17);
        	this.radioButton2.TabIndex = 1;
        	this.radioButton2.TabStop = true;
        	this.radioButton2.Text = "HMAC";
        	this.radioButton2.UseVisualStyleBackColor = true;
        	// 
        	// radioButton1
        	// 
        	this.radioButton1.AutoSize = true;
        	this.radioButton1.Checked = true;
        	this.radioButton1.Location = new System.Drawing.Point(13, 8);
        	this.radioButton1.Name = "radioButton1";
        	this.radioButton1.Size = new System.Drawing.Size(53, 17);
        	this.radioButton1.TabIndex = 0;
        	this.radioButton1.TabStop = true;
        	this.radioButton1.Text = "SHA1";
        	this.radioButton1.UseVisualStyleBackColor = true;
        	// 
        	// tabPage1
        	// 
        	this.tabPage1.Controls.Add(this.panel1);
        	this.tabPage1.Controls.Add(this.panel2);
        	this.tabPage1.Location = new System.Drawing.Point(4, 22);
        	this.tabPage1.Name = "tabPage1";
        	this.tabPage1.Padding = new System.Windows.Forms.Padding(3);
        	this.tabPage1.Size = new System.Drawing.Size(550, 305);
        	this.tabPage1.TabIndex = 0;
        	this.tabPage1.Text = "HMAC";
        	this.tabPage1.UseVisualStyleBackColor = true;
        	// 
        	// panel1
        	// 
        	this.panel1.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
        	this.panel1.Controls.Add(this.label2);
        	this.panel1.Controls.Add(this.label1);
        	this.panel1.Controls.Add(this.textBoxInMessage);
        	this.panel1.Controls.Add(this.textBoxOutMessage);
        	this.panel1.Location = new System.Drawing.Point(5, 47);
        	this.panel1.Name = "panel1";
        	this.panel1.Size = new System.Drawing.Size(539, 252);
        	this.panel1.TabIndex = 1;
        	// 
        	// label2
        	// 
        	this.label2.AutoSize = true;
        	this.label2.Location = new System.Drawing.Point(3, 131);
        	this.label2.Name = "label2";
        	this.label2.Size = new System.Drawing.Size(118, 13);
        	this.label2.TabIndex = 3;
        	this.label2.Text = "Входящее сообщение";
        	// 
        	// label1
        	// 
        	this.label1.AutoSize = true;
        	this.label1.Location = new System.Drawing.Point(3, 5);
        	this.label1.Name = "label1";
        	this.label1.Size = new System.Drawing.Size(116, 13);
        	this.label1.TabIndex = 2;
        	this.label1.Text = "Исходное сообщение";
        	// 
        	// textBoxInMessage
        	// 
        	this.textBoxInMessage.Location = new System.Drawing.Point(3, 21);
        	this.textBoxInMessage.Multiline = true;
        	this.textBoxInMessage.Name = "textBoxInMessage";
        	this.textBoxInMessage.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
        	this.textBoxInMessage.Size = new System.Drawing.Size(530, 94);
        	this.textBoxInMessage.TabIndex = 0;
        	// 
        	// textBoxOutMessage
        	// 
        	this.textBoxOutMessage.Location = new System.Drawing.Point(3, 147);
        	this.textBoxOutMessage.Multiline = true;
        	this.textBoxOutMessage.Name = "textBoxOutMessage";
        	this.textBoxOutMessage.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
        	this.textBoxOutMessage.Size = new System.Drawing.Size(530, 100);
        	this.textBoxOutMessage.TabIndex = 1;
        	// 
        	// panel2
        	// 
        	this.panel2.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
        	this.panel2.Controls.Add(this.button1);
        	this.panel2.Controls.Add(this.buttonValidate);
        	this.panel2.Controls.Add(this.label3);
        	this.panel2.Controls.Add(this.radioButton2);
        	this.panel2.Controls.Add(this.radioButton1);
        	this.panel2.Controls.Add(this.textBoxKey);
        	this.panel2.Location = new System.Drawing.Point(5, 6);
        	this.panel2.Name = "panel2";
        	this.panel2.Size = new System.Drawing.Size(539, 35);
        	this.panel2.TabIndex = 2;
        	// 
        	// button1
        	// 
        	this.button1.Location = new System.Drawing.Point(328, 3);
        	this.button1.Name = "button1";
        	this.button1.Size = new System.Drawing.Size(89, 23);
        	this.button1.TabIndex = 4;
        	this.button1.Text = "Хешировать";
        	this.button1.UseVisualStyleBackColor = true;
        	this.button1.Click += new System.EventHandler(this.buttonHash_Click);
        	// 
        	// buttonValidate
        	// 
        	this.buttonValidate.Location = new System.Drawing.Point(436, 3);
        	this.buttonValidate.Name = "buttonValidate";
        	this.buttonValidate.Size = new System.Drawing.Size(75, 23);
        	this.buttonValidate.TabIndex = 3;
        	this.buttonValidate.Text = "Проверить";
        	this.buttonValidate.UseVisualStyleBackColor = true;
        	this.buttonValidate.Click += new System.EventHandler(this.buttonValidate_Click);
        	// 
        	// label3
        	// 
        	this.label3.AutoSize = true;
        	this.label3.Location = new System.Drawing.Point(174, 8);
        	this.label3.Name = "label3";
        	this.label3.Size = new System.Drawing.Size(33, 13);
        	this.label3.TabIndex = 3;
        	this.label3.Text = "Ключ";
        	// 
        	// textBoxKey
        	// 
        	this.textBoxKey.Location = new System.Drawing.Point(213, 5);
        	this.textBoxKey.Name = "textBoxKey";
        	this.textBoxKey.Size = new System.Drawing.Size(109, 20);
        	this.textBoxKey.TabIndex = 2;
        	this.textBoxKey.TextChanged += new System.EventHandler(this.TextBoxKeyTextChanged);
        	// 
        	// tab
        	// 
        	this.tab.Controls.Add(this.tabPage1);
        	this.tab.Controls.Add(this.tabPage2);
        	this.tab.Location = new System.Drawing.Point(12, 2);
        	this.tab.Multiline = true;
        	this.tab.Name = "tab";
        	this.tab.SelectedIndex = 0;
        	this.tab.Size = new System.Drawing.Size(558, 331);
        	this.tab.TabIndex = 0;
        	// 
        	// Form1
        	// 
        	this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
        	this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
        	this.ClientSize = new System.Drawing.Size(584, 337);
        	this.Controls.Add(this.tab);
        	this.Name = "Form1";
        	this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
        	this.Text = "ZIRKSiS Lab2";
        	this.tabPage2.ResumeLayout(false);
        	this.panel5.ResumeLayout(false);
        	this.panel5.PerformLayout();
        	this.panel4.ResumeLayout(false);
        	this.panel4.PerformLayout();
        	this.panel3.ResumeLayout(false);
        	this.panel3.PerformLayout();
        	this.tabPage1.ResumeLayout(false);
        	this.panel1.ResumeLayout(false);
        	this.panel1.PerformLayout();
        	this.panel2.ResumeLayout(false);
        	this.panel2.PerformLayout();
        	this.tab.ResumeLayout(false);
        	this.ResumeLayout(false);
        }
        
     

        private System.Windows.Forms.TabControl tab;
        private System.Windows.Forms.TabPage tabPage1;
        private System.Windows.Forms.TabPage tabPage2;
        private System.Windows.Forms.Button buttonValidate;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.Panel panel2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox textBoxKey;
        private System.Windows.Forms.RadioButton radioButton2;
        private System.Windows.Forms.RadioButton radioButton1;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox textBoxInMessage;
        private System.Windows.Forms.TextBox textBoxOutMessage;
        private System.Windows.Forms.Panel panel3;
        private System.Windows.Forms.OpenFileDialog openFileDialog1;
        private System.Windows.Forms.Button buttonCreateSign;
        private System.Windows.Forms.Button buttonSaveDigSign;
        private System.Windows.Forms.TextBox textBoxSaveDigSign;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.SaveFileDialog saveFileDialog1;
        private System.Windows.Forms.Panel panel4;
        private System.Windows.Forms.Button buttonSignFile;
        private System.Windows.Forms.Button buttonOpenDigSign;
        private System.Windows.Forms.Button buttonOpenFile;
        private System.Windows.Forms.TextBox textBoxOpenDigSign;
        private System.Windows.Forms.TextBox textBoxOpenFile;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.Panel panel5;
        private System.Windows.Forms.TextBox textBoxCheckUserName;
        private System.Windows.Forms.Button buttonCheckSign;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Button buttonOpenCheckSign;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Button buttonOpenCheckFile;
        private System.Windows.Forms.TextBox textBoxOpenCheckFile;
        private System.Windows.Forms.TextBox textBoxOpenCheckSign;
        private System.Windows.Forms.Label label8;
       
	}}


