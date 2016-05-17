package by.verkpavel.iofs.main

import by.verkpavel.iofs.classification.Classifier
import by.verkpavel.iofs.documents.Document

object Main extends App{
  val classifier = new Classifier(Document("mathematics", "res/texts/sampleSet/mathematics/2.txt"),
                                  Document("programming", "res/texts/sampleSet/programming/1.txt"),
                                  Document("philosophy", "res/texts/sampleSet/philosophy/1.txt"))


  println("----------Sample set---------------")
  println(classifier.classify(Document("mathematics", "res/texts/sampleSet/mathematics/2.txt")))
  println(classifier.classify(Document("programming", "res/texts/sampleSet/programming/1.txt")))
  println(classifier.classify(Document("philosophy", "res/texts/sampleSet/philosophy/1.txt")))
  println("-----------------------------------")

  println("----------Other text---------------")
  println(classifier.classify(Document("unknown", "res/texts/mathematics/3.txt")))
  println(classifier.classify(Document("unknown", "res/texts/philosophy/1.txt")))
  println(classifier.classify(Document("unknown", "res/texts/programming/1.txt")))
  println("-----------------------------------")
}
