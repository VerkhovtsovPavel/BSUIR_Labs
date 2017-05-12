package by.verkpavel.grafolnet.main

import akka.actor.SupervisorStrategy.{Restart, Stop}
import akka.actor.{Terminated, _}
import akka.io.IO
import akka.util.Timeout
import by.verkpavel.grafolnet.authorisation.AuthorisationActor
import by.verkpavel.grafolnet.model.ModelActor
import by.verkpavel.grafolnet.parser.ParserActor
import by.verkpavel.grafolnet.personal.PersonalActor
import by.verkpavel.grafolnet.service.ServiceActor
import spray.can.Http

object TopLevel {
  def props: Props = Props[ProductionTopLevel]
  def name = "top-level"
}

class ProductionTopLevel extends TopLevel with TopLevelConfig {
  private def c = context.system.settings.config
  def host = c.getString("grafolParser.service.host")
  def port = c.getInt("grafolParser.service.port")
  implicit def askTimeout = Timeout(c.getMilliseconds("grafolParser.service.ask-timeout"))

  def createModel = context.actorOf(ModelActor.props, ModelActor.name)
  def createParser = context.actorOf(ParserActor.props, ParserActor.name)
  def createPersonal = context.actorOf(PersonalActor.props, PersonalActor.name)
  def createAuthorisation = context.actorOf(AuthorisationActor.props, AuthorisationActor.name)
  def createService(model: ActorRef, parser: ActorRef, personal: ActorRef, authorisation: ActorRef) = context.actorOf(ServiceActor.props(model, parser, personal, authorisation), ServiceActor.name)
}

trait TopLevelConfig {
  def createModel: ActorRef
  def createParser: ActorRef
  def createPersonal: ActorRef
  def createAuthorisation: ActorRef
  def createService(model: ActorRef, parser: ActorRef, personal: ActorRef, authorisation: ActorRef): ActorRef
  def host: String
  def port: Int
}

class TopLevel extends Actor with ActorLogging {
  this: TopLevelConfig =>

  val model = createModel
  context watch model

  val parser = createParser
  context watch parser

  val personal = createPersonal
  context watch personal

  val authorisation = createAuthorisation
  context watch authorisation

  val service = createService(model, parser, personal, authorisation)
  context watch service

  import context._
  IO(Http) ! Http.Bind(service, host, port)

  override def supervisorStrategy: SupervisorStrategy = OneForOneStrategy() {
    case _ if model == sender => Stop
    case _ if parser == sender => Stop
    case _ if personal == sender => Stop
    case _ if service == sender => Restart
  }

  def receive = {
    case Http.CommandFailed(_) => context stop self
    case Terminated(`model`) => context stop self
    case Terminated(`parser`) => context stop self
    case Terminated(`personal`) => context stop self
    case _ =>
  }

}
