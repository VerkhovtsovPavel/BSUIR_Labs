package by.verkpavel.iofs.main

import by.verkpavel.iofs.documents.{DocumentSearcher, Document}

import scala.io.StdIn


object Main extends App {
  val searchEngine = new DocumentSearcher("texts\\Texts")

  val query = StdIn.readLine()

  val result = searchEngine.search("items взять")

  println(result.foldLeft("Search result : \n")((res, elem) => res + elem._1.getFileName+" => "+elem._2.mkString(", ")+"\n"))
}
