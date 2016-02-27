package by.verkpavel.iofs.documents

import java.io.File

import scala.io.Source

class Document(val category : String, val file: File) {

  val text = Source.fromFile(file).mkString.toLowerCase.replaceAll( """[\"\,\.\—\!\?\:\;\(\)\d+]|( *- )""", " ").replaceAll( """([^\pL\pN\pP\pS\pZ])|([\xC2\xA0])""", " ").split("\\s+")
  val totalLength = text.foldLeft(0)(_ + _.length)
  var wordFrequencies = text.foldLeft(Map[String, Int]())((map, word) => map + ((word, map.getOrElse(word, 0) + 1)))
  excludeStopWords()

  def keyWord = foundKeyWords()

  private def excludeStopWords(): Unit = {
    for (stopWord <- Source.fromFile("res/stopWords.txt").mkString.split("(,\\s*)|(\\n)")) {
      wordFrequencies -= stopWord
    }
  }

  private def foundKeyWords() = {
    val maxFrequencies = wordFrequencies.values.max
    var list = List[String]()
    for (freq <- (1 to maxFrequencies).reverse) {
      list = list ++ wordFrequencies.filter(_._2 == freq).keys
    }
    list.slice(list.length / 2 - 5, list.length / 2 + 5)
  }
}

object Document {
  def apply(category : String, file: File) = new Document(category, file)
  def apply(category : String, fileName: String) = new Document(category, new File(fileName))
}
