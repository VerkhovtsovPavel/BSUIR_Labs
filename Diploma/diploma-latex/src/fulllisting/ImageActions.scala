package by.verkpavel.grafolnet.parser

import java.awt.image.{BufferedImage, WritableRaster}
import java.io.File
import javax.imageio.ImageIO

import scala.collection.mutable.ArrayBuffer
import java.awt.Image
import java.awt.image.BufferedImage

import scala.collection.mutable

object ImageActions {

  def minMaxFilter(baseImage: BufferedImage): BufferedImage = {
    val coreSize = 3
    val min = ImageUtils.spatialImageTransformation(_: BufferedImage, coreSize,
      (x: Array[(Int, Int, Int)]) => x.minBy((ImageUtils.pointBrightness _).tupled))
    val max = ImageUtils.spatialImageTransformation(_: BufferedImage, coreSize,
      (x: Array[(Int, Int, Int)]) => x.maxBy((ImageUtils.pointBrightness _).tupled))
    min.andThen(max)(baseImage)
  }

  def middleBrihtness(baseImage: BufferedImage): Double = {
    val pixelsBrihtness = ImageUtils.extractColors(baseImage).map((ImageUtils.pointBrightness _).tupled)
    pixelsBrihtness.sum / pixelsBrihtness.size
  }

  def frequence(baseImage: BufferedImage): Double = {
    val pixelsBrihtness = ImageUtils.extractColors(baseImage).map((ImageUtils.pointBrightness _).tupled).partition(_ == 0)
    pixelsBrihtness._1.size.toDouble / pixelsBrihtness._2.size
  }

  def symbolsAngel(symbols: mutable.Buffer[BufferedImage]): Double = {
    val angels = symbols.map(baseImage => {
      val pixelsBrihtness: Seq[Int] = ImageUtils.extractColors(baseImage).map((ImageUtils.pointBrightness _).tupled)
      val rigthTop: Int = pixelsBrihtness.takeWhile(_ == 0).size
      val leftBottom = pixelsBrihtness.size - pixelsBrihtness.reverse.takeWhile(_ == 0).size
      val (rigthTop_x, rigthTop_y) = (rigthTop / baseImage.getWidth, rigthTop % (baseImage.getWidth + 1))
      val (leftBottom_x, leftBottom_y) = (leftBottom / baseImage.getWidth, leftBottom % (baseImage.getWidth + 1))
      Math.atan((leftBottom_y - rigthTop_y) / (leftBottom_x - rigthTop_x))
    })
    angels.sum / angels.size
  }

  def imageBinarization(baseImage: BufferedImage, threshold: Int): BufferedImage = {
    ImageUtils.binaryImageTransformation(
      baseImage,
      (r: Int, g: Int, b: Int) => ImageUtils.pointBrightness(r, g, b) > threshold,
      (r: Int, g: Int, b: Int) => Array(255, 255, 255),
      (r: Int, g: Int, b: Int) => Array(0, 0, 0)
    )
  }
}

