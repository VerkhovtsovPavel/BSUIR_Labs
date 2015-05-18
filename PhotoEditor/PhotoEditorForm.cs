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
		private Point leftUp;
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
				_Y = new Point(image.Width, image.Height);
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
					blueColorTrack.Value, _X, _Y);
				mainPictureBox.Width = bitmap.Width;
				mainPictureBox.Height = bitmap.Height;
				mainPictureBox.Image = bitmap;
				zoomCounter++;
				//originalBitmap = bitmap;
			}
		}

		private void resizeMinusButton_Click(object sender, EventArgs e)
		{
			if (zoomCounter > -10) {
				bitmap = PhotoEditHelper.AdjustImage(new Bitmap(originalBitmap, bitmap.Width - 5 * (bitmap.Width / 100), bitmap.Height - 5 *  (bitmap.Height / 100)), brightnessTrack.Value, contrastTrack.Value, redColorTrack.Value, greenColorTrack.Value,
					blueColorTrack.Value, _X, _Y);
				mainPictureBox.Width = bitmap.Width;
				mainPictureBox.Height = bitmap.Height;
				mainPictureBox.Image = bitmap;
				zoomCounter--;
				//originalBitmap = bitmap;
			}
		}
        
		private void changeImage(object sender, EventArgs e)
		{
			if (isFromFile) {
				bitmap = PhotoEditHelper.AdjustImage(new Bitmap(originalBitmap, bitmap.Width, bitmap.Height), brightnessTrack.Value, contrastTrack.Value, redColorTrack.Value, greenColorTrack.Value,
					blueColorTrack.Value, _X, _Y);
				mainPictureBox.Image = bitmap;
				
				Graphics g =  Graphics.FromImage(this.mainPictureBox.Image);
				g.DrawRectangle(new Pen(Color.Black), _X.X, _X.Y, _Y.X - _X.X, _Y.Y - _X.Y);
			}
		}
		
		private void image_MouseUp(object sender, System.Windows.Forms.MouseEventArgs e)
		{
			/*Graphics.FromImage(this.mainPictureBox.Image).DrawImage(bmpBack, 0, 0, this.mainPictureBox.Image.Width,
				this.mainPictureBox.Image.Height);*/
			Graphics g = null;		
			g = Graphics.FromImage(this.mainPictureBox.Image);
			this.mainPictureBox.Update();
			this.mainPictureBox.Invalidate();
			_Y.X = e.X;
			_Y.Y = e.Y;
		//	Scale(new Point(_X, _Y), new Point(e.X, e.Y));
		
		}

		private void image_MouseMove(object sender, System.Windows.Forms.MouseEventArgs e)
		{
			if (e.Button == MouseButtons.Left) {
				Graphics.FromImage(this.mainPictureBox.Image).DrawImage(bmpBack, 0, 0, this.mainPictureBox.Image.Width,
					this.mainPictureBox.Image.Height);
				Graphics g = null;		
				g = Graphics.FromImage(this.mainPictureBox.Image);
				g.DrawRectangle(new Pen(Color.Black), _X.X, _X.Y, e.X - _X.X, e.Y - _X.Y);
				g = null;
				this.mainPictureBox.Update();
				this.mainPictureBox.Invalidate();
			}
		}

		private void image_MouseDown(object sender, System.Windows.Forms.MouseEventArgs e)
		{
			bmpBack = PhotoEditHelper.AdjustImage(originalBitmap, brightnessTrack.Value, contrastTrack.Value, redColorTrack.Value, greenColorTrack.Value,
					blueColorTrack.Value, _X, _Y);
			_X.X = e.X;
			_X.Y = e.Y;
		}
		
//TODO Array or list local(not full) filters.

		private Bitmap bmPhoto;
		private Bitmap bmpBack = new Bitmap(1,1);
		private Point _X = new Point(0,0), _Y;

	}
}
