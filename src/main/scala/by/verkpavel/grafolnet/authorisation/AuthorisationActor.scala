package by.verkpavel.grafolnet.authorisation

import akka.actor.{Actor, Props}
import by.verkpavel.grafolnet.database.DB
import by.verkpavel.grafolnet.database.domain.User

object AuthorisationActor {
  def props: Props = Props[AuthorisationActor]
  def name = "authorisation"

  case object ItemNotFound
}

class AuthorisationActor extends Actor {

  def receive = {
    case (userName: String, password: String) =>
      sender ! DB.isUserExist(userName, password)

    case user: User =>
      sender ! DB.addUser(user)
  }

}

