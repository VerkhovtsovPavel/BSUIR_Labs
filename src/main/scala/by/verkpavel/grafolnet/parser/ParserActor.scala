package by.verkpavel.grafolnet.parser

import java.awt.image.BufferedImage
import akka.actor.{Actor, Props}
import by.verkpavel.grafolnet.database.DB
import net.sourceforge.tess4j.Tesseract

import scala.collection.mutable

object ParserActor {
  def props: Props = Props[ParserActor]
  def name = "parser"
}

class ParserActor extends Actor {
  val tes = new Tesseract()
  import scala.collection.JavaConversions._

  def receive = {
    case id: Int =>
      val image = DB.getImageByID(id)
      val lines: mutable.Buffer[BufferedImage] = tes.getWords(image, 2).map(x => image.getSubimage(x.getBoundingBox.getX.toInt, x.getBoundingBox.getY.toInt, x.getBoundingBox.width, x.getBoundingBox.height))
      val words: mutable.Buffer[BufferedImage] = tes.getWords(image, 3).map(x => image.getSubimage(x.getBoundingBox.getX.toInt, x.getBoundingBox.getY.toInt, x.getBoundingBox.width, x.getBoundingBox.height))
      val symbols: mutable.Buffer[BufferedImage] = tes.getWords(image, 4).map(x => image.getSubimage(x.getBoundingBox.getX.toInt, x.getBoundingBox.getY.toInt, x.getBoundingBox.width, x.getBoundingBox.height))

      sender ! (power(image), frequense(image), linesInterval(lines), linesAngle(lines), wordInterval(words), symbolsInterval(symbols), symbolsAngle(symbols)) //TODO Maybe use Map[String, Double]
  }


  def power(image : BufferedImage) = ???
  def frequense(image : BufferedImage) = ???
  def linesInterval(lines: mutable.Buffer[BufferedImage]) = ???
  def linesAngle(lines: mutable.Buffer[BufferedImage]) = ???
  def wordInterval(words: mutable.Buffer[BufferedImage]) = ???
  def symbolsInterval(symbols: mutable.Buffer[BufferedImage]) = ???
  def symbolsAngle(symbols: mutable.Buffer[BufferedImage]) = ???

}

