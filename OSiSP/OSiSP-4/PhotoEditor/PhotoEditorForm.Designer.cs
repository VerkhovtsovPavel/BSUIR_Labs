namespace PhotoEditor
{
    partial class PhotoEditorForm
    {
        /// <summary>
        /// Требуется переменная конструктора.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Освободить все используемые ресурсы.
        /// </summary>
        /// <param name="disposing">истинно, если управляемый ресурс должен быть удален; иначе ложно.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Код, автоматически созданный конструктором форм Windows

        /// <summary>
        /// Обязательный метод для поддержки конструктора - не изменяйте
        /// содержимое данного метода при помощи редактора кода.
        /// </summary>
        private void InitializeComponent()
        {
        	this.mainPictureBox = new System.Windows.Forms.PictureBox();
        	this.openFileDialog = new System.Windows.Forms.OpenFileDialog();
        	this.menuStrip1 = new System.Windows.Forms.MenuStrip();
        	this.fileToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
        	this.openFile = new System.Windows.Forms.ToolStripMenuItem();
        	this.saveFile = new System.Windows.Forms.ToolStripMenuItem();
        	this.exit = new System.Windows.Forms.ToolStripMenuItem();
        	this.saveFileDialog = new System.Windows.Forms.SaveFileDialog();
        	this.labelBrightness = new System.Windows.Forms.Label();
        	this.contrastTrack = new System.Windows.Forms.TrackBar();
        	this.greenColorTrack = new System.Windows.Forms.TrackBar();
        	this.redColorTrack = new System.Windows.Forms.TrackBar();
        	this.contrastLabel = new System.Windows.Forms.Label();
        	this.redColorLabel = new System.Windows.Forms.Label();
        	this.greenColorLabel = new System.Windows.Forms.Label();
        	this.blueColorTrack = new System.Windows.Forms.TrackBar();
        	this.label1 = new System.Windows.Forms.Label();
        	this.resizePlusButton = new System.Windows.Forms.Button();
        	this.resizaMinusButton = new System.Windows.Forms.Button();
        	this.brightnessTrack = new System.Windows.Forms.TrackBar();
        	((System.ComponentModel.ISupportInitialize)(this.mainPictureBox)).BeginInit();
        	this.menuStrip1.SuspendLayout();
        	((System.ComponentModel.ISupportInitialize)(this.contrastTrack)).BeginInit();
        	((System.ComponentModel.ISupportInitialize)(this.greenColorTrack)).BeginInit();
        	((System.ComponentModel.ISupportInitialize)(this.redColorTrack)).BeginInit();
        	((System.ComponentModel.ISupportInitialize)(this.blueColorTrack)).BeginInit();
        	((System.ComponentModel.ISupportInitialize)(this.brightnessTrack)).BeginInit();
        	this.SuspendLayout();
        	// 
        	// mainPictureBox
        	// 
        	this.mainPictureBox.Location = new System.Drawing.Point(249, 23);
        	this.mainPictureBox.Margin = new System.Windows.Forms.Padding(2);
        	this.mainPictureBox.Name = "mainPictureBox";
        	this.mainPictureBox.Size = new System.Drawing.Size(627, 368);
        	this.mainPictureBox.TabIndex = 0;
        	this.mainPictureBox.TabStop = false;
        	this.mainPictureBox.MouseDown += new System.Windows.Forms.MouseEventHandler(this.image_MouseDown);
        	this.mainPictureBox.MouseMove += new System.Windows.Forms.MouseEventHandler(this.image_MouseMove);
        	this.mainPictureBox.MouseUp += new System.Windows.Forms.MouseEventHandler(this.image_MouseUp);
        	// 
        	// openFileDialog
        	// 
        	this.openFileDialog.Filter = "\"Изображения (*.jpg)|*.jpg|Все файлы (*.*)|*.*\"";
        	// 
        	// menuStrip1
        	// 
        	this.menuStrip1.ImageScalingSize = new System.Drawing.Size(24, 24);
        	this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
			this.fileToolStripMenuItem});
        	this.menuStrip1.Location = new System.Drawing.Point(0, 0);
        	this.menuStrip1.Name = "menuStrip1";
        	this.menuStrip1.Padding = new System.Windows.Forms.Padding(4, 1, 0, 1);
        	this.menuStrip1.Size = new System.Drawing.Size(884, 24);
        	this.menuStrip1.TabIndex = 2;
        	this.menuStrip1.Text = "menuStrip1";
        	// 
        	// fileToolStripMenuItem
        	// 
        	this.fileToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
			this.openFile,
			this.saveFile,
			this.exit});
        	this.fileToolStripMenuItem.Name = "fileToolStripMenuItem";
        	this.fileToolStripMenuItem.Size = new System.Drawing.Size(37, 22);
        	this.fileToolStripMenuItem.Text = "File";
        	// 
        	// openFile
        	// 
        	this.openFile.Name = "openFile";
        	this.openFile.Size = new System.Drawing.Size(103, 22);
        	this.openFile.Text = "Open";
        	this.openFile.Click += new System.EventHandler(this.openFile_Click);
        	// 
        	// saveFile
        	// 
        	this.saveFile.Name = "saveFile";
        	this.saveFile.Size = new System.Drawing.Size(103, 22);
        	this.saveFile.Text = "Save";
        	this.saveFile.Click += new System.EventHandler(this.saveFile_Click);
        	// 
        	// exit
        	// 
        	this.exit.Name = "exit";
        	this.exit.Size = new System.Drawing.Size(103, 22);
        	this.exit.Text = "Exit";
        	this.exit.Click += new System.EventHandler(this.exit_Click);
        	// 
        	// labelBrightness
        	// 
        	this.labelBrightness.AutoSize = true;
        	this.labelBrightness.Location = new System.Drawing.Point(6, 40);
        	this.labelBrightness.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
        	this.labelBrightness.Name = "labelBrightness";
        	this.labelBrightness.Size = new System.Drawing.Size(56, 13);
        	this.labelBrightness.TabIndex = 5;
        	this.labelBrightness.Text = "Brightness";
        	// 
        	// contrastTrack
        	// 
        	this.contrastTrack.Location = new System.Drawing.Point(67, 75);
        	this.contrastTrack.Margin = new System.Windows.Forms.Padding(2);
        	this.contrastTrack.Minimum = 1;
        	this.contrastTrack.Name = "contrastTrack";
        	this.contrastTrack.Size = new System.Drawing.Size(168, 45);
        	this.contrastTrack.TabIndex = 6;
        	this.contrastTrack.Tag = "Contrast";
        	this.contrastTrack.Value = 1;
        	this.contrastTrack.Scroll += new System.EventHandler(this.changeImage);
        	// 
        	// greenColorTrack
        	// 
        	this.greenColorTrack.Location = new System.Drawing.Point(67, 161);
        	this.greenColorTrack.Margin = new System.Windows.Forms.Padding(2);
        	this.greenColorTrack.Maximum = 255;
        	this.greenColorTrack.Name = "greenColorTrack";
        	this.greenColorTrack.Size = new System.Drawing.Size(168, 45);
        	this.greenColorTrack.TabIndex = 8;
        	this.greenColorTrack.Tag = "greenColor";
        	this.greenColorTrack.Scroll += new System.EventHandler(this.changeImage);
        	// 
        	// redColorTrack
        	// 
        	this.redColorTrack.Location = new System.Drawing.Point(67, 112);
        	this.redColorTrack.Margin = new System.Windows.Forms.Padding(2);
        	this.redColorTrack.Maximum = 255;
        	this.redColorTrack.Name = "redColorTrack";
        	this.redColorTrack.Size = new System.Drawing.Size(168, 45);
        	this.redColorTrack.TabIndex = 7;
        	this.redColorTrack.Tag = "redColor";
        	this.redColorTrack.Scroll += new System.EventHandler(this.changeImage);
        	// 
        	// contrastLabel
        	// 
        	this.contrastLabel.AutoSize = true;
        	this.contrastLabel.Location = new System.Drawing.Point(6, 75);
        	this.contrastLabel.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
        	this.contrastLabel.Name = "contrastLabel";
        	this.contrastLabel.Size = new System.Drawing.Size(46, 13);
        	this.contrastLabel.TabIndex = 9;
        	this.contrastLabel.Text = "Contrast";
        	// 
        	// redColorLabel
        	// 
        	this.redColorLabel.AutoSize = true;
        	this.redColorLabel.Location = new System.Drawing.Point(6, 124);
        	this.redColorLabel.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
        	this.redColorLabel.Name = "redColorLabel";
        	this.redColorLabel.Size = new System.Drawing.Size(27, 13);
        	this.redColorLabel.TabIndex = 10;
        	this.redColorLabel.Text = "Red";
        	// 
        	// greenColorLabel
        	// 
        	this.greenColorLabel.AutoSize = true;
        	this.greenColorLabel.Location = new System.Drawing.Point(6, 169);
        	this.greenColorLabel.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
        	this.greenColorLabel.Name = "greenColorLabel";
        	this.greenColorLabel.Size = new System.Drawing.Size(36, 13);
        	this.greenColorLabel.TabIndex = 11;
        	this.greenColorLabel.Text = "Green";
        	// 
        	// blueColorTrack
        	// 
        	this.blueColorTrack.Location = new System.Drawing.Point(67, 210);
        	this.blueColorTrack.Margin = new System.Windows.Forms.Padding(2);
        	this.blueColorTrack.Maximum = 255;
        	this.blueColorTrack.Name = "blueColorTrack";
        	this.blueColorTrack.Size = new System.Drawing.Size(168, 45);
        	this.blueColorTrack.TabIndex = 12;
        	this.blueColorTrack.Tag = "blueColor";
        	this.blueColorTrack.Scroll += new System.EventHandler(this.changeImage);
        	// 
        	// label1
        	// 
        	this.label1.AutoSize = true;
        	this.label1.Location = new System.Drawing.Point(6, 210);
        	this.label1.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
        	this.label1.Name = "label1";
        	this.label1.Size = new System.Drawing.Size(28, 13);
        	this.label1.TabIndex = 13;
        	this.label1.Text = "Blue";
        	// 
        	// resizePlusButton
        	// 
        	this.resizePlusButton.Location = new System.Drawing.Point(6, 262);
        	this.resizePlusButton.Margin = new System.Windows.Forms.Padding(2);
        	this.resizePlusButton.Name = "resizePlusButton";
        	this.resizePlusButton.Size = new System.Drawing.Size(26, 27);
        	this.resizePlusButton.TabIndex = 15;
        	this.resizePlusButton.Text = "+";
        	this.resizePlusButton.UseVisualStyleBackColor = true;
        	this.resizePlusButton.Click += new System.EventHandler(this.resizePlusButton_Click);
        	// 
        	// resizaMinusButton
        	// 
        	this.resizaMinusButton.Location = new System.Drawing.Point(37, 262);
        	this.resizaMinusButton.Margin = new System.Windows.Forms.Padding(2);
        	this.resizaMinusButton.Name = "resizaMinusButton";
        	this.resizaMinusButton.Size = new System.Drawing.Size(26, 27);
        	this.resizaMinusButton.TabIndex = 16;
        	this.resizaMinusButton.Text = "-";
        	this.resizaMinusButton.UseVisualStyleBackColor = true;
        	this.resizaMinusButton.Click += new System.EventHandler(this.resizeMinusButton_Click);
        	// 
        	// brightnessTrack
        	// 
        	this.brightnessTrack.Location = new System.Drawing.Point(67, 26);
        	this.brightnessTrack.Margin = new System.Windows.Forms.Padding(2);
        	this.brightnessTrack.Maximum = 100;
        	this.brightnessTrack.Name = "brightnessTrack";
        	this.brightnessTrack.Size = new System.Drawing.Size(168, 45);
        	this.brightnessTrack.TabIndex = 4;
        	this.brightnessTrack.Tag = "Brightess";
        	this.brightnessTrack.Scroll += new System.EventHandler(this.changeImage);
        	// 
        	// PhotoEditorForm
        	// 
        	this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
        	this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
        	this.ClientSize = new System.Drawing.Size(884, 399);
        	this.Controls.Add(this.resizaMinusButton);
        	this.Controls.Add(this.resizePlusButton);
        	this.Controls.Add(this.label1);
        	this.Controls.Add(this.blueColorTrack);
        	this.Controls.Add(this.greenColorLabel);
        	this.Controls.Add(this.redColorLabel);
        	this.Controls.Add(this.contrastLabel);
        	this.Controls.Add(this.greenColorTrack);
        	this.Controls.Add(this.redColorTrack);
        	this.Controls.Add(this.contrastTrack);
        	this.Controls.Add(this.labelBrightness);
        	this.Controls.Add(this.brightnessTrack);
        	this.Controls.Add(this.mainPictureBox);
        	this.Controls.Add(this.menuStrip1);
        	this.MainMenuStrip = this.menuStrip1;
        	this.Margin = new System.Windows.Forms.Padding(2);
        	this.Name = "PhotoEditorForm";
        	this.Text = "Photo Editor";
        	this.MouseClick += new System.Windows.Forms.MouseEventHandler(this.deleteSelection);
        	((System.ComponentModel.ISupportInitialize)(this.mainPictureBox)).EndInit();
        	this.menuStrip1.ResumeLayout(false);
        	this.menuStrip1.PerformLayout();
        	((System.ComponentModel.ISupportInitialize)(this.contrastTrack)).EndInit();
        	((System.ComponentModel.ISupportInitialize)(this.greenColorTrack)).EndInit();
        	((System.ComponentModel.ISupportInitialize)(this.redColorTrack)).EndInit();
        	((System.ComponentModel.ISupportInitialize)(this.blueColorTrack)).EndInit();
        	((System.ComponentModel.ISupportInitialize)(this.brightnessTrack)).EndInit();
        	this.ResumeLayout(false);
        	this.PerformLayout();

        }
        #endregion

        private System.Windows.Forms.PictureBox mainPictureBox;
        private System.Windows.Forms.OpenFileDialog openFileDialog;
        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem fileToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem openFile;
        private System.Windows.Forms.ToolStripMenuItem saveFile;
        private System.Windows.Forms.ToolStripMenuItem exit;
        private System.Windows.Forms.SaveFileDialog saveFileDialog;
        private System.Windows.Forms.TrackBar brightnessTrack;
        private System.Windows.Forms.Label labelBrightness;
        private System.Windows.Forms.TrackBar contrastTrack;
        private System.Windows.Forms.TrackBar greenColorTrack;
        private System.Windows.Forms.TrackBar redColorTrack;
        private System.Windows.Forms.Label contrastLabel;
        private System.Windows.Forms.Label redColorLabel;
        private System.Windows.Forms.Label greenColorLabel;
        private System.Windows.Forms.TrackBar blueColorTrack;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button resizePlusButton;
        private System.Windows.Forms.Button resizaMinusButton;
    }
}

