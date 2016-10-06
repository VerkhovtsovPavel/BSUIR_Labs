package by.bsuir.verpav.misoi.clustering.steps

import java.awt.image.BufferedImage
import javax.swing.JFrame

import scala.collection.mutable

/**
  * Created by Pavel_Verkhovtsov on 10/5/16.
  */
trait ClusteringStep {
  //TODO Maybe combane 'params' and 'context'
  val params = mutable.Map[String, Int]()
  var stepContext : mutable.Map[String, Any] = _
  def perform(baseImage: BufferedImage): BufferedImage

  def requestParameters(frame: JFrame): Unit
  def setContext(context : Any) = { this}
  def apply(baseImage: BufferedImage)(implicit context : mutable.Map[String, Any]): BufferedImage ={stepContext = context;  perform(baseImage)}
}
