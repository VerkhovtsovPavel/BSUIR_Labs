using System;
using System.Drawing;
using System.Drawing.Imaging;

namespace PhotoEditor
{
	public static class PhotoEditHelper
	{
		private static Graphics graphics;
		
		public static Bitmap AdjustImage(Bitmap sourceBitmap, float brightness, float contrast, float redColor, float greenColor, float blueColor, Point selectionLT, Point selectionRD)
		{
			brightness /= 255;
			redColor = redColor / 255 + 1;
			greenColor = greenColor / 255 + 1;
			blueColor = blueColor / 255 + 1;
			
			float[][] matrix = {
				new[] { redColor, 0, 0, 0, 0 },
				new[] { 0, greenColor, 0, 0, 0 },
				new[] { 0, 0, blueColor, 0, 0 },
				new[] { 0, 0, 0, 1f, 0 },
				new[] { brightness, brightness, brightness, 0, 1 }
			};

			ImageAttributes attributes = new ImageAttributes();
			attributes.SetColorMatrix(new ColorMatrix(matrix), ColorMatrixFlag.Default, ColorAdjustType.Bitmap);
			attributes.SetGamma(contrast, ColorAdjustType.Bitmap);

			var newImage = new Bitmap(sourceBitmap.Width, sourceBitmap.Height);
			using (graphics = Graphics.FromImage(newImage)) {
				graphics.DrawImage(sourceBitmap, 0, 0, newImage.Width, newImage.Height);
				graphics.DrawImage(sourceBitmap, new Rectangle(selectionLT.X, selectionLT.Y, selectionRD.X - selectionLT.X, selectionRD.Y - selectionLT.Y), selectionLT.X, selectionLT.Y, selectionRD.X - selectionLT.X, selectionRD.Y - selectionLT.Y, GraphicsUnit.Pixel, attributes);
			}
            	
			return newImage;
		}
	}
}
