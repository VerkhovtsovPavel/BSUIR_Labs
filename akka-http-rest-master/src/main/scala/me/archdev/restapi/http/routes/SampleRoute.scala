package me.archdev.restapi.http.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import io.circe.generic.auto._
import io.circe.syntax._
import me.archdev.restapi.core.profiles.UserProfileService
import me.archdev.restapi.core.{UserProfile, UserProfileUpdate}
import me.archdev.restapi.utils.SecurityDirectives

import scala.concurrent.ExecutionContext

class SampleRoute(
  secretKey: String,
  usersService: UserProfileService
)(implicit executionContext: ExecutionContext) extends FailFastCirceSupport {

  import SecurityDirectives._
  import StatusCodes._
  import usersService._

  val route = pathPrefix("samples") {
    pathEndOrSingleSlash {
      get {
        complete(getProfiles().map(_.asJson))
      }
    } ~
      pathPrefix("me") {
        pathEndOrSingleSlash {
          authenticate(secretKey) { userId =>
            get {
              complete(getProfile(userId))
            }
          }
        }
      } ~
      pathPrefix("new") {
        pathEndOrSingleSlash {
          authenticate(secretKey) { userId =>
            delete {
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
          }
      }
  }
}
