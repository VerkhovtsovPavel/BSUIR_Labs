package by.verkpavel.iofs.documents

import java.io.File

import scala.io.Source

class Document(val category : String, val file: File) {

  val text = Source.fromFile(file).mkString.toLowerCase.replaceAll( """[\"\,\.\—\!\?\:\;\(\)\d+]|( *- )""", " ").replaceAll( """([^\pL\pN\pP\pS\pZ])|([\xC2\xA0])""", " ").split("\\s+")
  val totalLength = text.foldLeft(0)(_ + _.length)
  var wordFrequencies = text.foldLeft(Map[String, Int]())((map, word) => map + ((word, map.getOrElse(word, 0) + 1)))
  excludeStopWords()
  var vector = Array[Double](1)

  val countKeyWord = 30

  def keyWords = foundKeyWords()

  private def excludeStopWords(): Unit = {
    for (stopWord <- Source.fromFile("res/stopWords.txt").mkString.split("(,\\s*)|(\\n)")) {
      wordFrequencies -= stopWord
    }
  }

  private def foundKeyWords() : Map[String, Double] = {
    val maxFrequencies = wordFrequencies.values.max
    var list = Map[String, Int]()
    for (freq <- (1 to maxFrequencies).reverse) {
      list = list ++ wordFrequencies.filter(_._2 == freq)
    }
    list.slice(list.size / 2 - countKeyWord / 2, list.size / 2 + countKeyWord / 2).map(entity => (entity._1, entity._2.toDouble / totalLength))
  }

  def buildVector(convertDocumentToVector: (Document) => Array[Double]): Unit = {
    vector = convertDocumentToVector(this)
  }
}

object Document {
  def apply(category : String, file: File) = new Document(category, file)
  def apply(category : String, fileName: String) = new Document(category, new File(fileName))
}
