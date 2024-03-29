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
    val connectedDomains = stepContext.get("connectedDomains").get.asInstanceOf[mutable.Map[Color,ArrayBuffer[(Int, Int)]]]
    val regionsCount = connectedDomains.keys.size
    val objectsProperties = new Array[List[Any]](regionsCount)
    var regionCounter = 0
    for ((color, domain) <- connectedDomains) {
      val square : Double = domain.size
      val perimeter : Double = calculatePerimeter(domain)
      val density : Double = perimeter * perimeter / square.toDouble
      val centerOfMass: (Double, Double) = (domain.map(_._1).sum / square, domain.map(_._2).sum / square)
      val m20: Double = domain.map((x) => Math.pow(x._1 - centerOfMass._1, 2)).sum
      val m02: Double = domain.map((x) => Math.pow(x._2 - centerOfMass._2, 2)).sum
      val m11: Double = domain.map((x) => (x._1 - centerOfMass._1) * (x._2 - centerOfMass._2)).sum

      val m22: Double = domain.map((x) => Math.pow(x._1 - centerOfMass._1, 2) * Math.pow(x._2 - centerOfMass._2, 2)).sum
      val elongation: Double =  (m20 + m02 + Math.sqrt(Math.pow(m20 - m02, 2) + 4 * Math.pow(m11, 2))) /
                                (m20 + m02 - Math.sqrt(Math.pow(m20 - m02, 2) + 4 * Math.pow(m11, 2)))

      objectsProperties(regionCounter)=List(color, square, perimeter, density, m22, elongation)
      regionCounter+=1
    }
    new ObjectPropertiesTable(objectsProperties.clone())
    stepContext.put("objectsProperties", objectsProperties)
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
