package by.bsuir.verpav.misoi.clustering.steps

import java.awt.Color
import java.awt.image.BufferedImage
import javax.swing.{JFrame, JOptionPane}

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * Created by Pavel_Verkhovtsov on 10/5/16.
  */
object HighlightConnectedDomains extends ClusteringStep {
  override def perform(baseImage: BufferedImage): BufferedImage = {
    val noiseLimit = params.getOrElse("noiseLimit", 10)
    val regions = mutable.Map[Int, ArrayBuffer[(Int, Int)]]()
    val cm = baseImage.getColorModel
    val isAlphaPremultiplied = cm.isAlphaPremultiplied
    val raster = baseImage.copyData(null)
    for (x <- 0 until baseImage.getWidth; y <- 0 until baseImage.getHeight) {
      val rgb = raster.getPixel(x, y, null.asInstanceOf[Array[Int]])
      if (rgb(0) == 255) {
        val firstRegion = regions.find((e: (Int, ArrayBuffer[(Int, Int)])) => e._2.contains((x - 1, y)))
        val secondRegion = regions.find((e: (Int, ArrayBuffer[(Int, Int)])) => e._2.contains((x, y - 1)))

        if (firstRegion.isEmpty && secondRegion.isEmpty) {
          regions.put(if (regions.keys.isEmpty) 0 else regions.keys.max + 1, ArrayBuffer[(Int, Int)]((x, y)))
        }
        else if (firstRegion.isDefined && secondRegion.isEmpty) {
          firstRegion.get._2.append((x, y))
        }
        else if (firstRegion.isEmpty && secondRegion.isDefined) {
          secondRegion.get._2.append((x, y))
        }
        else if (firstRegion.isDefined && secondRegion.isDefined) {
          if (firstRegion != secondRegion) {
            firstRegion.get._2.appendAll(secondRegion.get._2)
            regions.remove(secondRegion.get._1)
          }
          firstRegion.get._2.append((x, y))
        }
      }
    }

    val (validRegions, noiseRegions) = regions.partition(_._2.size > noiseLimit)

    val regionsCount = validRegions.keys.size
    var currentRegion = 0
    val coloredRegions = mutable.Map[Color, ArrayBuffer[(Int, Int)]]()
    for (v <- validRegions.values) {
      currentRegion += 1
      val regionColor = (255.0 / regionsCount * currentRegion).toInt
      coloredRegions.put(new Color(regionColor, 255 - regionColor, Math.abs(regionColor - 126)), v)
      for ((x, y) <- v) {
        raster.setPixel(x, y, Array[Int](regionColor, 255 - regionColor, Math.abs(regionColor - 126)))
      }
    }

    for (v <- noiseRegions.values;
         (x, y) <- v) {
      raster.setPixel(x, y, Array[Int](0, 0, 0))
    }

    stepContext.put("connectedDomains", coloredRegions)
    new BufferedImage(cm, raster, isAlphaPremultiplied, null)
  }

  override def requestParameters(frame: JFrame): Unit = {
    val noiseLimit = JOptionPane.showInputDialog(frame, "Please input noise limit : ")
    params.put("noiseLimit", noiseLimit.toInt)
  }
}
