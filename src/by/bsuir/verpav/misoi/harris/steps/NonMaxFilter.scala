package by.bsuir.verpav.misoi.harris.steps

import java.awt.image.{WritableRaster, Raster, BufferedImage}
import javax.swing.JFrame
import by.bsuir.verpav.misoi.pipeline.PipelineStep
import scala.collection.mutable.ArrayBuffer

/**
  * Created by verkpavel on 21.10.16.
  */
object NonMaxFilter extends PipelineStep{

  val baseCoreSize = 1

  override def perform(baseImage: BufferedImage): BufferedImage = {

    val cornersList = new ArrayBuffer[(Int, Int)]()

    val harrisResponse = stepContext.get("harrisResponse").asInstanceOf[Array[Array[Double]]]

    val coreSize = params.getOrElse("coreSize", baseCoreSize).asInstanceOf[Int]

    val raster = baseImage.copyData(null)
    val width = raster.getWidth
    val height= raster.getHeight

    for (y <- coreSize to height - coreSize;
         x <- coreSize to width - coreSize)
      {
        var isMax = true
        val currentValue = harrisResponse(y)(x)

        for (i <- -coreSize to coreSize;
             j <- -coreSize to coreSize)
        {
           if (harrisResponse(y + i)(x + j) > currentValue)
           {
             isMax = false
           }
        }

        if (isMax)
        {
          cornersList.append((x, y))
        }
      }

    cornersList

    val cm = baseImage.getColorModel
    val isAlphaPremultiplied = cm.isAlphaPremultiplied
    val raster : WritableRaster = baseImage.copyData(null)

    for(x <- 0 to height;
        y <- 0 to width)
    {
      if(cornersList.contains((x,y))){
        raster
      }
    }

    baseImage
  }


  override def requestParameters(frame: JFrame): Unit = ???
}
