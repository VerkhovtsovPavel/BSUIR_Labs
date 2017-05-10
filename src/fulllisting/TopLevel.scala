package by.verkpavel.grafolnet.main

import akka.actor.SupervisorStrategy.{Restart, Stop}
import akka.actor.{Terminated, _}
import akka.io.IO
import akka.util.Timeout
import by.verkpavel.grafolnet.model.ModelActor
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
  def createService(model: ActorRef) = context.actorOf(ServiceActor.props(model), ServiceActor.name)
}

trait TopLevelConfig {
  def createModel: ActorRef
  def createService(model: ActorRef): ActorRef
  def host: String
  def port: Int
}

class TopLevel extends Actor with ActorLogging {
  this: TopLevelConfig =>

  val model = createModel
  context watch model

  val service = createService(model)
  context watch service

  import context._
  IO(Http) ! Http.Bind(service, host, port)

  override def supervisorStrategy: SupervisorStrategy = OneForOneStrategy() {
    case _ if model == sender => Stop
    case _ if service == sender => Restart
  }

  def receive = {
    case Http.CommandFailed(_) => context stop self
    case Terminated(`model`) => context stop self
    case _ =>
  }

}
