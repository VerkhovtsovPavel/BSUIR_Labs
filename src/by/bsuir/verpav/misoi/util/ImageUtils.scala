package by.bsuir.verpav.misoi.util

import java.awt.image.{BufferedImage, WritableRaster}
import java.io.File
import javax.imageio.ImageIO

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Pavel_Verkhovtsov on 9/16/16.
  */
object ImageUtils {

  def binaryImageTransformation(baseImage: BufferedImage, predicate: (Int, Int, Int) => Boolean,
                                trueTransformation : (Int, Int, Int) => Array[Int],
                                falseTransformation : (Int, Int, Int) => Array[Int]) = {
    val raster = baseImage.getRaster
    for(x <- 0 until baseImage.getWidth; y <- 0 until baseImage.getHeight()) {
      val rgb = raster.getPixel(x, y, null.asInstanceOf[Array[Int]])
      if(predicate(rgb(0), rgb(1), rgb(2)))
        raster.setPixel(x, y, trueTransformation(rgb(0), rgb(1), rgb(2)))
      else{
        raster.setPixel(x, y, falseTransformation(rgb(0), rgb(1), rgb(2)))
      }
    }
    baseImage
  }

  def getNeighborhood(raster: WritableRaster, x: Int, y: Int, coreSize: Int, includePoint: Boolean) : Array[(Int, Int, Int)] = {
    val delta = (coreSize-1)/2
    val buffer = ArrayBuffer[(Int, Int, Int)]()

    for(xx <- (x - delta) to (x + delta); yy <- (y - delta) to (y + delta)){
      try {
        if(xx!=x && yy!=y) {
          val rgb = raster.getPixel(xx, yy, null.asInstanceOf[Array[Int]])
          buffer += ((rgb(0), rgb(1), rgb(2)))
        }
      } catch { case iob : ArrayIndexOutOfBoundsException => }
    }
    if(includePoint){
      val rgb = raster.getPixel(x, y, null.asInstanceOf[Array[Int]])
      buffer += ((rgb(0), rgb(1), rgb(2)))
    }
    buffer.toArray
  }

  def spatialImageTransformation(baseImage: BufferedImage, coreSize : Int, newPixelValue : Array[(Int, Int, Int)] => (Int, Int, Int)) = {
    val cm = baseImage.getColorModel
    val isAlphaPremultiplied = cm.isAlphaPremultiplied
    val newRaster = baseImage.copyData(null)
    val raster = baseImage.getRaster
    for(x <- 0 until baseImage.getWidth; y <- 0 until baseImage.getHeight()) {
      val neighborhood  = getNeighborhood(raster, x, y, coreSize, false)
      val newValue = newPixelValue(neighborhood)
      newRaster.setPixel(x, y, Array[Int](newValue._1, newValue._2, newValue._3))
    }
    new BufferedImage(cm, newRaster, isAlphaPremultiplied, null)
  }

  def extractColors (image : BufferedImage): Seq[(Int, Int, Int)] = {
    val raster = image.getRaster
    for(x <- 0 until image.getWidth; y <- 0 until image.getHeight()) yield {
      val rgb = raster.getPixel(x, y, null.asInstanceOf[Array[Int]])
      (rgb(0), rgb(1), rgb(2))
    }
  }

  def extractColors (imageName : String) : Seq[(Int, Int, Int)] = {
    val image = ImageIO.read(new File(imageName))
    extractColors(image)
  }

  def pointBrightness(red : Int, green : Int, blue : Int) = (0.3 * red + 0.59 * green + 0.11 * blue).toInt
}
