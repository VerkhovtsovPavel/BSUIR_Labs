package by.verkpavel.grafolnet.personal

import akka.actor.{Actor, Props}
import by.verkpavel.grafolnet.database.DB
import libsvm._

object PersonalActor {
  def props: Props = Props[PersonalActor]
  def name = "personal"
}

class PersonalActor extends Actor {
  val t123: LibSVM = new LibSVM
  val model2: svm_model = t123.svmTrain("sonar_scale_train.txt", 208, 60)
  def receive = {
    case id: Int =>
      val image = DB.getImageParamsByID(id)

    sender ! t123.evaluate_single_instance((1 to 6).toArray, image.values.toArray, model2)
  }

}

