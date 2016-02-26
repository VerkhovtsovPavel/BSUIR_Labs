import scala.io.Source

object Main extends App{
  new GrammarBuilder(Source.fromFile("res/base.txt").mkString.split("""\s+""")).build()
}
