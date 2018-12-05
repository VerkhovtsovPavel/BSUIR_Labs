package by.pavelverk.hardwrite.http

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import by.pavelverk.hardwrite.core.auth.AuthService
import by.pavelverk.hardwrite.core.profile.UserProfileService
import by.pavelverk.hardwrite.core.sample.SampleService
import by.pavelverk.hardwrite.http.routes.{ActionsRoute, AuthRoute, ProfileRoute, SampleRoute}
import ch.megard.akka.http.cors.scaladsl.CorsDirectives._

import scala.concurrent.ExecutionContext

class HttpRoute(
  userProfileService: UserProfileService,
  sampleService: SampleService,
  authService: AuthService,
  secretKey: String
)(implicit executionContext: ExecutionContext) {

  private val usersRouter = new ProfileRoute(secretKey, userProfileService)
  private val actionsRouter = new ActionsRoute()
  private val sampleRouter = new SampleRoute(secretKey, sampleService)
  private val authRouter = new AuthRoute(authService)

  val route: Route =
    cors() {
      pathPrefix("v1") {
          usersRouter.route ~
          sampleRouter.route ~
          authRouter.route
      } ~
        pathPrefix("healthcheck") {
          get {
            complete("OK")
          }
        }
    }
}
