package by.bsuir.verpav.misoi.clustering.steps

import java.awt.Color
import java.awt.image.BufferedImage
import javax.swing.JFrame

import by.bsuir.verpav.misoi.ui.table.ObjectPropertiesTable

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * Created by Pavel_Verkhovtsov on 10/5/16.
  */
object DetermineObjectsProperties extends ClusteringStep {
  override def perform(baseImage: BufferedImage): BufferedImage = {
    val connectedDomains = stepContext.asInstanceOf[mutable.Map[String,Any]].get("connectedDomains").get.asInstanceOf[mutable.Map[Int,ArrayBuffer[(Int, Int)]]]
    val regionsCount = connectedDomains.keys.size
    val objectsProperties = Array.ofDim[Any](regionsCount, 6)
    var currentRegion = 0
    for (domain <- connectedDomains.values) {
      currentRegion += 1
      val regionColor = (255.0 / regionsCount * currentRegion).toInt
      val color = new Color(regionColor, 255 - regionColor, Math.abs(regionColor - 126))
      val square = domain.size
      val perimeter = calculatePerimeter(domain)
      val density = perimeter * perimeter / square.toDouble
      val centerOfMass = (domain.map(_._1).sum / square, domain.map(_._2).sum / square)
      val m20 = domain.map((x) => Math.pow(x._1 - centerOfMass._1, 2)).sum
      val m02 = domain.map((x) => Math.pow(x._2 - centerOfMass._2, 2)).sum
      val m11 = domain.map((x) => (x._1 - centerOfMass._1) * (x._2 - centerOfMass._2)).sum

      val elongation =  (m20 + m02 + Math.sqrt(Math.pow(m20 - m02, 2) + 4 * Math.pow(m11, 2))) /
                        (m20 + m02 - Math.sqrt(Math.pow(m20 - m02, 2) + 4 * Math.pow(m11, 2)))

      objectsProperties(currentRegion-1) = Array(color, square, perimeter, density, m11, elongation)
    }
    new ObjectPropertiesTable(objectsProperties)
    baseImage
  }

  private def calculatePerimeter(points: ArrayBuffer[(Int, Int)]) = {
    var counter = 0
    for ((x, y) <- points) {
      if (!points.contains((x - 1, y)) || !points.contains((x, y - 1)) || !points.contains((x + 1, y)) || !points.contains((x, y + 1)))
        counter += 1
    }
    counter
  }

  override def requestParameters(frame: JFrame): Unit = {}
}
