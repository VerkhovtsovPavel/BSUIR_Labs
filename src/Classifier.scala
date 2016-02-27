import by.verkpavel.iofs.documents.Document

class Classifier(textSamples : Document*) {

  val functions : Map[String, DecisionFunction] = textSamples.map(doc => (doc.category, new DecisionFunction)).toMap
  val parameters = buildParameters()
  learn()

  private def buildParameters() = {
    textSamples.map(_.keyWord).sorted.toArray
  }

  private def learn(): Unit = {
    val sampleSet = new Circular[Document](textSamples.toList)



  }


}
