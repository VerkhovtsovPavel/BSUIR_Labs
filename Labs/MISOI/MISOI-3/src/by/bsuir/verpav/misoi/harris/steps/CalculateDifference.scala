package by.bsuir.verpav.misoi.harris.steps

import java.awt.image.BufferedImage
import javax.swing.JFrame

import by.bsuir.verpav.misoi.pipeline.PipelineStep
import by.bsuir.verpav.misoi.util.ImageUtils

/**
  * Created by Pavel_Verkhovtsov on 10/19/16.
  */
object CalculateDifference extends PipelineStep{

  override def perform(baseImage: BufferedImage): BufferedImage = {

    val width = baseImage.getWidth
    val height = baseImage.getHeight

    val raster = baseImage.getRaster

    val offset = 1
    val coreSize = 3

    val diff: Array[Array[(Double,Double,Double)]] = Array.ofDim[(Double,Double,Double)](width - (offset * 2), height - (offset * 2))

    for (x <- offset until width  - offset;
         y <- offset until height - offset)
      {
        val neib = ImageUtils.getNeighborhood(raster, x, y, coreSize, false).map(_._1)
        val diffY = (neib(0) + neib(1) + neib(2) - (neib(5) + neib(6) + neib(7))) * 0.166666667
        val diffX = (neib(0) + neib(3) + neib(5) - (neib(2) + neib(4) + neib(7))) * 0.166666667

        diff(x - offset)(y - offset) = (diffX * diffX, diffY * diffY, diffY * diffX)
      }
    stepContext.put("difference", diff)
    baseImage
  }

  override def requestParameters(frame: JFrame): Unit = {}
}
