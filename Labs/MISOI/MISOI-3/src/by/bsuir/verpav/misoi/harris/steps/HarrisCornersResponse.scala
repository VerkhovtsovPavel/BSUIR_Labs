package by.bsuir.verpav.misoi.harris.steps

import java.awt.image.BufferedImage
import javax.swing.{JFrame, JOptionPane}

import by.bsuir.verpav.misoi.pipeline.PipelineStep

/**
  * Created by Pavel_Verkhovtsov on 10/19/16.
  */
object HarrisCornersResponse extends PipelineStep {

  val baseThreshold = 50

  override def perform(baseImage: BufferedImage): BufferedImage = {
    val threshold = params.getOrElse("Threshold", baseThreshold)

    val differences = stepContext.get("gaussDifference").get.asInstanceOf[Array[Array[(Double, Double, Double)]]]

    val width = differences.length
    val height = differences(0).length

    val hcr = Array.ofDim[Double](width, height)

    val d = differences.flatMap(_.map((x) => {val(a,b,c) = x;  (a * b - c * c) / (a + b + 1.4e-45)})).max // TODO DEbug remove
    println(d)

    for (x <- 0 until width;
         y <- 0 until height) {
      val A = differences(x)(y)._1
      val B = differences(x)(y)._2
      val C = differences(x)(y)._3
      val M = (A * B - C * C) / (A + B + 1.4e-45)
      if (M > threshold)
        hcr(x)(y) = M
      else
        hcr(x)(y) = 0
    }
    stepContext.put("harrisResponse", hcr)
    baseImage
  }

  override def requestParameters(frame: JFrame): Unit = {
    val threshold = JOptionPane.showInputDialog(frame, "Please input response threshold : ")
    params.put("Threshold", threshold.toInt)
  }
}
