package by.verkpavel.grafolnet.parser

import java.awt.image.BufferedImage

import akka.actor.{Actor, Props}
import by.verkpavel.grafolnet.database.DB
import net.sourceforge.tess4j.{Tesseract, Word}

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
      var image = DB.getImageByID(id)
      val lines = tes.getWords(image, 2)
      val linesImage = lines.map(x => image.getSubimage(x.getBoundingBox.getX.toInt, x.getBoundingBox.getY.toInt, x.getBoundingBox.width, x.getBoundingBox.height))
      val words = tes.getWords(image, 3)
      val symbols = tes.getWords(image, 4)
      val symbolsImage = symbols.map(x => image.getSubimage(x.getBoundingBox.getX.toInt, x.getBoundingBox.getY.toInt, x.getBoundingBox.width, x.getBoundingBox.height))

      val handwritePower = power(image)

      image = ImageActions.imageBinarization(image, 100)

      sender ! (handwritePower, frequense(image), linesInterval(lines), linesAngle(linesImage), wordInterval(words), symbolsInterval(symbols), symbolsAngle(symbolsImage)) //TODO Maybe use Map[String, Double]
  }

  //TODO Remove ImageAction class and inline code
  def power(image: BufferedImage) = ImageActions.middleBrihtness(image)

  def frequense(image: BufferedImage) = ImageActions.frequence(image)

  def linesInterval(lines: mutable.Buffer[Word]): Double = {
    var count = 0
    var sum = 0
    for (i <- lines.indices) {
      val lower = lines(i).getBoundingBox.getY.toInt + lines(i).getBoundingBox.height
      val upper = lines(i + 1).getBoundingBox.getY.toInt
      if (upper > lower) {
        sum += upper - lower
        count += 1
      }
    }
    sum / count
  }

  def linesAngle(lines: mutable.Buffer[BufferedImage]) = ImageActions.symbolsAngel(lines) //TODO Uncorrect

  def wordInterval(words: mutable.Buffer[Word]) = {
    var count = 0
    var sum = 0
    for (i <- words.indices) {
      val left = words(i).getBoundingBox.getX.toInt + words(i).getBoundingBox.width
      val right = words(i + 1).getBoundingBox.getX.toInt
      if (right > left) {
        sum += right - left
        count += 1
      }
    }
    sum / count
  }

  def symbolsInterval(symbols: mutable.Buffer[Word]) = {
    var count = 0
    var sum = 0
    for (i <- symbols.indices) {
      val left = symbols(i).getBoundingBox.getX.toInt + symbols(i).getBoundingBox.width
      val right = symbols(i + 1).getBoundingBox.getX.toInt
      if (right > left) {
        sum += right - left
        count += 1
      }
    }
    sum / count
  }

  def symbolsAngle(symbols: mutable.Buffer[BufferedImage]) = ImageActions.symbolsAngel(symbols)

}

