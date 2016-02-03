package by.verkpavel.iofs.main

import by.verkpavel.iofs.documents.DocumentSearcher

import scala.io.StdIn

object Main extends App {
  val searchEngine = new DocumentSearcher("texts\\Texts")

  while (true) {
    print("Enter search query > ")
    val query = StdIn.readLine()

    val result = searchEngine.search(query)

    println(result.foldLeft("Search result : \n")((res, elem) => res + elem._1.getFileName + " => " + elem._2.mkString(", ") + "\n"))
  }
}