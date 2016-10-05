package by.bsuir.verpav.misoi.clustering.steps

import java.awt.image.BufferedImage
import javax.swing.JFrame

import scala.collection.mutable

/**
  * Created by Pavel_Verkhovtsov on 10/5/16.
  */
trait ClusteringStep {
  val params = mutable.Map[String, Int]()
  var stepContext : Any = _
  def perform(baseImage: BufferedImage): BufferedImage

  def requestParameters(frame: JFrame): Unit
  def setContext(context : Any) = {stepContext = context; this}
  def apply(baseImage: BufferedImage): BufferedImage =  perform(baseImage)
}
