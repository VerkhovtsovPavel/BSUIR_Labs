

object Main extends App {
  val englishText = new ZipfParameters("English.txt", "stopWordsEnglish.txt")
  englishText.showRankFrequencies()

  val russianText = new ZipfParameters("Russian.txt", "stopWordsRussian.txt")
  russianText.showRankFrequencies()
//
//  val belorussian = new ZipfParameters("Belorussian.txt")
//  belorussian.showRankFrequencies()
//  print(Source.fromFile("stopWordsEnglish.txt").mkString.split("(,\\s+)|(\\n+)").map(_.trim).mkString(","))
}

object reverseOrder extends Ordering[Int] {
  override def compare(x: Int, y: Int): Int = y - x
}

