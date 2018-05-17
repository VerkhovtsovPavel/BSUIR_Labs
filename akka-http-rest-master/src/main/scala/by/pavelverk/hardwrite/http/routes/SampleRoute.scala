package by.pavelverk.hardwrite.http.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import by.pavelverk.hardwrite.core.UserProfileUpdate
import by.pavelverk.hardwrite.core.profile.UserProfileService
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import io.circe.generic.auto._
import io.circe.syntax._

import scala.concurrent.ExecutionContext

class SampleRoute(
  secretKey: String,
  usersService: UserProfileService
)(implicit executionContext: ExecutionContext) extends FailFastCirceSupport {

  import by.pavelverk.hardwrite.utils.SecurityDirectives._
  import StatusCodes._
  import usersService._

  val route = pathPrefix("samples") {
    pathPrefix("new") {
      pathEndOrSingleSlash {
        authenticate(secretKey) { userId =>
          post {
            entity(as[UserProfileUpdate]) { userUpdate =>
              complete(updateProfile(userId, userUpdate).map {
                case Some(profile) =>
                  OK -> profile.asJson
                case None =>
                  BadRequest -> None.asJson
              })
            }
          }
        }
      }
    } ~
      pathPrefix(Segment) { id =>
        pathEndOrSingleSlash {
          get {
            complete(getProfile(id).map {
              case Some(profile) =>
                OK -> profile.asJson
              case None =>
                BadRequest -> None.asJson
            })
          } ~
            delete {
              entity(as[UserProfileUpdate]) { userUpdate =>
                complete(updateProfile(id, userUpdate).map {
                  case Some(profile) =>
                    OK -> profile.asJson
                  case None =>
                    BadRequest -> None.asJson
                })
              }
            }
        }
      } ~
      pathPrefix(Segment / "params") { id =>
        pathEndOrSingleSlash {
          get {
            complete(getProfile(id).map {
              case Some(profile) =>
                OK -> profile.asJson
              case None =>
                BadRequest -> None.asJson
            })
          }
        }
      }
    // getParameters -> getSample -  processed -> return params
    //                            \- not processed -> extract parameters -> store in DB -> return params
  }
}
