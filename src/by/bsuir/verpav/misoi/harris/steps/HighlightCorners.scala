package by.bsuir.verpav.misoi.harris.steps

import java.awt.image.BufferedImage
import javax.swing.JFrame

import by.bsuir.verpav.misoi.pipeline.PipelineStep

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Pavel_Verkhovtsov on 10/25/16.
  */
object HighlightCorners  extends PipelineStep {

  override def perform(baseImage: BufferedImage): BufferedImage = {
    val cm = baseImage.getColorModel
    val isAlphaPremultiplied = cm.isAlphaPremultiplied

    val cornersList = stepContext.get("corners").get.asInstanceOf[ArrayBuffer[(Int, Int)]]

    val raster = baseImage.copyData(null)
    val width = raster.getWidth
    val height = raster.getHeight

    for(x <- 0 to height;
        y <- 0 to width)
    {
      if(cornersList.contains((x,y))){
        raster.setPixel(x-1, y-1, Array[Int](255, 0, 0))
        raster.setPixel(x-1, y+1, Array[Int](255, 0, 0))
        raster.setPixel(x+1, y+1, Array[Int](255, 0, 0))
        raster.setPixel(x+1, y-1, Array[Int](255, 0, 0))
        raster.setPixel(x+1, y, Array[Int](255, 0, 0))
        raster.setPixel(x, y+1, Array[Int](255, 0, 0))
        raster.setPixel(x-1, y, Array[Int](255, 0, 0))
        raster.setPixel(x, y-1, Array[Int](255, 0, 0))
      }
    }
    new BufferedImage(cm, raster, isAlphaPremultiplied, null)
  }

  override def requestParameters(frame: JFrame): Unit = {

  }
}
