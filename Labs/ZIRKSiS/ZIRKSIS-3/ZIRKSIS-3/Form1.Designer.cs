namespace Lab3
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
            this.groupBox2 = new System.Windows.Forms.GroupBox();
            this.label1 = new System.Windows.Forms.Label();
            this.tbxNameKerberos = new System.Windows.Forms.TextBox();
            this.tbxPassordKerberos = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.btnIdentificateKerberos = new System.Windows.Forms.Button();
            this.tbxLogKerberos = new System.Windows.Forms.TextBox();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.lblName = new System.Windows.Forms.Label();
            this.tbxPassword = new System.Windows.Forms.TextBox();
            this.btnIdentificateClient = new System.Windows.Forms.Button();
            this.lblPassword = new System.Windows.Forms.Label();
            this.btnRegisterOnServer = new System.Windows.Forms.Button();
            this.tbxName = new System.Windows.Forms.TextBox();
            this.tbxLog = new System.Windows.Forms.TextBox();
            this.groupBox3 = new System.Windows.Forms.GroupBox();
            this.groupBox4 = new System.Windows.Forms.GroupBox();
            this.tabControl1 = new System.Windows.Forms.TabControl();
            this.tabPage1 = new System.Windows.Forms.TabPage();
            this.tabPage2 = new System.Windows.Forms.TabPage();
            this.groupBox2.SuspendLayout();
            this.groupBox1.SuspendLayout();
            this.groupBox3.SuspendLayout();
            this.groupBox4.SuspendLayout();
            this.tabControl1.SuspendLayout();
            this.tabPage1.SuspendLayout();
            this.tabPage2.SuspendLayout();
            this.SuspendLayout();
            // 
            // groupBox2
            // 
            this.groupBox2.Controls.Add(this.label1);
            this.groupBox2.Controls.Add(this.tbxNameKerberos);
            this.groupBox2.Controls.Add(this.tbxPassordKerberos);
            this.groupBox2.Controls.Add(this.label2);
            this.groupBox2.Controls.Add(this.btnIdentificateKerberos);
            this.groupBox2.Font = new System.Drawing.Font("Arial", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.groupBox2.ForeColor = System.Drawing.SystemColors.WindowText;
            this.groupBox2.Location = new System.Drawing.Point(5, 23);
            this.groupBox2.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.groupBox2.Name = "groupBox2";
            this.groupBox2.Padding = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.groupBox2.Size = new System.Drawing.Size(122, 210);
            this.groupBox2.TabIndex = 41;
            this.groupBox2.TabStop = false;
            this.groupBox2.Text = "Login";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Arial", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.label1.ForeColor = System.Drawing.SystemColors.WindowText;
            this.label1.Location = new System.Drawing.Point(7, 20);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(40, 14);
            this.label1.TabIndex = 36;
            this.label1.Text = "Name :";
            // 
            // tbxNameKerberos
            // 
            this.tbxNameKerberos.Font = new System.Drawing.Font("Arial", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.tbxNameKerberos.Location = new System.Drawing.Point(10, 38);
            this.tbxNameKerberos.Margin = new System.Windows.Forms.Padding(2);
            this.tbxNameKerberos.Name = "tbxNameKerberos";
            this.tbxNameKerberos.Size = new System.Drawing.Size(102, 20);
            this.tbxNameKerberos.TabIndex = 0;
            // 
            // tbxPassordKerberos
            // 
            this.tbxPassordKerberos.Font = new System.Drawing.Font("Arial", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.tbxPassordKerberos.Location = new System.Drawing.Point(10, 92);
            this.tbxPassordKerberos.Margin = new System.Windows.Forms.Padding(2);
            this.tbxPassordKerberos.Name = "tbxPassordKerberos";
            this.tbxPassordKerberos.Size = new System.Drawing.Size(102, 20);
            this.tbxPassordKerberos.TabIndex = 1;
            this.tbxPassordKerberos.UseSystemPasswordChar = true;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Arial", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.label2.ForeColor = System.Drawing.SystemColors.WindowText;
            this.label2.Location = new System.Drawing.Point(7, 74);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(63, 14);
            this.label2.TabIndex = 34;
            this.label2.Text = "Password :";
            // 
            // btnIdentificateKerberos
            // 
            this.btnIdentificateKerberos.BackColor = System.Drawing.SystemColors.ButtonHighlight;
            this.btnIdentificateKerberos.Font = new System.Drawing.Font("Arial", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.btnIdentificateKerberos.ForeColor = System.Drawing.SystemColors.WindowText;
            this.btnIdentificateKerberos.Location = new System.Drawing.Point(10, 116);
            this.btnIdentificateKerberos.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.btnIdentificateKerberos.Name = "btnIdentificateKerberos";
            this.btnIdentificateKerberos.Size = new System.Drawing.Size(102, 30);
            this.btnIdentificateKerberos.TabIndex = 2;
            this.btnIdentificateKerberos.Text = "Authenticate";
            this.btnIdentificateKerberos.UseVisualStyleBackColor = false;
            this.btnIdentificateKerberos.Click += new System.EventHandler(this.btnIdentificateKerberos_Click);
            // 
            // tbxLogKerberos
            // 
            this.tbxLogKerberos.Font = new System.Drawing.Font("Arial", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.tbxLogKerberos.ForeColor = System.Drawing.SystemColors.ControlText;
            this.tbxLogKerberos.Location = new System.Drawing.Point(133, 31);
            this.tbxLogKerberos.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.tbxLogKerberos.Multiline = true;
            this.tbxLogKerberos.Name = "tbxLogKerberos";
            this.tbxLogKerberos.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
            this.tbxLogKerberos.Size = new System.Drawing.Size(329, 202);
            this.tbxLogKerberos.TabIndex = 40;
            this.tbxLogKerberos.TabStop = false;
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.lblName);
            this.groupBox1.Controls.Add(this.tbxPassword);
            this.groupBox1.Controls.Add(this.btnIdentificateClient);
            this.groupBox1.Controls.Add(this.lblPassword);
            this.groupBox1.Controls.Add(this.btnRegisterOnServer);
            this.groupBox1.Controls.Add(this.tbxName);
            this.groupBox1.Font = new System.Drawing.Font("Arial", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.groupBox1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.groupBox1.Location = new System.Drawing.Point(15, 18);
            this.groupBox1.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Padding = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.groupBox1.Size = new System.Drawing.Size(128, 203);
            this.groupBox1.TabIndex = 30;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Login";
            // 
            // lblName
            // 
            this.lblName.AutoSize = true;
            this.lblName.Font = new System.Drawing.Font("Arial", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.lblName.ForeColor = System.Drawing.SystemColors.ControlText;
            this.lblName.Location = new System.Drawing.Point(5, 25);
            this.lblName.Name = "lblName";
            this.lblName.Size = new System.Drawing.Size(40, 14);
            this.lblName.TabIndex = 26;
            this.lblName.Text = "Name :";
            // 
            // tbxPassword
            // 
            this.tbxPassword.Font = new System.Drawing.Font("Arial", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.tbxPassword.Location = new System.Drawing.Point(10, 98);
            this.tbxPassword.Margin = new System.Windows.Forms.Padding(2);
            this.tbxPassword.Name = "tbxPassword";
            this.tbxPassword.Size = new System.Drawing.Size(102, 20);
            this.tbxPassword.TabIndex = 1;
            this.tbxPassword.UseSystemPasswordChar = true;
            // 
            // btnIdentificateClient
            // 
            this.btnIdentificateClient.BackColor = System.Drawing.SystemColors.ButtonHighlight;
            this.btnIdentificateClient.Font = new System.Drawing.Font("Arial", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.btnIdentificateClient.ForeColor = System.Drawing.SystemColors.ControlText;
            this.btnIdentificateClient.Location = new System.Drawing.Point(10, 163);
            this.btnIdentificateClient.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.btnIdentificateClient.Name = "btnIdentificateClient";
            this.btnIdentificateClient.Size = new System.Drawing.Size(102, 32);
            this.btnIdentificateClient.TabIndex = 3;
            this.btnIdentificateClient.Text = "Authenticate";
            this.btnIdentificateClient.UseVisualStyleBackColor = false;
            this.btnIdentificateClient.Click += new System.EventHandler(this.btnIdentificateClient_Click);
            // 
            // lblPassword
            // 
            this.lblPassword.AutoSize = true;
            this.lblPassword.Font = new System.Drawing.Font("Arial", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.lblPassword.ForeColor = System.Drawing.SystemColors.ControlText;
            this.lblPassword.Location = new System.Drawing.Point(5, 79);
            this.lblPassword.Name = "lblPassword";
            this.lblPassword.Size = new System.Drawing.Size(63, 14);
            this.lblPassword.TabIndex = 24;
            this.lblPassword.Text = "Password :";
            // 
            // btnRegisterOnServer
            // 
            this.btnRegisterOnServer.BackColor = System.Drawing.SystemColors.ButtonHighlight;
            this.btnRegisterOnServer.Font = new System.Drawing.Font("Arial", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.btnRegisterOnServer.ForeColor = System.Drawing.SystemColors.ControlText;
            this.btnRegisterOnServer.Location = new System.Drawing.Point(10, 124);
            this.btnRegisterOnServer.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.btnRegisterOnServer.Name = "btnRegisterOnServer";
            this.btnRegisterOnServer.Size = new System.Drawing.Size(102, 32);
            this.btnRegisterOnServer.TabIndex = 2;
            this.btnRegisterOnServer.Text = "Register";
            this.btnRegisterOnServer.UseVisualStyleBackColor = false;
            this.btnRegisterOnServer.Click += new System.EventHandler(this.btnRegisterOnServer_Click);
            // 
            // tbxName
            // 
            this.tbxName.Font = new System.Drawing.Font("Arial", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.tbxName.Location = new System.Drawing.Point(10, 44);
            this.tbxName.Margin = new System.Windows.Forms.Padding(2);
            this.tbxName.Name = "tbxName";
            this.tbxName.Size = new System.Drawing.Size(102, 20);
            this.tbxName.TabIndex = 0;
            // 
            // tbxLog
            // 
            this.tbxLog.Font = new System.Drawing.Font("Arial", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.tbxLog.ForeColor = System.Drawing.SystemColors.ControlText;
            this.tbxLog.Location = new System.Drawing.Point(149, 20);
            this.tbxLog.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.tbxLog.Multiline = true;
            this.tbxLog.Name = "tbxLog";
            this.tbxLog.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
            this.tbxLog.Size = new System.Drawing.Size(313, 211);
            this.tbxLog.TabIndex = 29;
            this.tbxLog.TabStop = false;
            // 
            // groupBox3
            // 
            this.groupBox3.Controls.Add(this.tbxLogKerberos);
            this.groupBox3.Controls.Add(this.groupBox2);
            this.groupBox3.Font = new System.Drawing.Font("Arial", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.groupBox3.ForeColor = System.Drawing.SystemColors.WindowText;
            this.groupBox3.Location = new System.Drawing.Point(6, 6);
            this.groupBox3.Name = "groupBox3";
            this.groupBox3.Size = new System.Drawing.Size(468, 240);
            this.groupBox3.TabIndex = 1;
            this.groupBox3.TabStop = false;
            // 
            // groupBox4
            // 
            this.groupBox4.Controls.Add(this.tbxLog);
            this.groupBox4.Controls.Add(this.groupBox1);
            this.groupBox4.Font = new System.Drawing.Font("Arial", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.groupBox4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.groupBox4.Location = new System.Drawing.Point(6, 6);
            this.groupBox4.Name = "groupBox4";
            this.groupBox4.Size = new System.Drawing.Size(468, 238);
            this.groupBox4.TabIndex = 2;
            this.groupBox4.TabStop = false;
            // 
            // tabControl1
            // 
            this.tabControl1.Controls.Add(this.tabPage1);
            this.tabControl1.Controls.Add(this.tabPage2);
            this.tabControl1.Location = new System.Drawing.Point(12, 12);
            this.tabControl1.Name = "tabControl1";
            this.tabControl1.SelectedIndex = 0;
            this.tabControl1.Size = new System.Drawing.Size(488, 281);
            this.tabControl1.TabIndex = 39;
            this.tabControl1.SelectedIndexChanged += new System.EventHandler(this.tabControl1_SelectedIndexChanged);
            // 
            // tabPage1
            // 
            this.tabPage1.Controls.Add(this.groupBox3);
            this.tabPage1.Location = new System.Drawing.Point(4, 25);
            this.tabPage1.Name = "tabPage1";
            this.tabPage1.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage1.Size = new System.Drawing.Size(480, 252);
            this.tabPage1.TabIndex = 0;
            this.tabPage1.Text = "Kerberos";
            this.tabPage1.UseVisualStyleBackColor = true;
            // 
            // tabPage2
            // 
            this.tabPage2.Controls.Add(this.groupBox4);
            this.tabPage2.Location = new System.Drawing.Point(4, 25);
            this.tabPage2.Name = "tabPage2";
            this.tabPage2.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage2.Size = new System.Drawing.Size(480, 252);
            this.tabPage2.TabIndex = 1;
            this.tabPage2.Text = "S/Key";
            this.tabPage2.UseVisualStyleBackColor = true;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.White;
            this.ClientSize = new System.Drawing.Size(518, 302);
            this.Controls.Add(this.tabControl1);
            this.Font = new System.Drawing.Font("Arial", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.Name = "Form1";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "ZIRKSiS Lab3";
            this.groupBox2.ResumeLayout(false);
            this.groupBox2.PerformLayout();
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.groupBox3.ResumeLayout(false);
            this.groupBox3.PerformLayout();
            this.groupBox4.ResumeLayout(false);
            this.groupBox4.PerformLayout();
            this.tabControl1.ResumeLayout(false);
            this.tabPage1.ResumeLayout(false);
            this.tabPage2.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TextBox tbxLog;
        private System.Windows.Forms.Button btnIdentificateClient;
        private System.Windows.Forms.Button btnRegisterOnServer;
        private System.Windows.Forms.Label lblName;
        private System.Windows.Forms.TextBox tbxName;
        private System.Windows.Forms.Label lblPassword;
        private System.Windows.Forms.TextBox tbxPassword;
        private System.Windows.Forms.Button btnIdentificateKerberos;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox tbxNameKerberos;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox tbxPassordKerberos;
        private System.Windows.Forms.TextBox tbxLogKerberos;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.GroupBox groupBox2;
        private System.Windows.Forms.GroupBox groupBox3;
        private System.Windows.Forms.GroupBox groupBox4;
        private System.Windows.Forms.TabControl tabControl1;
        private System.Windows.Forms.TabPage tabPage1;
        private System.Windows.Forms.TabPage tabPage2;
    }
}

