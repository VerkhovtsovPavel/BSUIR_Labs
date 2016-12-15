package by.verkpavel.grafolnet.service

import akka.actor.{ Actor, ActorRef, Props }
import akka.pattern.ask
import akka.util.Timeout
import by.verkpavel.grafolnet.model.ModelActor.ItemNotFound
import by.verkpavel.grafolnet.model.{ ImageRequest, ImageResponse }
import spray.http.StatusCodes._
import spray.routing.HttpService
import spray.httpx.SprayJsonSupport._

object ServiceActor {
  def props(model: ActorRef)(implicit askTimeout: Timeout): Props = Props(classOf[ServiceActor], model, askTimeout)
  def name = "service"
}

class ServiceActor(model: ActorRef, implicit val askTimeout: Timeout) extends Actor with Service {
  def actorRefFactory = context
  def receive = runRoute(route(model))
}

trait Service extends HttpService with ServiceJsonProtocol {

  import scala.language.postfixOps

  implicit def ec = actorRefFactory.dispatcher

  def route(model: ActorRef)(implicit askTimeout: Timeout) =
    get {
      path("imageParams") {
        onSuccess(model ? 'list) {
          case item: List[ImageResponse] =>
            complete(OK, item)
        }
      } ~
        path("imageParams" / IntNumber) { id =>
          onSuccess(model ? id) {
            case item: ImageResponse =>
              complete(OK, item)

            case ItemNotFound =>
              complete(NotFound, "Not Found")
          }
        }
    } ~
      post {
        path("newImage") {
          entity(as[ImageRequest]) {
            image =>
              onSuccess(model ? image) {
                case id: Int =>
                  complete(OK, id.toString)
              }
          }
        }
      }
}
