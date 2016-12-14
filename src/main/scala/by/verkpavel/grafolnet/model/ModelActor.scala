package by.verkpavel.grafolnet.model

import akka.actor.{ Actor, Props }
import by.verkpavel.grafolnet.model.ModelActor.{ ItemNotFound, ItemSummaries }

object ModelActor {
  def props: Props = Props[ModelActor]
  def name = "model"

  case object ItemNotFound
  case class ItemSummaries(items: Seq[ItemSummary])
}

class ModelActor extends Actor with Model {

  def receive = {
    case id: Int =>
      sender ! get(id).getOrElse(ItemNotFound)

    case 'list =>
      sender ! ItemSummaries(list)

    case ('query, term: String) =>
      sender ! ItemSummaries(query(term))
  }

}

