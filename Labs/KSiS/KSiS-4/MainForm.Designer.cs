namespace HTTPServer
{
    partial class MainForm
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
            this.richTextBoxOutput = new System.Windows.Forms.RichTextBox();
            this.SuspendLayout();
            // 
            // richTextBoxOutput
            // 
            this.richTextBoxOutput.BackColor = System.Drawing.SystemColors.Window;
            this.richTextBoxOutput.Location = new System.Drawing.Point(10, 10);
            this.richTextBoxOutput.Name = "richTextBoxOutput";
            this.richTextBoxOutput.ReadOnly = true;
            this.richTextBoxOutput.Size = new System.Drawing.Size(660, 640);
            this.richTextBoxOutput.TabIndex = 0;
            this.richTextBoxOutput.Text = "";
            // 
            // MainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(684, 661);
            this.Controls.Add(this.richTextBoxOutput);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.MaximizeBox = false;
            this.Name = "MainForm";
            this.ShowIcon = false;
            this.Text = "Server";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.MainForm_FormClosing);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.RichTextBox richTextBoxOutput;
    }
}

