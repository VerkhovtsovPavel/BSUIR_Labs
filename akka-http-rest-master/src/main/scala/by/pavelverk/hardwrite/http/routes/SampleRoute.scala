package by.pavelverk.hardwrite.http.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import by.pavelverk.hardwrite.core.Sample
import by.pavelverk.hardwrite.core.sample.SampleService
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import io.circe.generic.auto._
import io.circe.syntax._

import scala.concurrent.ExecutionContext

class SampleRoute(
                   secretKey: String,
                   sampleService: SampleService
                 )(implicit executionContext: ExecutionContext) extends FailFastCirceSupport {

  import by.pavelverk.hardwrite.utils.SecurityDirectives._
  import StatusCodes._
  import sampleService._

  val route: Route = pathPrefix("samples") {
    pathPrefix("new") {
      pathEndOrSingleSlash {
        authenticate(secretKey) { userId =>
          post {
            entity(as[Sample]) { sample =>
              complete(createSample(sample).map {
                case s: Sample =>
                  OK -> s.asJson
                case _ =>
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
            complete(getSample(id).map {
              case Some(sample) =>
                OK -> sample.asJson
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
