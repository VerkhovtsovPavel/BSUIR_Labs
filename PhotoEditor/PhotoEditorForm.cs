using System;
using System.Drawing;
using System.Linq;
using System.Windows.Forms;

namespace PhotoEditor
{
	public partial class PhotoEditorForm : Form
	{
		private bool isFromFile = false;
		
		private Bitmap bitmap;
		private Bitmap originalBitmap;
		private Bitmap bmpBack;
		
		private Point leftUp = new Point(0, 0);
		private Point rigthDown;

		int zoomCounter;
		
		public PhotoEditorForm()
		{
			InitializeComponent();
		}

		private void openFile_Click(object sender, EventArgs e)
		{
			if (openFileDialog.ShowDialog() == DialogResult.OK) {
				string openFileName = openFileDialog.FileName;
				Image image = Image.FromFile(openFileName);
				bitmap = new Bitmap(image);
				originalBitmap = bitmap;
				mainPictureBox.Height = image.Height;
				mainPictureBox.Width = image.Width;
				mainPictureBox.Image = image;
				rigthDown = new Point(image.Width, image.Height);
				isFromFile = true;
				
				bmpBack = (Bitmap)this.mainPictureBox.Image.Clone();	
			}
		}

		private void exit_Click(object sender, EventArgs e)
		{
			if (ActiveForm != null)
				ActiveForm.Close();
		}

		private void saveFile_Click(object sender, EventArgs e)
		{
			if (isFromFile) {
				if (saveFileDialog.ShowDialog() == DialogResult.OK) {
					bitmap.Save(saveFileDialog.FileName);
				}
			}
		}

		private void resizePlusButton_Click(object sender, EventArgs e)
		{
			if (zoomCounter < 10) {
				bitmap = PhotoEditHelper.AdjustImage(new Bitmap(originalBitmap, bitmap.Width + 5 * (bitmap.Width / 100), bitmap.Height + 5 * (bitmap.Height / 100)), brightnessTrack.Value, contrastTrack.Value, redColorTrack.Value, greenColorTrack.Value,
					blueColorTrack.Value, leftUp, rigthDown);
				mainPictureBox.Width = bitmap.Width;
				mainPictureBox.Height = bitmap.Height;
				mainPictureBox.Image = bitmap;
				zoomCounter++;
			}
		}

		private void resizeMinusButton_Click(object sender, EventArgs e)
		{
			if (zoomCounter > -10) {
				bitmap = PhotoEditHelper.AdjustImage(new Bitmap(originalBitmap, bitmap.Width - 5 * (bitmap.Width / 100), bitmap.Height - 5 *  (bitmap.Height / 100)), brightnessTrack.Value, contrastTrack.Value, redColorTrack.Value, greenColorTrack.Value,
					blueColorTrack.Value, leftUp, rigthDown);
				mainPictureBox.Width = bitmap.Width;
				mainPictureBox.Height = bitmap.Height;
				mainPictureBox.Image = bitmap;
				zoomCounter--;
			}
		}
        
		private void changeImage(object sender, EventArgs e)
		{
			if (isFromFile) {
				bitmap = PhotoEditHelper.AdjustImage(new Bitmap(originalBitmap, bitmap.Width, bitmap.Height), brightnessTrack.Value, contrastTrack.Value, redColorTrack.Value, greenColorTrack.Value,
					blueColorTrack.Value, leftUp, rigthDown);
				mainPictureBox.Image = bitmap;
				
				Graphics g =  Graphics.FromImage(this.mainPictureBox.Image);
				g.DrawRectangle(new Pen(Color.Black), leftUp.X, leftUp.Y, rigthDown.X - leftUp.X, rigthDown.Y - leftUp.Y);
			}
		}
		
		private void image_MouseUp(object sender, System.Windows.Forms.MouseEventArgs e)
		{
			Graphics g = Graphics.FromImage(this.mainPictureBox.Image);
			this.mainPictureBox.Update();
			this.mainPictureBox.Invalidate();
			rigthDown.X = e.X;
			rigthDown.Y = e.Y;
		}

		private void image_MouseMove(object sender, System.Windows.Forms.MouseEventArgs e)
		{
			if (e.Button == MouseButtons.Left) {
				Graphics.FromImage(this.mainPictureBox.Image).DrawImage(bmpBack, 0, 0, this.mainPictureBox.Image.Width,
					this.mainPictureBox.Image.Height);
				Graphics g = Graphics.FromImage(this.mainPictureBox.Image);
				g.DrawRectangle(new Pen(Color.Black), leftUp.X, leftUp.Y, e.X - leftUp.X, e.Y - leftUp.Y);
				g = null;
				this.mainPictureBox.Update();
				this.mainPictureBox.Invalidate();
			}
		}

		private void image_MouseDown(object sender, System.Windows.Forms.MouseEventArgs e)
		{
			bmpBack = PhotoEditHelper.AdjustImage(originalBitmap, brightnessTrack.Value, contrastTrack.Value, redColorTrack.Value, greenColorTrack.Value,
					blueColorTrack.Value, leftUp, rigthDown);
			leftUp.X = e.X;
			leftUp.Y = e.Y;
		}
		
//TODO Array or list local(not full) filters.
	}
}
