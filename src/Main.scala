import scala.io.Source

object Main extends App{
 val grammarBuilder = new GrammarBuilder(Source.fromFile("res/base.txt").mkString.toLowerCase().split("""\s+"""))
  grammarBuilder.build()
  println("--------------------------Source words---------------------------")
  println(Source.fromFile("res/base.txt").mkString)
  println("--------------------------Grammar---------------------------")
  println(grammarBuilder.getGrammar())
  println("--------------------------Generated words---------------------------")
  for(_ <- 0 to 10)
    print(grammarBuilder.getWord() + " ")

  //println(NonTerminate(mutable.Map("a"-> NonTerminate(mutable.Map("b" -> Terminate("b"))), "b" -> Terminate("b"))) equals NonTerminate(mutable.Map("a"-> NonTerminate(mutable.Map("b" -> Terminate("b"))), "b" -> Terminate("b"))))

  //println(('a','b') equals ('a','b'))
}
