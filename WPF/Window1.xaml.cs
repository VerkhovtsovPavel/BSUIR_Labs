/*
 * Created by SharpDevelop.
 * User: Администратор
 * Date: 25.04.2015
 * Time: 12:07
 */
using System;
using System.IO;
using System.Runtime.Remoting.Channels;
using System.Windows;
using System.Windows.Controls;
using System.Drawing;
using System.Windows.Media.Imaging;
using Microsoft.Win32;
using PhotoEditor;


namespace WPF
{
	/// <summary>
	/// Interaction logic for Window1.xaml
	/// </summary>
	public partial class Window1 : Window
	{
		private BitmapImage bitmap;
		private BitmapImage originalBitmap;

		bool isFromFile;
		
		public Window1()
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
		void slider1_ValueChanged(object sender, RoutedPropertyChangedEventArgs<double> e)
		{
			throw new NotImplementedException();
		}
		
		private void changeImage(object sender, RoutedPropertyChangedEventArgs<double> e){
			if (isFromFile) {
				bitmap = PhotoEditHelper.AdjustImage(originalBitmap, brightnessTrack.Value, contrastTrack.Value, redColorTrack.Value, greenColorTrack.Value,
					blueColorTrack.Value);
				mainPictureBox.Source = bitmap;
			}
		}
	}
}
