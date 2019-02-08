package by.verkpavel.iofs.utils

import scala.util.Random


object StringGenerator {
  def generateStrings(size : Int, count : Int){
    var result = ""
    var random = Random.alphanumeric

    for(i <- 0 to count) {
      result+=random.take(size).mkString("") + " "
      random = random.drop(size)
    }

    print(result.trim)
  }
}
