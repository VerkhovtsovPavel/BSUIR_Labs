package by.verkpavel.grafolnet

import akka.actor.ActorSystem

object Boot extends App {

  implicit val system = ActorSystem("grafolParser")
  system.actorOf(TopLevel.props, TopLevel.name)

}
