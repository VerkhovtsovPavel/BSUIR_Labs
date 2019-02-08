package by.bsuir.verpav.misoi.clustering.steps
import java.awt.Color
import java.awt.image.BufferedImage
import javax.swing.{JFrame, JOptionPane}

import scala.annotation.tailrec
import scala.collection.immutable.IndexedSeq
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

object ClusteringObjects extends  ClusteringStep{

  override def perform(baseImage: BufferedImage): BufferedImage = {
    val connectedDomains = stepContext.get("connectedDomains").get.asInstanceOf[mutable.Map[Color,ArrayBuffer[(Int, Int)]]]
    val objectsProperties = stepContext.get("objectsProperties").get.asInstanceOf[Array[List[Any]]]
    normalizeProperties(objectsProperties)
    val classCount = if (params.getOrElse("classCount", 2) < objectsProperties.length) params.getOrElse("classCount", 2) else objectsProperties.length-1
    val classBound = mutable.Map[List[Any], Int]()
    val classCores = new Array[List[Any]](classCount)

    val shuffledProperties = Random.shuffle(objectsProperties.toList)
    for(i <- 0 until classCount){
      classCores(i) = shuffledProperties(i)
    }

    clusterazeObjects(objectsProperties, classCount, classBound, classCores)

    val cm = baseImage.getColorModel
    val isAlphaPremultiplied = cm.isAlphaPremultiplied
    val raster = baseImage.copyData(null)
    for (objectProperties <- objectsProperties) {
      val objectClass = classBound(objectProperties) + 1
      val regionColor = (255.0 / objectClass).toInt
      val points: ArrayBuffer[(Int, Int)] = connectedDomains(objectProperties.head.asInstanceOf[Color])
      for ((x, y) <- points) {
        raster.setPixel(x, y, Array[Int](regionColor, 255 - regionColor, Math.abs(regionColor - 126)))
      }
    }

    new BufferedImage(cm, raster, isAlphaPremultiplied, null)
  }

  @tailrec
  private def clusterazeObjects(objectsProperties: Array[List[Any]], classCount: Int, classBound: mutable.Map[List[Any], Int], classCores: Array[List[Any]]) : Unit = {
    classBound.clear()
    var newCoreFound = false
    for(objectProperties <- objectsProperties){
      val classNumber = classifyObject(objectProperties, classCores)
      classBound.put(objectProperties, classNumber)
    }

    for(i <- 0 until classCount){
      val newCoreCandidates = classBound.filter(_._2 == i).keys
      val newCore = findNewCore(newCoreCandidates)
      if(newCore!=classCores(i)) {
        newCoreFound = true
        classCores(i)=newCore
      }
    }

    if(newCoreFound){
      clusterazeObjects(objectsProperties, classCount, classBound, classCores)
    }
  }

  private def findNewCore(newCoreCandidates : Iterable[List[Any]]) : List[Any] ={
    val index = newCoreCandidates.toArray.map(candidate => medianDistance(candidate, newCoreCandidates)).zipWithIndex.minBy(_._1)._2
    newCoreCandidates.toList(index)
  }

  private def medianDistance(candidate : List[Any], newCoreCandidates : Iterable[List[Any]]) = {
      newCoreCandidates.map(otherCandidate => distance(candidate.tail, otherCandidate.tail)).toArray.sorted.apply(newCoreCandidates.size/2)
  }

  private def classifyObject(objectProperties : List[Any] , classCores : Array[List[Any]]) : Int ={
      classCores.map((core) => distance(objectProperties.tail, core.tail)).zipWithIndex.minBy(_._1)._2
  }

  private def distance(objectProperties : List[Any], core : List[Any]): Double ={
    val objectProp = objectProperties.asInstanceOf[List[Double]]
    val coreProp = core.asInstanceOf[List[Double]]
    coreProp.indices.map(i => Math.abs(objectProp(i) - coreProp(i))).sum
  }

  private def normalizeProperties(objectsProperties : Array[List[Any]]){
    for(item <- 1 until objectsProperties(0).size) {
      val maxVal = objectsProperties.map(_(item).asInstanceOf[Double]).max / 100
      for (i <- 0 until objectsProperties.length) {
        objectsProperties(i) = objectsProperties(i).zipWithIndex.map((e) => if(e._2==item) e._1.asInstanceOf[Double] / maxVal else e._1)
      }
    }
  }

  override def requestParameters(frame: JFrame): Unit = {
    val classCount = JOptionPane.showInputDialog(frame, "Enter class count: ")
    params.put("classCount", classCount.toInt)
  }
}
