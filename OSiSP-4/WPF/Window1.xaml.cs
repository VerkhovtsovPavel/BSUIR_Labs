using System;
using System.IO;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;
using Microsoft.Win32;
using PhotoEditor;

namespace WPF
{
	public partial class MainWindow : Window
	{
		private BitmapImage bitmap;
		private BitmapImage originalBitmap;
		
		private int zoomCounter;
		private bool isImageOpen;
		
			
		private Point startPoint;
		private Rectangle rect;
		
		
		private double x;
		private double y;
		private double w;
		private double h;
		
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
					
				
				rect = new Rectangle {};
				rect.Stroke = Brushes.LightBlue;
				selectAll();
				
				mainPictureBox.Source = bitmap;
				isImageOpen = true;
				zoomCounter = 0;
			}
		}
		
		private void selectAll()
		{
			x = 0;
			y = 0;
			w = bitmap.Width;
			h = bitmap.Height;
			
			rect.Width = w;
			rect.Height = h;

			Canvas.SetLeft(rect, x);
			Canvas.SetTop(rect, y);
			rect.StrokeThickness = 0;
		}
		
		void saveFile_Click(object sender, RoutedEventArgs e)
		{
			if (isImageOpen)
			{
				SaveFileDialog saveFileDialog = new SaveFileDialog();
				if (saveFileDialog.ShowDialog() == true) {
					JpegBitmapEncoder jpegBitmapEncoder = new JpegBitmapEncoder();
					jpegBitmapEncoder.QualityLevel = 100;
					jpegBitmapEncoder.Frames.Add(BitmapFrame.Create(bitmap));
					string fileName = saveFileDialog.FileName;
					
					if (File.Exists(fileName)) {
						File.Delete(fileName);
					}
					
					FileStream fileStream = new FileStream(fileName, FileMode.CreateNew);
					jpegBitmapEncoder.Save(fileStream);
					fileStream.Close();
				}
			}
		}
		
		private void changeImage(object sender, RoutedPropertyChangedEventArgs<double> e)
		{
			if (isImageOpen)
			{
				bitmap = PhotoEditHelper.AdjustImage(PhotoEditHelper.Resize(originalBitmap, (int)mainPictureBox.Width, (int)mainPictureBox.Height)  , brightnessTrack.Value, contrastTrack.Value, redColorTrack.Value, greenColorTrack.Value,
					blueColorTrack.Value, x, y, h, w);
				mainPictureBox.Source = bitmap;
			}
		}
		void resize_plus_Click(object sender, RoutedEventArgs e)
		{
			if (zoomCounter < 5 && isImageOpen)
			{
				w = rect.Width + 5 * rect.Width / 100;
				rect.Width = w;
				h = rect.Height + 5 * rect.Height / 100;
				rect.Height = h;
				x = x + 5 * x / 100;
				Canvas.SetLeft(rect, x);
				y = y + 5* y / 100;
				Canvas.SetTop(rect, y);

				BitmapImage newSizeImage = PhotoEditHelper.Resize(originalBitmap, (int)(bitmap.Width + 5 * (bitmap.Width / 100)), (int)(bitmap.Height + 5 * (bitmap.Height / 100)));
				
				bitmap = PhotoEditHelper.AdjustImage(newSizeImage, brightnessTrack.Value, contrastTrack.Value, redColorTrack.Value, greenColorTrack.Value,
					blueColorTrack.Value, x, y, h, w);
				mainPictureBox.Width = bitmap.Width;
				mainPictureBox.Height = bitmap.Height;
				mainPictureBox.Source = bitmap;
				
				zoomCounter++;
			}
		}
		void resize_minus_Click(object sender, RoutedEventArgs e)
		{
			if (zoomCounter > -5 && isImageOpen) {
				w = rect.Width - 5 * rect.Width / 100;
				rect.Width = w;
				h = rect.Height - 5 * rect.Height / 100;
				rect.Height = h;
				x = x - 5 * x / 100;
				Canvas.SetLeft(rect, x);
				y = y - 5* y / 100;
				Canvas.SetTop(rect, y);
				
				BitmapImage newSizeImage = PhotoEditHelper.Resize(originalBitmap, (int)(bitmap.Width - 5 * (bitmap.Width / 100)), (int)(bitmap.Height - 5 * (bitmap.Height / 100)));
				
				bitmap = PhotoEditHelper.AdjustImage(newSizeImage, brightnessTrack.Value, contrastTrack.Value, redColorTrack.Value, greenColorTrack.Value,
					blueColorTrack.Value, x, y, h, w);
				mainPictureBox.Width = bitmap.Width;
				mainPictureBox.Height = bitmap.Height;
				mainPictureBox.Source = bitmap;
				
				zoomCounter--;
			}
		}

		void mainPictureBox_MouseMove(object sender, System.Windows.Input.MouseEventArgs e)
		{
			if (e.LeftButton == MouseButtonState.Released || rect == null)
				return;

			var pos = e.GetPosition(canvas);

			x = Math.Min(pos.X, startPoint.X);
			y = Math.Min(pos.Y, startPoint.Y);

			w = Math.Max(pos.X, startPoint.X) - x;
			h = Math.Max(pos.Y, startPoint.Y) - y;

			rect.Width = w;
			rect.Height = h;

			Canvas.SetLeft(rect, x);
			Canvas.SetTop(rect, y);
			
			changeImage(sender , null);
		}

		void mainPictureBox_MouseLeftButtonDown(object sender, MouseButtonEventArgs e)
		{
			canvas.Children.Remove(rect);
			startPoint = e.GetPosition(canvas);

			rect = new Rectangle
			{
				Stroke = Brushes.LightBlue,
				StrokeThickness = 2
			};
			Canvas.SetLeft(rect, startPoint.X);
			Canvas.SetTop(rect, startPoint.Y);
			canvas.Children.Add(rect);
		}

		void window_KeyDown(object sender, KeyEventArgs e)
		{
			if(e.Key == Key.D && isImageOpen)
			{
				selectAll();
				changeImage(sender, null);
			}
		}
	}
}
