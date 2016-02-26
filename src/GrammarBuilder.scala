import scala.collection.mutable
class GrammarBuilder(words : Array[String]) {
  val termChains = words.sortBy(_.length).reverse
  val grammar = List[GrammarItem]
  var linkCount = 0

  val root = new NonTerminate(List(), List())

  def build(): Unit ={
    firstStage()
  }

  private def firstStage() = {
    for (word <- termChains){
      parseWord(word)
    }
  }

  private def parseWord(word : String, nextLink : GrammarItem = root): Unit ={
    if(word.nonEmpty) {
      nextLink match {
        case Some(list) if !list.map(_.replaceAll("~/d+", "")).contains(word.take(1)) =>
          linkCount += 1
          grammar.put(nextLink, list :+ (word(0) + "~" + linkCount))
          parseWord(word.drop(1), "~" + linkCount)
        case Some(list) =>
          val link = list.filter(_.contains(word.take(1))).head.replaceAll( """\w+(?=~)""", "")
          parseWord(word.drop(1), link)
        case None =>
          linkCount += 1
          (nextLink, List(word.take(1) + "~" + linkCount))
          parseWord(word.drop(1), "~" + linkCount)
      }
    }
  }
}

sealed case class GrammarItem
case class Terminate(str : String) extends GrammarItem
case class NonTerminate(var link : List[GrammarItem],var srt : List[String]/*Maybe use map*/)  extends GrammarItem
