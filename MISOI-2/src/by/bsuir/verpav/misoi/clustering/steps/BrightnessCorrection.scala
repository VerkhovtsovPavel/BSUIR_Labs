package by.bsuir.verpav.misoi.clustering.steps

import java.awt.image.BufferedImage
import javax.swing.{JFrame, JOptionPane}

import by.bsuir.verpav.misoi.util.ImageUtils

/**
  * Created by Pavel_Verkhovtsov on 10/5/16.
  */
object BrightnessCorrection extends ClusteringStep {

  override def perform(baseImage: BufferedImage): BufferedImage = {
    val isUseCorrection = params.getOrElse("isUseCorrection", 1)
    if (isUseCorrection == 1) {
      val colors = ImageUtils.extractColors(baseImage)
      val red = colors.map(_._1)
      val redMin = red.min
      val redMax = red.max
      val green = colors.map(_._2)
      val greenMin = green.min
      val greenMax = green.max
      val blue = colors.map(_._3)
      val blueMin = blue.min
      val blueMax = blue.max

      def newPixelValue(pixel: Int, max: Int, min: Int) = ((pixel - min).toDouble / (max - min).toDouble * 255).toInt

      ImageUtils.binaryImageTransformation(baseImage,
        (r: Int, g: Int, b: Int) => true,
        (r: Int, g: Int, b: Int) => Array(newPixelValue(r, redMax, redMin), newPixelValue(g, greenMax, greenMin), newPixelValue(b, blueMax, blueMin)),
        (r: Int, g: Int, b: Int) => Array(0, 0, 0))
    }
    else
      baseImage
  }

  override def requestParameters(frame: JFrame): Unit = {
    val isUseCorrection = JOptionPane.showInputDialog(frame, "Use correction 0 or 1: ")
    params.put("isUseCorrection", isUseCorrection.toInt)
  }
}
