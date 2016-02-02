package by.verkpavel.iofs.documents

import java.io.File
import collection.mutable

class DocumentSearcher(val directory : String) {

  val documents = new File(directory).listFiles().filter(_.getAbsolutePath.endsWith(".txt")).map(Document(_))


  def search(query : String) = {
    val result = mutable.Map[Document, List[String]]()
    val searchWords = query.split("\\s+")

    for(doc <- documents)
      for(word <- searchWords){
        if(doc.isDocumentContainsWord(word)){
          result.put(doc, result.getOrElse(doc, List[String]()) :+ word)
        }
      }
    result
  }
}
