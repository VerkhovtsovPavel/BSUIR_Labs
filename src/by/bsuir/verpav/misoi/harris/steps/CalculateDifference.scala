package by.bsuir.verpav.misoi.harris.steps

import java.awt.image.BufferedImage
import javax.swing.JFrame

import by.bsuir.verpav.misoi.pipeline.PipelineStep
import by.bsuir.verpav.misoi.util.ImageUtils

/**
  * Created by Pavel_Verkhovtsov on 10/19/16.
  */
object CalculateDifference extends PipelineStep{

  val coreSize = 3

  override def perform(baseImage: BufferedImage): BufferedImage = {

    val raster = baseImage.copyData(null)
    val width = raster.getWidth
    val height= raster.getHeight

    val diff: Array[Array[(Int,Int,Int)]] = Array.ofDim[(Int,Int,Int)](width, height)

    for (x <- coreSize / 2 to width  - (coreSize / 2);
         y <- coreSize / 2 to height - (coreSize / 2))
      {
        val neib = ImageUtils.getNeighborhood(raster, x, y, coreSize, false).map(_._1)
        val diffX = neib(0) + neib(1) + neib(2) - (neib(5) + neib(6) + neib(7))
        val diffY = neib(0) + neib(3) + neib(5) - (neib(2) + neib(4) + neib(7))

        diff(x)(y) = (diffX * diffX, diffY * diffY, diffY * diffY)
      }
    stepContext.put("difference", diff)
    baseImage //TODO Maybe rework
  }

  override def requestParameters(frame: JFrame): Unit = ???
}
