package by.verkpavel.iofs

class HuffmanCode(message : String) {

  val totalLength = message.trim.length
  var list : List[Item] = message.trim.toLowerCase.toCharArray.foldLeft(Map[Char, Int]())((map, char) => map + ((char, map.getOrElse(char, 0) +1))).map((item) => Leaf(item._1, item._2.toDouble / totalLength)).toList
  buildTree()

  var lettersCode : Map[Char, String] = Map[Char, String]()

  def buildCode(item : Item = list.head, code : String = "") : HuffmanCode = {
    item match {
      case Node(left, right) => buildCode(left, code+0) ; buildCode(right, code+1)
      case Leaf(char, _) => lettersCode += ((char, code))
    }
    this
  }

  def printCode(): Unit = {
    print(lettersCode.mkString("\n"))
  }

  private def buildTree(): Unit = {
    while(list.size != 1){
      list = list.sortBy(_.weight)
      val left = list.head
      val right = list.tail.head
      list = list.tail.tail :+ Node(left, right)
    }
  }
}
