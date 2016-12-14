package by.verkpavel.grafolnet.service

import akka.actor.{ Actor, ActorRef, Props }
import akka.pattern.ask
import akka.util.Timeout
import by.verkpavel.grafolnet.model.ModelActor.{ ItemNotFound, ItemSummaries }
import by.verkpavel.grafolnet.model.Item
import spray.http.CacheDirectives.`max-age`
import spray.http.HttpHeaders.`Cache-Control`
import spray.http.StatusCodes._
import spray.routing.HttpService

object ServiceActor {
  def props(model: ActorRef)(implicit askTimeout: Timeout): Props = Props(classOf[ServiceActor], model, askTimeout)
  def name = "service"
}

class ServiceActor(model: ActorRef, implicit val askTimeout: Timeout) extends Actor with Service {
  def actorRefFactory = context
  def receive = runRoute(route(model))
}

trait Service extends HttpService with ServiceJsonProtocol {

  import scala.language.postfixOps // for 'q ? in parameter() below

  implicit def ec = actorRefFactory.dispatcher

  val CacheHeader = (maxAge: Long) => `Cache-Control`(`max-age`(maxAge)) :: Nil

  // Cache items by a function of their stock level, but avoid leaking exact
  // stock levels via the Cache-Control header's max-age. Because this is an
  // example I'm hard-coding cache multipliers here.
  val MaxAge = (stockLevel: Int) => 10 * math.sqrt(10 + stockLevel).toLong
  val MaxAge404 = 600l

  def route(model: ActorRef)(implicit askTimeout: Timeout) =
    get {
      path("items") {
        parameter('q ?) { term =>
          val msg = term.map('query -> _).getOrElse('list)
          onSuccess(model ? msg) {
            case ItemSummaries(summaries) =>
              val maxAge = summaries match {
                case Nil => MaxAge404
                // Use smallest stock value in list for calculating max-age
                case xs => MaxAge(xs.map(_.stock).reduce(math.min))
              }
              import spray.httpx.SprayJsonSupport._
              complete(OK, CacheHeader(maxAge), summaries map { PublicItemSummary(_) })
          }
        }
      } ~
        path("items" / IntNumber) { id =>
          onSuccess(model ? id) {
            case item: Item =>
              import spray.httpx.SprayJsonSupport._
              complete(OK, CacheHeader(MaxAge(item.stock)), PublicItem(item))

            case ItemNotFound =>
              complete(NotFound, CacheHeader(MaxAge404), "Not Found")
          }
        }
    }

}
