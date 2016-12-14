package by.bsuir.verpav.misoi.clustering.steps

import java.awt.image.BufferedImage
import javax.swing.{JFrame, JOptionPane}

import by.bsuir.verpav.misoi.util.ImageUtils

/**
  * Created by Pavel_Verkhovtsov on 10/5/16.
  */

object FilterNoise extends ClusteringStep {
  override def perform(baseImage: BufferedImage): BufferedImage = {
    val coreSize = params.getOrElse("coreSize", 3)
    val min = ImageUtils.spatialImageTransformation(_: BufferedImage, coreSize,
      (x: Array[(Int, Int, Int)]) => x.minBy((ImageUtils.pointBrightness _).tupled))
    val max = ImageUtils.spatialImageTransformation(_: BufferedImage, coreSize,
      (x: Array[(Int, Int, Int)]) => x.maxBy((ImageUtils.pointBrightness _).tupled))
    min.andThen(max)(baseImage)
  }

  override def requestParameters(frame: JFrame): Unit = {
    val coreSize = JOptionPane.showInputDialog(frame, "Please input core size for 3 to 17: ")
    params.put("coreSize", coreSize.toInt)
  }
}
