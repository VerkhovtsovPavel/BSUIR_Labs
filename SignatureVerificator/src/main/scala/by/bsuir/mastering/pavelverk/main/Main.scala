package by.bsuir.mastering.pavelverk.main

import java.io.File

import by.bsuir.mastering.pavelverk.serialization.Serializer
import by.bsuir.mastering.pavelverk.simpleANN.NeuronNetwork

import scala.io.Source

object Main extends App{
  val sampleFile = "src/main/resources/signsSample.txt"

  val features: List[List[Double]] = Source.fromFile(new File(sampleFile)).mkString.split("\n").toList.map(_.split(" ").toList.map(_.toDouble))

  val ann = new NeuronNetwork(features.head.length, 0.85)
  ann.learn(features)

  for(sample <- features)
    println(ann.classify(sample))

  val path = Serializer.serialize(ann)

  val newAnn = Serializer.deserialize[NeuronNetwork](path)

  for(sample <- features)
    println(newAnn.classify(sample))
}
