import scala.collection.SortedMap
import scala.io.Source

class ZipfParameters(val fileName : String, val stopWordsFileName : String) {

  val text = Source.fromFile(fileName).mkString.toLowerCase.replaceAll("""[\"\,\.\—\!\?\:\;]"""," ").replaceAll("""([^\pL\pN\pP\pS\pZ])|([\xC2\xA0])""", " ").split("\\s+")
  val totalLength = text.foldLeft(0)(_ + _.length)
  var wordFrequencies = text.foldLeft(SortedMap[String, Int]())((map, word) => map + ((word, map.getOrElse(word, 0) + 1)))

  def showRankFrequencies(): Unit = {
    excludeStopWords
    var uniqFreq = SortedMap[Int, String]()(reverseOrder)
    uniqFreq ++= (for((k, v) <- wordFrequencies) yield (v,k))
    //uniqFreq = uniqFreq.filter((t) =>  t._1 > 3 && t._1 < 17)
    var rankCounter = 1

    val maxWordLength = uniqFreq.values.maxBy[Int](_.length()).length()
    printf("| %-"+maxWordLength+"s | %-4s | %s | %s |\n", "Word", "Rank", "Probation", "Zipf constant")
    for((k, v) <- uniqFreq) {
      printf("| %-"+maxWordLength+"s | %-4s | %.7f | %.11f |\n", v, rankCounter, k.toDouble/totalLength, k * rankCounter.toDouble / totalLength)
      rankCounter+=1
    }
  }

  private def excludeStopWords(): Unit ={
    for(stopWord <- Source.fromFile(stopWordsFileName).mkString.split(",\\s*")){
      wordFrequencies -= stopWord
    }
  }

  def showWordFrequencies() = println(wordFrequencies)
}
