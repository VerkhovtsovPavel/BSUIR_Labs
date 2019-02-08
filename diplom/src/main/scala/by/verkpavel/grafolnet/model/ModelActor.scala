package by.verkpavel.grafolnet.model

import akka.actor.{Actor, Props}
import by.verkpavel.grafolnet.database.DB
import by.verkpavel.grafolnet.database.domain.Sample
import com.mongodb.casbah.Imports.ObjectId

object ModelActor {
  def props: Props = Props[ModelActor]
  def name = "model"

  case object ItemNotFound
}

class ModelActor extends Actor {

  def receive = {
    case id: String =>
      sender ! DB.getImageByID(id)

    case ('delete, id: String) =>
      sender ! DB.deleteSampleByID(id)

    case (image: Array[Byte], format: String, owner: ObjectId) =>
      sender ! DB.addSample(Sample(user_id = owner, imageFormat = format, imageSource = image))

    case id: ObjectId =>
      sender ! DB.getImages(id)
  }
}

