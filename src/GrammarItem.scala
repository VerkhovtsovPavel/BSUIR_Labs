import scala.collection.mutable

sealed class GrammarItem()
case class Terminate(str : String) extends GrammarItem
case class NonTerminate(var map : mutable.Map[String,GrammarItem])  extends GrammarItem

