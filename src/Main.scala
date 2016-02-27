import java.io.File

import by.verkpavel.iofs.documents.Document

object Main extends App{
  new Classifier(Document("mathematics", "res/texts/sampleSet/mathematics/1.txt"),
                 Document("programming", "res/texts/sampleSet/programming/1.txt"),
                 Document("philosophy", "res/texts/sampleSet/philosophy/1.txt"))

}
