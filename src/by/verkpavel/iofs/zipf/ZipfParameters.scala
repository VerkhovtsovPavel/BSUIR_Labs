package by.verkpavel.iofs.zipf

import by.verkpavel.iofs.graph.LineChart

import scala.collection.SortedMap
import scala.io.Source

class ZipfParameters(val fileName: String, val stopWordsFileName: String) {

  val text = Source.fromFile(fileName).mkString.toLowerCase.replaceAll( """[\"\,\.\—\!\?\:\;\(\)]|( *- )""", " ").replaceAll( """([^\pL\pN\pP\pS\pZ])|([\xC2\xA0])""", " ").split("\\s+")
  val totalLength = text.foldLeft(0)(_ + _.length)
  var wordFrequencies = text.foldLeft(SortedMap[String, Int]())((map, word) => map + ((word, map.getOrElse(word, 0) + 1)))
  excludeStopWords()

  def showRankFrequencies(preText : String) = {
    var uniqueFrequencies = SortedMap[Int, String]()(ReverseIntOrdering)
    uniqueFrequencies ++= (for ((k, v) <- wordFrequencies) yield (v, k))
    //uniqueFrequencies = uniqueFrequencies.filter((t) =>  t._1 > 3 && t._1 < 17)
    printTable(preText, uniqueFrequencies)
    this
  }

  def showQuantityFrequencies(chartTitle : String =  "Quantity-Frequencies") = {
    val maxFrequencies = wordFrequencies.values.max

    var list = List[Int]()
    for (freq <- 1 to maxFrequencies) {
      list = list :+ wordFrequencies.count(_._2 == freq)
    }

    val chart = new LineChart(chartTitle)
    chart.addDataset(list)
    chart.draw()

    this
  }

  def showKeyWords() = {
    val maxFrequencies = wordFrequencies.values.max

    var list = List[String]()
    for (freq <- (1 to maxFrequencies).reverse) {
      list = list ++ wordFrequencies.filter(_._2 == freq).keys
    }

    println(list.take(15)mkString("Key words : (", ", ",")"))
    this
  }

  def showWordFrequencies() = println(wordFrequencies)

  private def excludeStopWords(): Unit = {
    for (stopWord <- Source.fromFile(stopWordsFileName).mkString.split(",\\s*")) {
      wordFrequencies -= stopWord
    }
  }

  private def printTable(preText : String, uniqueFrequencies : collection.Map[Int, String]): Unit ={
    var rankCounter = 1
    val maxWordLength = uniqueFrequencies.values.maxBy[Int](_.length).length
    val headerFormat = "| %-" + maxWordLength + "s | %-4s | %s | %s |\n"
    val horizontalDelimiter = "-"*(maxWordLength+39)

    println(preText+":")
    println(horizontalDelimiter)
    printf(headerFormat, "Word", "Rank", "Probation", "Zipf constant")
    println(horizontalDelimiter)
    for ((k, v) <- uniqueFrequencies) {
      printf("| %-" + maxWordLength + "s | %-4s | %.7f | %.11f |\n", v, rankCounter, k.toDouble / totalLength, k * rankCounter.toDouble / totalLength)
      rankCounter += 1
    }
    println(horizontalDelimiter)
  }
}

object ZipfParameters{
  def apply(fileName: String, stopWordsFileName: String) = new ZipfParameters(fileName, stopWordsFileName)
}