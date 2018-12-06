package by.pavelverk.hardwrite.core.feature

import by.pavelverk.hardwrite.core.{InMemoryStorage, Features}
import by.pavelverk.hardwrite.utils.db.DatabaseConnector

import scala.concurrent.{ExecutionContext, Future}

sealed trait FeaturesStorage {

  def getFeatures(id: String): Future[Option[Features]]

  def saveFeatures(sample: Features): Future[Features]

}

class JdbcFeaturesStorage(val databaseConnector: DatabaseConnector)(implicit executionContext: ExecutionContext)
  extends FeaturesTable with FeaturesStorage {

  import databaseConnector._
  import databaseConnector.profile.api._

  def getFeatures(id: String): Future[Option[Features]] = db.run(features.filter(_.id === id).result.headOption)

  def saveFeatures(sample: Features): Future[Features] =
    db.run(features.insertOrUpdate(sample)).map(_ => sample)

}

class InMemoryUserProfileStorage extends InMemoryStorage[String, Features] with FeaturesStorage {

  override def getFeatures(id: String): Future[Option[Features]] = get(id)

  override def saveFeatures(sample: Features): Future[Features] = save(sample)

}