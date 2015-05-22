using System;
using System.Drawing;
using System.Drawing.Imaging;
using System.IO;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace PhotoEditor
{
	public static class PhotoEditHelper
	{
		private static BitmapImage ConvertBitmapToBitmapImage(Bitmap bitmap)
		{
			MemoryStream stream = new MemoryStream();
			bitmap.Save(stream, ImageFormat.Png);
			stream.Position = 0;
			
			BitmapImage result = new BitmapImage();
			result.BeginInit();
			result.CacheOption = BitmapCacheOption.OnLoad;
			result.StreamSource = stream;
			result.EndInit();
			result.Freeze();
			
			return result;
		}
		
		private static Bitmap ConvertBitmapImageToBitmap(BitmapImage bitmapImage)
		{
			using (var outStream = new MemoryStream()) {
				BitmapEncoder enc = new PngBitmapEncoder();
				enc.Frames.Add(BitmapFrame.Create(bitmapImage));
				enc.Save(outStream);
				var bitmap = new Bitmap(outStream);
				return new Bitmap(bitmap);
			}
		}
		
		public static BitmapImage AdjustImage(BitmapImage sourceBitmap, double brightness, double contrast, double redColor, double greenColor, double blueColor, double startX, double startY, double h, double w)
		{
			Bitmap bitmap = ConvertBitmapImageToBitmap(sourceBitmap);
			
			brightness /= 255;
			redColor = redColor / 255 + 1;
			greenColor = greenColor / 255 + 1;
			blueColor = blueColor / 255 + 1;
			float[][] matrix = {
				new[] { (float)redColor, 0, 0, 0, 0 },
				new[] { 0, (float)greenColor, 0, 0, 0 },
				new[] { 0, 0, (float)blueColor, 0, 0 },
				new[] { 0, 0, 0, 1f, 0 },
				new[] {
					(float)brightness,
					(float)brightness,
					(float)brightness,
					0,
					1
				}
			};


			ImageAttributes attributes = new ImageAttributes();
			attributes.SetColorMatrix(new ColorMatrix(matrix), ColorMatrixFlag.Default, ColorAdjustType.Bitmap);
			attributes.SetGamma((float)contrast, ColorAdjustType.Bitmap);

			var newImage = new Bitmap(bitmap.Width, bitmap.Height);
			using (var graphics = Graphics.FromImage(newImage)) {
				graphics.DrawImage(bitmap, 0, 0, newImage.Width, newImage.Height);
				graphics.DrawImage(bitmap, new System.Drawing.Rectangle((int)startX, (int)startY, (int)w, (int)h), (int)startX, (int)startY, (int)w, (int)h,
					GraphicsUnit.Pixel, attributes);
			}
			return ConvertBitmapToBitmapImage(newImage);
		}
		
		public static BitmapImage Resize(BitmapImage source, int width, int height)
		{
			return ConvertBitmapToBitmapImage(new Bitmap(ConvertBitmapImageToBitmap(source), width, height));
		}
	}
}
