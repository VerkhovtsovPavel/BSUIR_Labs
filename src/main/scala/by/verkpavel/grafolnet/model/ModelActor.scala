package by.verkpavel.grafolnet.model

import akka.actor.{Actor, Props}
import by.verkpavel.grafolnet.database.DB

object ModelActor {
  def props: Props = Props[ModelActor]
  def name = "model"

  case object ItemNotFound
}

class ModelActor extends Actor {

  def receive = {
    case id: Int =>
      sender ! DB.getImageByID(id)

    case 'list =>
      sender ! DB.getImages("userID") //TODO Change on real userID
  }

}

