package by.bsuir.mastering.pavelverk.main

import java.io.File

import by.bsuir.mastering.pavelverk.serialization.Serializer
import by.bsuir.mastering.pavelverk.simpleANN.NeuronNetwork

import scala.io.Source

object Main extends App{
  if(args.isEmpty)
    help

  args(0) match {
    case "-s" | "--save" => {
      if(args.length < 2)
        help
      val sampleFile = new File(args(1))
      val features: List[List[Double]] = Source.fromFile(sampleFile).mkString.split("\n").toList.map(_.split(" ").toList.map(_.toDouble))
      val ann = new NeuronNetwork(features.head.length, 0.85)
      ann.learn(features)
      val path = Serializer.serialize(ann)
      println("ID: " + path)
    }
    case "-v" | "--verify" => {
      if(args.length < 3)
        help
      val newAnn = Serializer.deserialize[NeuronNetwork](args(1))
      val features: List[Double] = args.drop(2).toList.map(_.toDouble)
      println(newAnn.classify(features))
    }
    case _ => help
  }

  private def help: Unit = {
    println("Incorrect parameters")
    System.exit(1)
  }
}
