package by.pavelverk.hardwrite.http.routes

import akka.http.scaladsl.server.Directives.{complete, get, pathEndOrSingleSlash, pathPrefix, post}
import by.pavelverk.hardwrite.core.feature.FeaturesService
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import by.pavelverk.hardwrite.core.result.ResultService
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import io.circe.generic.auto._
import io.circe.syntax._

import scala.concurrent.ExecutionContext

class ActionsRoute (secretKey: String,
                    featureService: FeaturesService,
                    resultService: ResultService
                   )(implicit executionContext: ExecutionContext) extends FailFastCirceSupport {
  import StatusCodes._
  import featureService._
  import resultService._

  //TODO Add sample procession if some aspect is missing

  val route: Route = pathPrefix("samples") {
    pathPrefix(Segment / "features") { id =>
      pathEndOrSingleSlash {
        get {
          complete(getFeatures(id).map {
            case Some(features) =>
              OK -> features.asJson
            case None =>
              BadRequest -> None.asJson
          })
        }
      }
    } ~ pathPrefix(Segment / "med") { id =>
      pathEndOrSingleSlash {
        get {
          complete(getNeuroAnalysis(id).map {
            case Some(result) =>
              OK -> result.asJson
            case None =>
              BadRequest -> None.asJson
          })
        }
      }
    } ~ pathPrefix(Segment / "cybetsec") { id =>
      pathEndOrSingleSlash {
        get {
          complete(getCyberSecAnalysis(id).map {
            case Some(result) =>
              OK -> result.asJson
            case None =>
              BadRequest -> None.asJson
          })
        }
      }
    } ~ pathPrefix(Segment / "psyco") { id =>
      pathEndOrSingleSlash {
        get {
          complete(getPsychoAnalysis(id).map {
            case Some(result) =>
              OK -> result.asJson
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
