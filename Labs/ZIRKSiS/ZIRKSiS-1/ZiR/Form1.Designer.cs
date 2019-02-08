namespace ZIRKSiS1 {
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

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent() {
        	this.panel1 = new System.Windows.Forms.Panel();
        	this.buttonSaveFile = new System.Windows.Forms.Button();
        	this.buttonOpenFile = new System.Windows.Forms.Button();
        	this.textBox2 = new System.Windows.Forms.TextBox();
        	this.textBox1 = new System.Windows.Forms.TextBox();
        	this.label2 = new System.Windows.Forms.Label();
        	this.label1 = new System.Windows.Forms.Label();
        	this.openFileDialog1 = new System.Windows.Forms.OpenFileDialog();
        	this.saveFileDialog1 = new System.Windows.Forms.SaveFileDialog();
        	this.panel2 = new System.Windows.Forms.Panel();
        	this.textBoxUserKey = new System.Windows.Forms.TextBox();
        	this.radioButton2 = new System.Windows.Forms.RadioButton();
        	this.radioButton1 = new System.Windows.Forms.RadioButton();
        	this.panel3 = new System.Windows.Forms.Panel();
        	this.buttonDecrypt = new System.Windows.Forms.Button();
        	this.buttonEncrypt = new System.Windows.Forms.Button();
        	this.panel1.SuspendLayout();
        	this.panel2.SuspendLayout();
        	this.panel3.SuspendLayout();
        	this.SuspendLayout();
        	// 
        	// panel1
        	// 
        	this.panel1.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
        	this.panel1.Controls.Add(this.buttonSaveFile);
        	this.panel1.Controls.Add(this.buttonOpenFile);
        	this.panel1.Controls.Add(this.textBox2);
        	this.panel1.Controls.Add(this.textBox1);
        	this.panel1.Controls.Add(this.label2);
        	this.panel1.Controls.Add(this.label1);
        	this.panel1.Location = new System.Drawing.Point(451, 12);
        	this.panel1.Name = "panel1";
        	this.panel1.Size = new System.Drawing.Size(433, 82);
        	this.panel1.TabIndex = 0;
        	// 
        	// buttonSaveFile
        	// 
        	this.buttonSaveFile.Location = new System.Drawing.Point(404, 55);
        	this.buttonSaveFile.Name = "buttonSaveFile";
        	this.buttonSaveFile.Size = new System.Drawing.Size(24, 23);
        	this.buttonSaveFile.TabIndex = 1;
        	this.buttonSaveFile.Text = "...";
        	this.buttonSaveFile.UseVisualStyleBackColor = true;
        	this.buttonSaveFile.Click += new System.EventHandler(this.buttonSaveFile_Click);
        	// 
        	// buttonOpenFile
        	// 
        	this.buttonOpenFile.Location = new System.Drawing.Point(404, 16);
        	this.buttonOpenFile.Name = "buttonOpenFile";
        	this.buttonOpenFile.Size = new System.Drawing.Size(24, 23);
        	this.buttonOpenFile.TabIndex = 0;
        	this.buttonOpenFile.Text = "...";
        	this.buttonOpenFile.UseVisualStyleBackColor = true;
        	this.buttonOpenFile.Click += new System.EventHandler(this.buttonOpenFile_Click);
        	// 
        	// textBox2
        	// 
        	this.textBox2.Location = new System.Drawing.Point(6, 57);
        	this.textBox2.Name = "textBox2";
        	this.textBox2.ReadOnly = true;
        	this.textBox2.Size = new System.Drawing.Size(392, 20);
        	this.textBox2.TabIndex = 1;
        	this.textBox2.TabStop = false;
        	// 
        	// textBox1
        	// 
        	this.textBox1.Location = new System.Drawing.Point(6, 18);
        	this.textBox1.Name = "textBox1";
        	this.textBox1.ReadOnly = true;
        	this.textBox1.Size = new System.Drawing.Size(392, 20);
        	this.textBox1.TabIndex = 1;
        	this.textBox1.TabStop = false;
        	// 
        	// label2
        	// 
        	this.label2.AutoSize = true;
        	this.label2.Location = new System.Drawing.Point(3, 41);
        	this.label2.Name = "label2";
        	this.label2.Size = new System.Drawing.Size(86, 13);
        	this.label2.TabIndex = 0;
        	this.label2.Text = "Выходной файл";
        	// 
        	// label1
        	// 
        	this.label1.AutoSize = true;
        	this.label1.Location = new System.Drawing.Point(3, 2);
        	this.label1.Name = "label1";
        	this.label1.Size = new System.Drawing.Size(87, 13);
        	this.label1.TabIndex = 0;
        	this.label1.Text = "Исходный файл";
        	// 
        	// panel2
        	// 
        	this.panel2.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
        	this.panel2.Controls.Add(this.textBoxUserKey);
        	this.panel2.Controls.Add(this.radioButton2);
        	this.panel2.Controls.Add(this.radioButton1);
        	this.panel2.Location = new System.Drawing.Point(12, 12);
        	this.panel2.Name = "panel2";
        	this.panel2.Size = new System.Drawing.Size(433, 32);
        	this.panel2.TabIndex = 1;
        	// 
        	// textBoxUserKey
        	// 
        	this.textBoxUserKey.Location = new System.Drawing.Point(205, 6);
        	this.textBoxUserKey.Name = "textBoxUserKey";
        	this.textBoxUserKey.Size = new System.Drawing.Size(223, 20);
        	this.textBoxUserKey.TabIndex = 2;
        	// 
        	// radioButton2
        	// 
        	this.radioButton2.AutoSize = true;
        	this.radioButton2.Checked = true;
        	this.radioButton2.Location = new System.Drawing.Point(136, 7);
        	this.radioButton2.Name = "radioButton2";
        	this.radioButton2.Size = new System.Drawing.Size(63, 17);
        	this.radioButton2.TabIndex = 1;
        	this.radioButton2.TabStop = true;
        	this.radioButton2.Text = "Пароль";
        	this.radioButton2.UseVisualStyleBackColor = true;
        	this.radioButton2.CheckedChanged += new System.EventHandler(this.radioButton2_CheckedChanged);
        	// 
        	// radioButton1
        	// 
        	this.radioButton1.AutoSize = true;
        	this.radioButton1.Location = new System.Drawing.Point(6, 7);
        	this.radioButton1.Name = "radioButton1";
        	this.radioButton1.Size = new System.Drawing.Size(110, 17);
        	this.radioButton1.TabIndex = 0;
        	this.radioButton1.Text = "Сеансовый ключ";
        	this.radioButton1.UseVisualStyleBackColor = true;
        	// 
        	// panel3
        	// 
        	this.panel3.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
        	this.panel3.Controls.Add(this.buttonDecrypt);
        	this.panel3.Controls.Add(this.buttonEncrypt);
        	this.panel3.Location = new System.Drawing.Point(12, 50);
        	this.panel3.Name = "panel3";
        	this.panel3.Size = new System.Drawing.Size(433, 44);
        	this.panel3.TabIndex = 2;
        	// 
        	// buttonDecrypt
        	// 
        	this.buttonDecrypt.Location = new System.Drawing.Point(280, 6);
        	this.buttonDecrypt.Name = "buttonDecrypt";
        	this.buttonDecrypt.Size = new System.Drawing.Size(110, 23);
        	this.buttonDecrypt.TabIndex = 1;
        	this.buttonDecrypt.Text = "Расшифровать";
        	this.buttonDecrypt.UseVisualStyleBackColor = true;
        	this.buttonDecrypt.Click += new System.EventHandler(this.buttonDecrypt_Click);
        	// 
        	// buttonEncrypt
        	// 
        	this.buttonEncrypt.Location = new System.Drawing.Point(33, 6);
        	this.buttonEncrypt.Name = "buttonEncrypt";
        	this.buttonEncrypt.Size = new System.Drawing.Size(110, 23);
        	this.buttonEncrypt.TabIndex = 0;
        	this.buttonEncrypt.Text = "Зашифровать";
        	this.buttonEncrypt.UseVisualStyleBackColor = true;
        	this.buttonEncrypt.Click += new System.EventHandler(this.buttonEncrypt_Click);
        	// 
        	// Form1
        	// 
        	this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
        	this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
        	this.ClientSize = new System.Drawing.Size(895, 105);
        	this.Controls.Add(this.panel3);
        	this.Controls.Add(this.panel2);
        	this.Controls.Add(this.panel1);
        	this.Name = "Form1";
        	this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
        	this.Text = "ZIRKSiS Lab1";
        	this.panel1.ResumeLayout(false);
        	this.panel1.PerformLayout();
        	this.panel2.ResumeLayout(false);
        	this.panel2.PerformLayout();
        	this.panel3.ResumeLayout(false);
        	this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.Button buttonSaveFile;
        private System.Windows.Forms.Button buttonOpenFile;
        private System.Windows.Forms.TextBox textBox2;
        private System.Windows.Forms.TextBox textBox1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.OpenFileDialog openFileDialog1;
        private System.Windows.Forms.SaveFileDialog saveFileDialog1;
        private System.Windows.Forms.Panel panel2;
        private System.Windows.Forms.RadioButton radioButton2;
        private System.Windows.Forms.RadioButton radioButton1;
        private System.Windows.Forms.TextBox textBoxUserKey;
        private System.Windows.Forms.Panel panel3;
        private System.Windows.Forms.Button buttonDecrypt;
        private System.Windows.Forms.Button buttonEncrypt;
    }
}

