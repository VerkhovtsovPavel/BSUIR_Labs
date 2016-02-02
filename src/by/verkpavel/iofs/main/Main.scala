package by.verkpavel.iofs.main

import by.verkpavel.iofs.documents.Document


object Main extends App {
  Document("texts/English.txt").showRankFrequencies("English").showQuantityFrequencies("Quantity-Frequencies (English)").showKeyWords()
//  ZipfParameters("texts/Russian.txt").showRankFrequencies("Russian").showQuantityFrequencies("Quantity-Frequencies (Russian)").showKeyWords()
//  ZipfParameters("texts/Belarusian.txt").showRankFrequencies("Belarusian").showQuantityFrequencies("Quantity-Frequencies (Belarusian)").showKeyWords()
}
