package by.verkpavel.iofs.main

import by.verkpavel.iofs.zipf.ZipfParameters


object Main extends App {
  val englishText = new ZipfParameters("texts/English.txt", "texts/stopWordsEnglish.txt")
  englishText.showRankFrequencies()
  englishText.showQuantityFrequencies()

  val russianText = new ZipfParameters("texts/Russian.txt", "texts/stopWordsRussian.txt")
  russianText.showRankFrequencies()
  //
  //  val belorussian = new ZipfParameters("texts/Belorussian.txt")
  //  belorussian.showRankFrequencies()
  //  print(Source.fromFile("stopWordsEnglish.txt").mkString.split("(,\\s+)|(\\n+)").map(_.trim).mkString(","))
}
