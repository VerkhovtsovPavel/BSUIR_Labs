package by.bsuir.verpav.misoi.clustering.steps

import java.awt.image.BufferedImage
import javax.swing.{JFrame, JOptionPane}

import by.bsuir.verpav.misoi.util.ImageUtils

/**
  * Created by Pavel_Verkhovtsov on 10/5/16.
  */
object ImageBinarization extends ClusteringStep {

  override def perform(baseImage: BufferedImage): BufferedImage = {
    val threshold = params.getOrElse("Threshold", 200)
    ImageUtils.binaryImageTransformation(baseImage,
      (r: Int, g: Int, b: Int) => ImageUtils.pointBrightness(r, g, b) > threshold,
      (r: Int, g: Int, b: Int) => Array(255, 255, 255),
      (r: Int, g: Int, b: Int) => Array(0, 0, 0))
  }

  override def requestParameters(frame: JFrame): Unit = {
    val threshold = JOptionPane.showInputDialog(frame, "Please input threshold for 0 to 255: ")
    params.put("Threshold", threshold.toInt)
  }
}
