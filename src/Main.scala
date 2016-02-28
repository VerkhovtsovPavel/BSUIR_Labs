import scala.io.Source

object Main extends App{
  new GrammarBuilder(Source.fromFile("res/base.txt").mkString.split("""\s+""")).build()

  //println(NonTerminate(mutable.Map("a"-> NonTerminate(mutable.Map("b" -> Terminate("b"))), "b" -> Terminate("b"))) equals NonTerminate(mutable.Map("a"-> NonTerminate(mutable.Map("b" -> Terminate("b"))), "b" -> Terminate("b"))))
}
