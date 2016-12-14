package by.bsuir.verpav.misoi.harris.steps

import java.awt.image.BufferedImage
import javax.swing.JFrame

import by.bsuir.verpav.misoi.pipeline.PipelineStep
import by.bsuir.verpav.misoi.util.ImageUtils

/**
  * Created by Pavel_Verkhovtsov on 10/18/16.
  */
object GrayScaleImage extends PipelineStep {

  override def perform(baseImage: BufferedImage): BufferedImage = {
    ImageUtils.binaryImageTransformation(baseImage,
      (r: Int, g: Int, b: Int) => true,
      (r: Int, g: Int, b: Int) => {val I = ImageUtils.pointBrightness(r, g, b); Array(I, I, I)},
      (r: Int, g: Int, b: Int) => Array(0, 0, 0))
  }

  override def requestParameters(frame: JFrame): Unit = {

  }
}
