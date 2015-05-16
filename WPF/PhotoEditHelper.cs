using System;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace PhotoEditor
{
	public static class PhotoEditHelper
	{
		public static BitmapImage AdjustImage(BitmapImage sourceBitmap, double brightness, double contrast, double redColor, double greenColor, double blueColor)
		{
			System.Drawing.Bitmap bitmap = new Bitmap();
			
			if (contrast < 1)
			{
				contrast = 1;
			}
			else if (contrast > 10)
			{
				contrast = 10;
			}
			
			CheckValue(ref brightness);
			CheckValue(ref redColor);
			CheckValue(ref greenColor);
			CheckValue(ref blueColor);
			brightness /= 255;
			redColor = redColor / 255 + 1;
			greenColor = greenColor / 255 + 1;
			blueColor = blueColor / 255 + 1;
			double[][] matrix = {
                new[] {redColor, 0, 0, 0, 0},
                new[] {0, greenColor, 0, 0, 0},
                new[] {0, 0, blueColor, 0, 0},
                new[] {0, 0, 0, (double)1, 0},
                new[] {brightness, brightness, brightness, 0, 1}
            };


			ImageAttributes attributes = new ImageAttributes();
            attributes.SetColorMatrix(new ColorMatrix(matrix), ColorMatrixFlag.Default, ColorAdjustType.Bitmap);
            attributes.SetGamma(contrast, ColorAdjustType.Bitmap);

            var newImage = new BitmapImage(sourceBitmap.Width, sourceBitmap.Height);
            using (graphics = Graphics.FromImage(newImage))
                graphics.DrawImage(sourceBitmap, new Rectangle(0, 0, newImage.Width, newImage.Height), 0, 0, newImage.Width,
                    newImage.Height,  GraphicsUnit.Pixel, attributes);
            return newImage;
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
