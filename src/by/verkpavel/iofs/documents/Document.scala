package by.verkpavel.iofs.documents

import by.verkpavel.iofs.graph.LineChart

import scala.collection.SortedMap
import scala.io.Source

class Document(val fileName: String) {

  val text = Source.fromFile(fileName).mkString.toLowerCase.replaceAll( """[\"\,\.\—\!\?\:\;\(\)]|( *- )""", " ").replaceAll( """([^\pL\pN\pP\pS\pZ])|([\xC2\xA0])""", " ").split("\\s+")
  val totalLength = text.foldLeft(0)(_ + _.length)
  var wordFrequencies = text.foldLeft(SortedMap[String, Int]())((map, word) => map + ((word, map.getOrElse(word, 0) + 1)))
  excludeStopWords()

  private def excludeStopWords(): Unit = {
    for (stopWord <- Source.fromFile("texts/stopWords.txt").mkString.split("(,\\s*)|(\\n)")) {
      wordFrequencies -= stopWord
    }
  }

  def getWordFrequencies(word : String) = wordFrequencies.getOrElse(word, 0).toDouble / totalLength

}

object Document{
  def apply(fileName: String) = new Document(fileName)
}