package by.verkpavel.grafolnet.service

import java.awt.image.BufferedImage

import akka.actor.{Actor, ActorRef, Props}
import akka.pattern.ask
import akka.util.Timeout
import by.verkpavel.grafolnet.database.domain.User
import by.verkpavel.grafolnet.model.ModelActor.ItemNotFound
import spray.http.MultipartFormData
import spray.http.StatusCodes._
import spray.routing.HttpService
import spray.httpx.SprayJsonSupport._

object ServiceActor {
  def props(model: ActorRef, parser: ActorRef, personal: ActorRef, authorisation: ActorRef)(implicit askTimeout: Timeout): Props = Props(classOf[ServiceActor], model, parser, personal, authorisation, askTimeout)
  def name = "service"
}

class ServiceActor(model: ActorRef, parser: ActorRef, personal: ActorRef, authorisation: ActorRef, implicit val askTimeout: Timeout) extends Actor with Service {
  def actorRefFactory = context
  def receive = runRoute(route(model, parser, personal, authorisation))
}

trait Service extends HttpService with ServiceJsonProtocol {

  import scala.language.postfixOps

  implicit def ec = actorRefFactory.dispatcher

  case class ImageUploaded(size: Long)
  implicit val imageUploadedFormat = jsonFormat1(ImageUploaded)

  def route(model: ActorRef, parser: ActorRef, personal: ActorRef, authorisation: ActorRef)(implicit askTimeout: Timeout) =
    get {
      //--------------------------------------------------
      path("index.html") {
        getFromFile("src/main/angular/root/index.html")
      } ~
        path("controllers.js") {
          getFromFile("src/main/angular/root/controllers.js")
        } ~
        path("directives.js") {
          getFromFile("src/main/angular/root/directives.js")
        } ~
        path("angular-file-upload.min.js") {
          getFromFile("src/main/angular/root/angular-file-upload.min.js")
        } ~
        path("console-sham.js") {
          getFromFile("src/main/angular/root/console-sham.js")
        } ~
        path("grapholog_avatar.png") {
          getFromFile("src/main/angular/root/grapholog_avatar.png")
        } ~
        path("images") {
          onSuccess(model ? 'list) {
            case item: List[Any] => //Uncorrect new Sample
              complete(OK, item)
          }
        } ~
        //----------------------------------------------------
        path("images" / IntNumber) { id =>
          onSuccess(model ? id) {
            case item: String =>
              complete(OK, item)

            case ItemNotFound =>
              complete(NotFound, "Not Found")
          }
        } ~
        path("imageParams" / IntNumber) { id =>
          onSuccess(parser ? id) {
            case item: Map[String, Double] =>
              complete(OK, item)

            case ItemNotFound =>
              complete(NotFound, "Not Found")
          }
        } ~
        path("personalParams" / IntNumber) { id =>
          onSuccess(personal ? id) {
            case item: String =>
              complete(OK, item)

            case ItemNotFound =>
              complete(NotFound, "Not Found")
          }
        } ~
        path("login") {
          parameters('login, 'password) { (login, password) =>
            onSuccess(authorisation ? (login, password))
            complete(s"The color is '$login' and the background is '$password'")
          }
        }
    } ~
      post {
        path("newImage") {
          handleWith { data: MultipartFormData =>
            data.fields.headOption match {
              case Some(imageEntity) =>
                val size = imageEntity.entity.data.length
                println(s"Uploaded $size")
                ImageIO.read(new ByteArrayInputStream(imageEntity.entity.data.toByteArray))
                ImageUploaded(0)
              case None =>
                println("No files")
                ImageUploaded(500)
            }
          }
        } //  ~
        //  path("registration") {
        //    handleWith { user: MultipartFormData =>
        //      onSuccess(authorisation ? user)
        //       complete(OK, "The color is")
        //     }
        //   }
      }
}
