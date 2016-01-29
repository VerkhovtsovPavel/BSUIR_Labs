package by.verkpavel.iofs.zipf

import by.verkpavel.iofs.graph.LineChart

import scala.collection.SortedMap
import scala.io.Source

class ZipfParameters(val fileName: String, val stopWordsFileName: String) {

  val text = Source.fromFile(fileName).mkString.toLowerCase.replaceAll( """[\"\,\.\—\!\?\:\;]""", " ").replaceAll( """([^\pL\pN\pP\pS\pZ])|([\xC2\xA0])""", " ").split("\\s+")
  val totalLength = text.foldLeft(0)(_ + _.length)
  var wordFrequencies = text.foldLeft(SortedMap[String, Int]())((map, word) => map + ((word, map.getOrElse(word, 0) + 1)))
  excludeStopWords()

  def showRankFrequencies(): Unit = {
    var uniqueFrequencies = SortedMap[Int, String]()(ReverseIntOrdering)
    uniqueFrequencies ++= (for ((k, v) <- wordFrequencies) yield (v, k))
    //uniqueFrequencies = uniqueFrequencies.filter((t) =>  t._1 > 3 && t._1 < 17)
    var rankCounter = 1

    val maxWordLength = uniqueFrequencies.values.maxBy[Int](_.length()).length()
    printf("| %-" + maxWordLength + "s | %-4s | %s | %s |\n", "Word", "Rank", "Probation", "Zipf constant")
    for ((k, v) <- uniqueFrequencies) {
      printf("| %-" + maxWordLength + "s | %-4s | %.7f | %.11f |\n", v, rankCounter, k.toDouble / totalLength, k * rankCounter.toDouble / totalLength)
      rankCounter += 1
    }
  }

  def showQuantityFrequencies(): Unit = {
    val maxFrequecie = wordFrequencies.values.max

    var list = List[Int]()
    for (freq <- 1 to maxFrequecie) {
      list = list :+ wordFrequencies.count(_._2 == freq)
    }

    val chart = new LineChart("Quantity-Frequencies")
    chart.addDataset(list)
    chart.draw()
  }

  def showWordFrequencies() = println(wordFrequencies)

  private def excludeStopWords(): Unit = {
    for (stopWord <- Source.fromFile(stopWordsFileName).mkString.split(",\\s*")) {
      wordFrequencies -= stopWord
    }
  }
}

object ReverseIntOrdering extends Ordering[Int] {
  override def compare(x: Int, y: Int): Int = y - x
}
