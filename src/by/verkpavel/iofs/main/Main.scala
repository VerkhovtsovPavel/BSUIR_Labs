package by.verkpavel.iofs.main

import by.verkpavel.iofs.zipf.ZipfParameters


object Main extends App {
  ZipfParameters("texts/English.txt", "texts/stopWordsEnglish.txt").showRankFrequencies("English").showQuantityFrequencies("Quantity-Frequencies (English)").showKeyWords()
  ZipfParameters("texts/Russian.txt", "texts/stopWordsRussian.txt").showRankFrequencies("Russian").showQuantityFrequencies("Quantity-Frequencies (Russian)").showKeyWords()
  ZipfParameters("texts/Belarusian.txt", "texts/stopWordsBelarusian.txt").showRankFrequencies("Belarusian").showQuantityFrequencies("Quantity-Frequencies (Belarusian)").showKeyWords()
}
