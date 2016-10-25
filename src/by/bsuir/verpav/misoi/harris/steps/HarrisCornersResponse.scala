package by.bsuir.verpav.misoi.harris.steps

import java.awt.image.BufferedImage
import javax.swing.JFrame

import by.bsuir.verpav.misoi.pipeline.PipelineStep

/**
  * Created by Pavel_Verkhovtsov on 10/19/16.
  */
object HarrisCornersResponse extends PipelineStep {

  val baseThreshold = 10000
  val baseK = 0.12

  override def perform(baseImage: BufferedImage): BufferedImage = {
    val threshold = params.getOrElse("Threshold", baseThreshold)
    val k = params.getOrElse("K", baseK).asInstanceOf[Double]

    val differences = stepContext.get("difference").get.asInstanceOf[Array[Array[(Int, Int, Int)]]]

    val width = differences.length
    val height = differences(0).length

    val hcr = Array.ofDim[Double](width, height)

    for (x <- 0 until width;
         y <- 0 until height) {
      val A = differences(x)(y)._1
      val B = differences(x)(y)._2
      val C = differences(x)(y)._3
      val M = (A * B - (C * C)) - (k * ((A + B) * (A + B)))
      if (M > threshold)
        hcr(x)(y) = M
      else
        hcr(x)(y) = 0
    }

    stepContext.put("harrisResponse", hcr)
    baseImage
  }

  override def requestParameters(frame: JFrame): Unit = ???
}
