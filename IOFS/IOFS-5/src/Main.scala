import scala.io.Source

object Main extends App{
 val grammarBuilder = new GrammarBuilder(Source.fromFile("res/base.txt").mkString.toLowerCase().split("""\s+"""))
  grammarBuilder.build()
  println("--------------------------Source words---------------------------")
  println(Source.fromFile("res/base.txt").mkString)
  println("--------------------------Grammar---------------------------")
  println(grammarBuilder.getGrammar)
  println("--------------------------Generated words---------------------------")
  for(_ <- 0 until 3)
    print(grammarBuilder.getWord() + " ")
}
