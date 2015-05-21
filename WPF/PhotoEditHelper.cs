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
		private static BitmapImage ConvertBitmapToBitmapImage(Bitmap bitmap){
			MemoryStream stream = new MemoryStream();
			bitmap.Save(stream, ImageFormat.Jpeg);
			stream.Position = 0;
			
			BitmapImage result = new BitmapImage();
			result.BeginInit();
			result.CacheOption = BitmapCacheOption.OnLoad;
			result.StreamSource = stream;
			result.EndInit();
			result.Freeze();
			
			return result;
		}
		
		private static Bitmap ConvertBitmapImageToBitmap(BitmapImage bitmapImage){
			using(var outStream = new MemoryStream()){
				BitmapEncoder enc = new JpegBitmapEncoder();
				enc.Frames.Add(BitmapFrame.Create(bitmapImage));
				enc.Save(outStream);
				var bitmap = new Bitmap(outStream);
				return new Bitmap(bitmap);
			}
			
		}
		
		public static BitmapImage AdjustImage(BitmapImage sourceBitmap, double brightness, double contrast, double redColor, double greenColor, double blueColor)
		{
			Bitmap bitmap = ConvertBitmapImageToBitmap(sourceBitmap);
			
			brightness /= 255;
			redColor = redColor / 255 + 1;
			greenColor = greenColor / 255 + 1;
			blueColor = blueColor / 255 + 1;
			float[][] matrix = {
				new[] {(float)redColor, 0, 0, 0, 0},
				new[] {0, (float)greenColor, 0, 0, 0},
				new[] {0, 0, (float)blueColor, 0, 0},
                new[] {0, 0, 0, 1f, 0},
                new[] {(float)brightness, (float)brightness, (float)brightness, 0, 1}
            };


			ImageAttributes attributes = new ImageAttributes();
            attributes.SetColorMatrix(new ColorMatrix(matrix), ColorMatrixFlag.Default, ColorAdjustType.Bitmap);
            attributes.SetGamma((float)contrast, ColorAdjustType.Bitmap);

            var newImage = new Bitmap(bitmap.Width, bitmap.Height);
            using (var graphics = Graphics.FromImage(newImage))
                graphics.DrawImage(bitmap, new System.Drawing.Rectangle(0, 0, newImage.Width, newImage.Height), 0, 0, newImage.Width,
                    newImage.Height,  GraphicsUnit.Pixel, attributes);
            return ConvertBitmapToBitmapImage(newImage);
        }	
		
		private static void CheckValue(ref double value)
		{
	    	if (value > 255) 
	    	    value = 255;
	        else if (value < -255) 
	    	    value = 255;
		}
	}
}
