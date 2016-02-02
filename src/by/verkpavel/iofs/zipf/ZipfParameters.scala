package by.verkpavel.iofs.zipf

import by.verkpavel.iofs.graph.LineChart

import scala.collection.SortedMap
import scala.io.Source

class ZipfParameters(val fileName: String) {

  val text = Source.fromFile(fileName).mkString.toLowerCase.replaceAll( """[\"\,\.\—\!\?\:\;\(\)]|( *- )""", " ").replaceAll( """([^\pL\pN\pP\pS\pZ])|([\xC2\xA0])""", " ").split("\\s+")
  val totalLength = text.foldLeft(0)(_ + _.length)
  var wordFrequencies = text.foldLeft(SortedMap[String, Int]())((map, word) => map + ((word, map.getOrElse(word, 0) + 1)))

  excludeStopWords()

  def showRankFrequencies(preText : String = "") = {
    var uniqueFrequencies = SortedMap[Int, String]()(ReverseIntOrdering)
    uniqueFrequencies ++= (for ((k, v) <- wordFrequencies) yield (v, k))
    //uniqueFrequencies = uniqueFrequencies.slice(3, 17)
    printTable(preText, uniqueFrequencies)

    val chart = new LineChart("Rank-Frequencies ("+preText+")")
    chart.addDataset(uniqueFrequencies.keys.toList.map(_.toDouble / totalLength))
    chart.draw("Rank" ,"Frequencies")

    this
  }

  def showQuantityFrequencies(chartTitle : String =  "Quantity-Frequencies") = {
    val maxFrequencies = wordFrequencies.values.max

    var list = List[Double]()
    for (freq <- 1 to maxFrequencies) {
      list = list :+ wordFrequencies.count(_._2 == freq).toDouble
    }

    val chart = new LineChart(chartTitle)
    chart.addDataset(list)
    chart.draw("Frequencies", "Quantity")

    this
  }

  def showKeyWords() = {
    val maxFrequencies = wordFrequencies.values.max

    var list = List[String]()
    for (freq <- (1 to maxFrequencies).reverse) {
      list = list ++ wordFrequencies.filter(_._2 == freq).keys
    }

    println(list.slice(list.length/2 -5, list.length/2 +5).mkString("Key words : (", ", ",")"))
    this
  }

  def showWordFrequencies() = println(wordFrequencies)

  private def excludeStopWords(): Unit = {
    for (stopWord <- Source.fromFile("texts/stopWords.txt").mkString.split("(,\\s*)|(\\n)")) {
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
  def apply(fileName: String) = new ZipfParameters(fileName)
}