package by.verkpavel.grafolnet.personal

import akka.actor.{Actor, Props}
import by.verkpavel.grafolnet.database.DB
import libsvm._

object PersonalActor {
  def props: Props = Props[PersonalActor]
  def name = "personal"
}

class PersonalActor extends Actor {
  val svm: LibSVM = new LibSVM
  val model: svm_model = svm.svmTrain("sampleSet.txt", 1539, 7)
  def receive = {
    case id: String =>
      val image = DB.getImageParamsByID(id)
      sender ! personalDescription((image.values.sum * 1000).toInt % 17) //svm.evaluate_single_instance((1 to 7).toArray, image.values.toArray, model)
  }

  def personalDescription(id: Int) = {
    id match  {
      case 1 => "Sanguine. Leader. Low performer."
      case 2 => "Choleric. Leader. Low performer."
      case 3 => "Phlegmatic. Leader. Low performer."
      case 4 => "Melancholic. Leader. Low performer."
      case 5 => "Sanguine. Worker. Low performer."
      case 6 => "Choleric. Worker. Low performer."
      case 7 => "Phlegmatic. Worker. Low performer."
      case 8 => "Melancholic. Worker. Low performer."
      case 9 => "Sanguine. Leader. High performer."
      case 10 => "Choleric. Leader. High performer."
      case 11 => "Phlegmatic. Leader. High performer."
      case 12 => "Melancholic. Leader. High performer."
      case 13 => "Sanguine. Worker. High performer."
      case 14 => "Choleric. Worker. High performer."
      case 15 => "Phlegmatic. Worker. High performer."
      case 16 => "Melancholic. Worker. High performer."
    }
  }
}

