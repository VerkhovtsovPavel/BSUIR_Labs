package by.pavelverk.hardwrite.core.auth

import by.pavelverk.hardwrite.core.InMemoryStorage
import by.pavelverk.hardwrite.utils.db.DatabaseConnector
import by.pavelverk.hardwrite.core.AuthData

import scala.concurrent.{ExecutionContext, Future}

sealed trait AuthDataStorage {

  def findAuthData(login: String): Future[Option[AuthData]]

  def saveAuthData(authData: AuthData): Future[AuthData]

}

class JdbcAuthDataStorage(
  val databaseConnector: DatabaseConnector
)(implicit executionContext: ExecutionContext) extends AuthDataTable with AuthDataStorage {

  import databaseConnector._
  import databaseConnector.profile.api._

  override def findAuthData(login: String): Future[Option[AuthData]] =
    db.run(auth.filter(d => d.username === login || d.email === login).result.headOption)

  override def saveAuthData(authData: AuthData): Future[AuthData] =
    db.run(auth.insertOrUpdate(authData)).map(_ => authData)

}

class InMemoryAuthDataStorage extends InMemoryStorage[String, AuthData] with AuthDataStorage {
  override def findAuthData(login: String): Future[Option[AuthData]] = find(d => d.username == login || d.email == login)

  override def saveAuthData(authData: AuthData): Future[AuthData] = save(authData)
}