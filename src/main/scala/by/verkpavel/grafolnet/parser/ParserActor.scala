package by.verkpavel.grafolnet.parser

import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import javax.imageio.ImageIO

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
    case id: String =>
      val sample = DB.getSampleByID(id)
      var image = ImageIO.read(new ByteArrayInputStream(sample.imageSource))
      val lines = tes.getWords(image, 2)
      val linesImage = lines.map(x => image.getSubimage(x.getBoundingBox.getX.toInt, x.getBoundingBox.getY.toInt, x.getBoundingBox.width, x.getBoundingBox.height))
      val words = tes.getWords(image, 3)
      val symbols = tes.getWords(image, 4)
      val symbolsImage = symbols.map(x => image.getSubimage(x.getBoundingBox.getX.toInt, x.getBoundingBox.getY.toInt, x.getBoundingBox.width, x.getBoundingBox.height))

      val handwritePower = power(image)

      image = ImageActions.imageBinarization(image, (handwritePower * 0.7).toInt)

      val parameters = Map[String, Double](
        "characterTilt" -> symbolsAngle(symbolsImage),
        "lineTilt" -> linesAngle(linesImage),
        "characterSpacing" -> symbolsInterval(symbols) / image.getWidth * 1000,
        "wordSpacing" -> wordInterval(words) / image.getWidth * 1000,
        "lineSpacing" -> linesInterval(lines) / image.getHeight * 1000,
        "frequencyOfText" -> frequense(image),
        "pressingForce" -> handwritePower
      )

      sample.handwriteFeatures = parameters
      DB.updateSample(sample)

      sender ! parameters
  }

  //TODO Remove ImageAction class and inline code
  def power(image: BufferedImage) = ImageActions.middleBrihtness(image)

  def frequense(image: BufferedImage) = ImageActions.frequence(image)

  def linesInterval(lines: mutable.Buffer[Word]): Double = {
    var count = 0
    var sum = 0
    for (i <- 0 to lines.size - 2) {
      val lower = lines(i).getBoundingBox.getY.toInt
      val upper = lines(i + 1).getBoundingBox.getY.toInt
      if (upper > lower) {
        sum += upper - lower
        count += 1
      }
    }
    sum.toDouble / count
  }

  def linesAngle(lines: mutable.Buffer[BufferedImage]) = ImageActions.symbolsAngel(lines) - 1.5708

  def wordInterval(words: mutable.Buffer[Word]) = {
    var count = 0
    var sum = 0
    for (i <- 0 to words.size - 2) {
      val left = words(i).getBoundingBox.getX.toInt + words(i).getBoundingBox.width
      val right = words(i + 1).getBoundingBox.getX.toInt
      if (right > left) {
        sum += right - left
        count += 1
      }
    }
    sum.toDouble / count
  }

  def symbolsInterval(symbols: mutable.Buffer[Word]) = {
    var count = 0
    var sum = 0
    for (i <- 0 to symbols.size - 2) {
      val left = symbols(i).getBoundingBox.getX.toInt + symbols(i).getBoundingBox.width
      val right = symbols(i + 1).getBoundingBox.getX.toInt
      if (right > left) {
        sum += right - left
        count += 1
      }
    }
    sum.toDouble / count
  }

  def symbolsAngle(symbols: mutable.Buffer[BufferedImage]) = ImageActions.symbolsAngel(symbols)

}

