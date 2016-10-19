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

    for (x <- coreSize / 2 to width - (coreSize / 2);
         y <- coreSize / 2 to height - (coreSize / 2))
      {
        val neig = ImageUtils.getNeighborhood(raster, x, y, coreSize, false).map(_._1)
        val valX = neig(0) + neig(1) + neig(2) - (neig(5) + neig(6) + neig(7))
        val valY = neig(0) + neig(3) + neig(5) - (neig(2) + neig(4) + neig(7))

        diff(x)(y) = (valX * valX, valY * valY, valY * valY)
      }
    stepContext.put("difference", diff)
    baseImage //TODO Maybe rework
  }

  override def requestParameters(frame: JFrame): Unit = ???
}
