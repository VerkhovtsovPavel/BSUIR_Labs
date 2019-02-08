package by.verkpavel.grafolnet.main

import akka.actor.ActorSystem

object Boot extends App {

  implicit val system = ActorSystem("grafolParser")
  system.actorOf(TopLevel.props, TopLevel.name)

}
