using System;
using System.IO;
using System.Windows;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using Microsoft.Win32;
using PhotoEditor;


namespace WPF
{
	public partial class MainWindow : Window
	{
		private BitmapImage bitmap;
		private BitmapImage originalBitmap;

		private int zoomCounter;
		private bool isFromFile;
		
		public MainWindow()
		{
			InitializeComponent();
		}
		
		void openFile_Click(object sender, RoutedEventArgs e)
		{
			OpenFileDialog openFileDialog = new OpenFileDialog();
			if (openFileDialog.ShowDialog() == true) {
				string openFileName = openFileDialog.FileName;
                
				bitmap = new BitmapImage(new Uri(openFileName, UriKind.RelativeOrAbsolute));
				originalBitmap = bitmap;
				mainPictureBox.Height = bitmap.Height;
				mainPictureBox.Width = bitmap.Width;
				mainPictureBox.Source = bitmap;
				isFromFile = true;
			}
		}
		
		void saveFile_Click(object sender, RoutedEventArgs e)
		{
			if (isFromFile) {
				SaveFileDialog saveFileDialog = new SaveFileDialog();
				if (saveFileDialog.ShowDialog() == true) {
					JpegBitmapEncoder jpegBitmapEncoder = new JpegBitmapEncoder();
					jpegBitmapEncoder.QualityLevel = 100;
					jpegBitmapEncoder.Frames.Add(BitmapFrame.Create(bitmap));
					string fileName = saveFileDialog.FileName;
					
					if (File.Exists(fileName)){
						File.Delete(fileName);
					}
					
					FileStream fileStream = new FileStream(fileName, FileMode.CreateNew);
					jpegBitmapEncoder.Save(fileStream);
					fileStream.Close();
				}
			}
		}
		
		private void changeImage(object sender, RoutedPropertyChangedEventArgs<double> e){
			if (isFromFile) {
				bitmap = PhotoEditHelper.AdjustImage(originalBitmap, brightnessTrack.Value, contrastTrack.Value, redColorTrack.Value, greenColorTrack.Value,
					blueColorTrack.Value);
				mainPictureBox.Source = bitmap;
			}
		}
		void resize_plus_Click(object sender, RoutedEventArgs e)
		{
			if (zoomCounter < 5) {
				/*rigthDown.X = rigthDown.X + 5 * rigthDown.X / 100;
				rigthDown.Y = rigthDown.Y + 5 * rigthDown.Y / 100;
				leftUp.X = leftUp.X + 5 * leftUp.X / 100;
				leftUp.Y = leftUp.Y + 5 * leftUp.Y / 100;*/
				
				BitmapImage newSizeImage = (BitmapImage)(new TransformedBitmap(originalBitmap, new ScaleTransform( (bitmap.Width + 5 * (bitmap.Width / 100)) / bitmap.PixelWidth,  (bitmap.Height + 5 * (bitmap.Height / 100)) / bitmap.PixelHeight)).Source);
				
				bitmap = PhotoEditHelper.AdjustImage(newSizeImage ,  brightnessTrack.Value, contrastTrack.Value, redColorTrack.Value, greenColorTrack.Value,
					blueColorTrack.Value);
				mainPictureBox.Width = bitmap.Width;
				mainPictureBox.Height = bitmap.Height;
				mainPictureBox.Source = bitmap;
				
				/*Graphics g = Graphics.FromImage(this.mainPictureBox.Image);
				g.DrawRectangle(new Pen(Color.Black), leftUp.X, leftUp.Y, rigthDown.X - leftUp.X, rigthDown.Y - leftUp.Y);*/
				
				zoomCounter++;
			}
		}
		void resize_minus_Click(object sender, RoutedEventArgs e)
		{
			throw new NotImplementedException();
		}
	}
}
