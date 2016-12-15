package by.verkpavel.grafolnet.model

import akka.actor.{ Actor, Props }
import by.verkpavel.grafolnet.model.ModelActor.ItemNotFound

object ModelActor {
  def props: Props = Props[ModelActor]
  def name = "model"

  case object ItemNotFound
}

class ModelActor extends Actor with Model {

  def receive = {
    case id: Int =>
      sender ! get(id).getOrElse(ItemNotFound)

    case 'list =>
      sender ! list

    case image : ImageRequest =>


  }

}

