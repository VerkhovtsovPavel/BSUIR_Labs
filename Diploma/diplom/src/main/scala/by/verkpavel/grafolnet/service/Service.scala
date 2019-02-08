package by.verkpavel.grafolnet.service

import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import javax.imageio.ImageIO

import akka.actor.{Actor, ActorRef, Props}
import akka.pattern.ask
import akka.util.Timeout
import by.verkpavel.grafolnet.authorisation.AuthorisationData
import by.verkpavel.grafolnet.database.domain.{Sample, User}
import by.verkpavel.grafolnet.model.ModelActor.ItemNotFound
import spray.http.MultipartFormData
import spray.http.StatusCodes._
import spray.routing.{Directive, HttpService}
import spray.httpx.SprayJsonSupport._
import spray.routing.directives.OnSuccessFutureMagnet

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

  var currentUser: User = _

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
        path("application.js") {
          getFromFile("src/main/angular/root/application.js")
        } ~
        //----------------------------------------------------
        path("images") {
          onSuccess(model ? currentUser._id) {
            case item: List[Sample] =>
              complete(OK, item)
          }
        } ~
        path("images" / """[\w\d]+""".r) { id =>
          onSuccess(model ? id) {
            case item: String =>
              complete(OK, item)

            case ItemNotFound =>
              complete(NotFound, "Not Found")
          }
        } ~
        path("imageParams" / """[\w\d]+""".r) { id =>
          onSuccess(parser ? id) {
            case item: Map[String, Double] =>
              complete(OK, item)

            case ItemNotFound =>
              complete(NotFound, "Not Found")
          }
        } ~
        path("personalParams" / """[\w\d]+""".r) { id =>
          onSuccess(personal ? id) {
            case item: Int =>
              complete(OK, item.toString)

            case item: String =>
              complete(OK, item)

            case ItemNotFound =>
              complete(NotFound, "Not Found")
          }
        } ~
        path("login") {
          parameters('login, 'password) { (login, password) =>
            onSuccess(authorisation ? (login, password)) {
              case result: Some[User] =>
                currentUser = result.get
                if (currentUser.isAdmin)
                  complete("Access granted")
                else
                  complete("Login successful")
              case None =>
                complete("Please check username and password")
            }
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
                model ? ((imageEntity.entity.data.toByteArray, imageEntity.headers(1).value.split(";")(2).split("=")(1), currentUser._id))
                ImageUploaded(0)
              case None =>
                println("No files")
                ImageUploaded(500)
            }
          }
        } ~
          path("registration") {
            respondWithStatus(Created) {
              entity(as[AuthorisationData]) { user =>
                onSuccess(authorisation ? user) {
                  case s: String if s.length == 0 =>
                    complete(OK, "User name already used")
                  case id =>
                    complete(OK, "Your id is " + id)
                }
              }
            }
          }
      } ~
      delete {
        path("images" / """[\w\d]+""".r) { id =>
          onSuccess(model ? (('delete, id))) {
            case item: String =>
              complete(OK, item)

            case ItemNotFound =>
              complete(NotFound, "Not Found")
          }
        }
      }
}

//TODO Localization

