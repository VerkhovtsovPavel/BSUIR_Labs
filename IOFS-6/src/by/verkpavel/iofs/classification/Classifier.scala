package by.verkpavel.iofs.classification

import by.verkpavel.iofs.documents.Document
import by.verkpavel.iofs.utils.Circular

class Classifier(textSamples : Document*) {
  val parameters = buildParameters()
  val functions : Map[String, DecisionFunction] = textSamples.map(doc => (doc.category, new DecisionFunction(parameters))).toMap
  learn()

  private def buildParameters() = {
    textSamples.flatMap(_.keyWords.keys).sorted.toArray
  }

  private def learn(): Unit = {
    textSamples.toList.foreach(_.buildVector(convertDocumentToVector))
    val sampleSet = new Circular[Document](textSamples.toList)
    var successfulClassificationCounter = 0

    while(successfulClassificationCounter != sampleSet.realSize){
      val currentDocument = sampleSet.next

      if(classify(currentDocument)!= currentDocument.category) {
        val partitionList = functions.partition(_._1 == currentDocument.category)
        partitionList._1.foreach(_._2.promotion(currentDocument.vector))
        partitionList._2.foreach(_._2.punishment(currentDocument.vector))

        successfulClassificationCounter = 0
      }else{
        successfulClassificationCounter+=1
      }
    }
  }

  def classify(document : Document) : String = {
    functions.map(pair =>(pair._1, pair._2.calculate(document))).maxBy(_._2)._1
  }

  implicit def convertDocumentToVector(text : Document) : Array[Double] = parameters.map(text.keyWords.getOrElse(_, 0.0))
}
