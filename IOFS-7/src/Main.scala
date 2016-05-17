object Main extends App{
  val synopsis = Document("./texts/Texts/8.txt").buildSynopsis(0.2)
  println("Synopsis:\n" + synopsis)
  println("Synopsis total length "+ synopsis.length)
}
