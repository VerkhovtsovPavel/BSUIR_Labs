package by.pavelverk.hardwrite.core.profile

import by.pavelverk.hardwrite.core.InMemoryStorage
import by.pavelverk.hardwrite.utils.db.DatabaseConnector
import by.pavelverk.hardwrite.core.UserProfile

import scala.concurrent.{ExecutionContext, Future}

sealed trait UserProfileStorage {

  def getProfiles(): Future[Seq[UserProfile]]

  def getProfile(id: String): Future[Option[UserProfile]]

  def saveProfile(profile: UserProfile): Future[UserProfile]

}

class JdbcUserProfileStorage(
  val databaseConnector: DatabaseConnector
)(implicit executionContext: ExecutionContext) extends UserProfileTable with UserProfileStorage {

  import databaseConnector._
  import databaseConnector.profile.api._

  def getProfiles(): Future[Seq[UserProfile]] = db.run(profiles.result)

  def getProfile(id: String): Future[Option[UserProfile]] = db.run(profiles.filter(_.id === id).result.headOption)

  def saveProfile(profile: UserProfile): Future[UserProfile] =
    db.run(profiles.insertOrUpdate(profile)).map(_ => profile)

}

class InMemoryUserProfileStorage extends InMemoryStorage[String, UserProfile] with UserProfileStorage {

  override def getProfiles(): Future[Seq[UserProfile]] = getAll()

  override def getProfile(id: String): Future[Option[UserProfile]] = get(id)

  override def saveProfile(profile: UserProfile): Future[UserProfile] = save(profile)
}