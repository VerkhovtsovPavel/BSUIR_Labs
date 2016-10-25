package by.bsuir.verpav.misoi.harris.steps

import java.awt.image.{BufferedImage, Raster, WritableRaster}
import javax.swing.JFrame

import by.bsuir.verpav.misoi.pipeline.PipelineStep

import scala.collection.mutable.ArrayBuffer

/**
  * Created by verkpavel on 21.10.16.
  */
object NonMaxFilter extends PipelineStep{

  val baseCoreSize = 5

  override def perform(baseImage: BufferedImage): BufferedImage = {

    val cornersList = new ArrayBuffer[(Int, Int)]()
    val harrisResponse = stepContext.get("harrisResponse").get.asInstanceOf[Array[Array[Double]]]
    val coreSize = params.getOrElse("coreSize", baseCoreSize)

    val width = harrisResponse.length
    val height= harrisResponse(0).length

    for (x <- coreSize until width - coreSize;
         y <- coreSize until height - coreSize)
      {
        var isMax = true
        val currentValue = harrisResponse(x)(y)

        if(currentValue!=0) {
          for (i <- -coreSize to coreSize;
               j <- -coreSize to coreSize) {
            if (harrisResponse(x + i)(y + j) > currentValue) {
              isMax = false
            }
          }
          if (isMax) {
            cornersList.append((x, y))
          }
        }
      }

    stepContext.put("corners", cornersList)
    baseImage
  }


  override def requestParameters(frame: JFrame) = ???
}
