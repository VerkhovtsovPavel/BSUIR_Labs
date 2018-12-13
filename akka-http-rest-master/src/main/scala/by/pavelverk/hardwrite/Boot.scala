package by.pavelverk.hardwrite

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import by.pavelverk.hardwrite.core.auth.{AuthService, JdbcAuthDataStorage}
import by.pavelverk.hardwrite.core.profile.{JdbcUserProfileStorage, UserProfileService}
import by.pavelverk.hardwrite.core.sample.{JdbcSampleStorage, SampleService}
import by.pavelverk.hardwrite.core.feature.{JdbcFeaturesStorage, FeaturesService}
import by.pavelverk.hardwrite.http.HttpRoute
import by.pavelverk.hardwrite.utils.Config
import by.pavelverk.hardwrite.utils.db.DatabaseConnector
import by.pavelverk.hardwrite.utils.db.DatabaseMigrationManager

import scala.concurrent.ExecutionContext

object Boot extends App {

  def startApplication() = {
    implicit val actorSystem = ActorSystem()
    implicit val executor: ExecutionContext = actorSystem.dispatcher
    implicit val materializer: ActorMaterializer = ActorMaterializer()

    val config = Config.load()

    new DatabaseMigrationManager(
      config.database.jdbcUrl,
      config.database.username,
      config.database.password
    ).migrateDatabaseSchema()

    val databaseConnector = new DatabaseConnector(
      config.database.jdbcUrl,
      config.database.username,
      config.database.password
    )

    val userProfileStorage = new JdbcUserProfileStorage(databaseConnector)
    val authDataStorage = new JdbcAuthDataStorage(databaseConnector)
    val sampleStorage = new JdbcSampleStorage(databaseConnector)
    val featureStorage = new JdbcFeaturesStorage(databaseConnector)

    val usersService = new UserProfileService(userProfileStorage)
    val authService = new AuthService(authDataStorage, config.secretKey)
    val sampleService = new SampleService(sampleStorage)
    val featuresService = new FeaturesService(featureStorage)
    val httpRoute = new HttpRoute(usersService, sampleService, featuresService, authService, config.secretKey)

    Http().bindAndHandle(httpRoute.route, config.http.host, config.http.port)
  }

  startApplication()
}