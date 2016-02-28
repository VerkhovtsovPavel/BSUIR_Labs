import scala.collection.mutable

class GrammarBuilder(words : Array[String]) {
  val termChains = words.sortBy(_.length).reverse
  var linkCount = 0

  val root = new NonTerminate(mutable.Map())

  def build(): Unit ={
    firstStage()
    secondStage()
  }

  private def firstStage() = {
    val maxLength = termChains.head.length
    for (word <- termChains){
      parseWord(word, word.length == maxLength)
    }
  }

  private def parseWord(word : String, isLongest : Boolean, nextLink : GrammarItem = root): Unit ={
    if(word.nonEmpty && (!isLongest || word.length>1 )){
      nextLink match {
        case NonTerminate(map) if !map.keys.toList.contains(word.head.toString) =>
          if(isLongest && word.length == 2) {
            map.put(word.take(2), Terminate(word.take(2)))
            parseWord("", isLongest)
          }
          else if(word.length == 1){
            map.put(word.take(1), Terminate(word.take(1)))
            parseWord("", isLongest)
          }
          else{
            val newNode = NonTerminate(mutable.Map())
            map.put(word.take(1), newNode)
            parseWord(word.tail, isLongest, newNode)
          }
        case NonTerminate(map) =>
          parseWord(word.drop(1), isLongest, map.get(word.head.toString).get)
        case Terminate(_) =>

      }
    }
  }

  private def buildDoubleTermElementsList(node : GrammarItem = root): List[NonTerminate] =
    node match {
      case item @ NonTerminate(map) if map.keys.count(_.length == 2) > 0 => item +: map.values.flatMap(buildDoubleTermElementsList).toList
      case item @ NonTerminate(map) => map.values.flatMap(buildDoubleTermElementsList).toList
      case item : Terminate => Nil
    }

  private def findAlternativeNode(termSeq: String, currentNode : GrammarItem = root): NonTerminate = {
       if (currentNode.isInstanceOf[NonTerminate] && currentNode.asInstanceOf[NonTerminate].map.keys.toList.contains(termSeq.head.toString)) {
         val child = currentNode.asInstanceOf[NonTerminate].map(termSeq.head.toString)
         if (child.isInstanceOf[NonTerminate] && child.asInstanceOf[NonTerminate].map.contains(termSeq.tail) && child.asInstanceOf[NonTerminate].map(termSeq.tail).isInstanceOf[Terminate]) {
           currentNode.asInstanceOf[NonTerminate]
         } else { currentNode.asInstanceOf[NonTerminate].map.values.map(findAlternativeNode(termSeq, _)).head }
       }else{ currentNode.asInstanceOf[NonTerminate].map.values.map(findAlternativeNode(termSeq, _)).head }
    }

  def replaceNodes(oldNode: NonTerminate, newNode: NonTerminate, currentNode : GrammarItem = root) : Unit = {
    currentNode match {
      case node : NonTerminate if node.map.values.toList.contains(oldNode) =>
        val key = oldNode.map.keys.filter(_.length == 2).head.head.toString
        newNode.map = newNode.map ++ oldNode.map
        newNode.map = newNode.map.filter(_._1.length < 2)
        node.map.put(key, newNode)
      case node : NonTerminate =>  node.map.values.foreach(replaceNodes(oldNode, newNode, _))
      case _ =>
    }
    }

  private def secondStage(): Unit = {
    val nodesWithDoubleItems = buildDoubleTermElementsList()
    val transformationMap = mutable.Map[NonTerminate, NonTerminate]()
    val w = for(node <- nodesWithDoubleItems)
      transformationMap.put(node, findAlternativeNode(node.map.keys.filter(_.length == 2).head, root))

    transformationMap.foreach((pair) => replaceNodes(pair._1, pair._2))
  }

  def removeDuplicates(currentNode : GrammarItem): Unit = {
    currentNode match {
      case node : NonTerminate =>
        for (pair <- node.map){
          if(
        }
    }
  }

  private def thirdStage() : Unit = {
    removeDuplicates(root)
  }
}